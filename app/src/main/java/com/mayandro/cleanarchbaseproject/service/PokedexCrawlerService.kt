package com.mayandro.cleanarchbaseproject.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokedexItem
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokedexPokemon
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokemonResponseModel
import com.mayandro.cleanarchbaseproject.data.repo.PokemonRepository
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.utility.data_observer.PokedexDataObserver
import com.mayandro.cleanarchbaseproject.utility.notification.NotificationProvider
import com.mayandro.cleanarchbaseproject.utility.toPokemon
import com.mayandro.cleanarchbaseproject.utility.toPokemonData
import com.mayandro.cleanarchbaseproject.utility.toPokemonMoves
import com.mayandro.cleanarchbaseproject.utility.toPokemonStats
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.NoSuchElementException

class PokedexCrawlerService : IntentService(PokedexCrawlerService::class.java.name), KoinComponent {

    private val repository: PokemonRepository by inject()
    private val notificationProvider: NotificationProvider by inject()
    private val pokedexDataObserver: PokedexDataObserver by inject()
    private var notificationBuilder: NotificationCompat.Builder? = null

    companion object {
        var startingIntent: Intent? = null
            private set(value) {
                field = value
            }

        fun createStartingIntent(context: Context): Intent {
            startingIntent = Intent(context, PokedexCrawlerService::class.java)
            return startingIntent!!
        }
    }

    @InternalCoroutinesApi
    override fun onHandleIntent(p0: Intent?) {
        fetchPokedexAndSaveToDb()
    }

    private fun fetchPokedexAndSaveToDb() {
        println("PokedexCrawlerService.fetchPokedexAndSaveToDb3")

        GlobalScope.launch {
            val flowPokedexSavedEntry = repository.getLastSavedPokemonFromDb()
            val flowPokedexServerEntry = repository.getPokedexEntiresFromServer()

            flowPokedexSavedEntry
                .zip(flowPokedexServerEntry) { lastSavedPokemon, pokemonList ->
                    pokedexDataObserver.setPokedexSize(pokemonList.size)
                    println("PokedexCrawlerService.fetchPokedexAndSaveToDb3 ${pokemonList.size} last index ${pokemonList.last().orderID} last saved pokemon: ${lastSavedPokemon?.orderID} ${lastSavedPokemon?.name}")
                    when {
                        lastSavedPokemon == null -> pokemonList
                        lastSavedPokemon.orderID == pokemonList.last().orderID -> emptyList()
                        lastSavedPokemon.orderID < pokemonList.last().orderID -> pokemonList.subList(
                            lastSavedPokemon.orderID,
                            pokemonList.lastIndex
                        )
                        else -> emptyList()
                    }
                }.transform { list ->
                    if (list.isNotEmpty()) {
                        generateNotification()
                    }
                    println("PokedexCrawlerService.fetchPokedexAndSaveToDb3 ${list.size} last index ${list.last().orderID}")
                    list.forEach {
                        println("emit ${it.name} ${it.orderID}")
                        emit(it)
                    }
                }
                .flatMapConcat {
                    pokedexDataObserver.setCurrentPokemon(it)
                    updateNotification("Fetching data for: ${it.name}")
                    repository.savePokemonToDb(it.toPokemon())
                    repository.savePokemonStatsToDb(it.toPokemonStats())
                    repository.getPokemonByIdFromServer(it.nDex)
                }.transform {
                    repository.savePokemonMovesToDb(it.toPokemonMoves())
                    emit(it)
                }
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    println("on Error ${e.message}")
                    updateNotification("Error on fetching data: ${e.message} $e")
                    if(e is NoSuchElementException) {
                        pokedexDataObserver.setPokedexFetchFinished()
                    }
                }
                .collect {
                    updateNotification("Pokedex fetch finished for ${it.name}")
                    pokedexDataObserver.checkIfPokedexFetchIsFinished()
                }
        }

        showCompletionMessage("Pokedex Finished")
    }

    private fun generateNotification() {
        notificationBuilder = notificationProvider.getNotificationBuilder(
            "Fetching Pokedex",
            "Fetching data for the pokedex"
        )
        notificationProvider.showNotification(notificationBuilder = notificationBuilder)
    }

    private fun updateNotification(message: String) {
        notificationProvider.updateNotificationMessage(
            message,
            notificationBuilder = notificationBuilder
        )
    }

    private fun showCompletionMessage(message: String) {
        notificationProvider.showProcessCompletionMessage(
            completionMessage = message,
            notificationBuilder = notificationBuilder
        )
    }
}