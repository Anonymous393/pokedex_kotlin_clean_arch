package com.mayandro.cleanarchbaseproject.utility.data_observer

import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokedexItem
import java.util.*

class PokedexDataObserver: Observable() {

    private var pokedexItem: PokedexItem? = null

    private var totalCount: Int = 0

    private var currentProgress: Int = 0

    private var pokedexFetchFinished: Boolean = false

    private fun notifySongChanged() {
        setChanged()
        notifyObservers()
    }

    fun setPokedexSize(count: Int) {
        totalCount = count
        notifySongChanged()
    }

    fun setCurrentPokemon(item: PokedexItem?) {
        pokedexItem = item
        setProgressPercent(item)
        notifySongChanged()
    }

    fun getCurrentPokemon(): PokedexItem? {
        return pokedexItem
    }

    fun getPokedexProgress(): Int {
        return currentProgress
    }

    private fun setProgressPercent(item: PokedexItem?) {
        if(item == null) return
        if(totalCount == 0) return
        if(totalCount < item.orderID) return

        currentProgress = ((item.orderID * 100 )/totalCount)
    }

    fun checkIfPokedexFetchIsFinished() {
        if(pokedexItem == null) return
        if(pokedexItem!!.orderID == totalCount)
        {
            println("PokedexDataObserver.setPokedexFetchFinished pokedexItem!!.orderID ${pokedexItem!!.orderID}")
            setPokedexFetchFinished()
        }
    }

    fun setPokedexFetchFinished() {
        pokedexFetchFinished = true
        notifySongChanged()
    }

    fun getPokedexFetchFinished(): Boolean = pokedexFetchFinished
}