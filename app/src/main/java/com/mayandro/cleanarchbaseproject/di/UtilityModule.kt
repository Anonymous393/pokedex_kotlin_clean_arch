package com.mayandro.cleanarchbaseproject.di

import com.mayandro.cleanarchbaseproject.utility.data_observer.PokedexDataObserver
import com.mayandro.cleanarchbaseproject.utility.notification.NotificationProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val utilityModule = module {
    single { NotificationProvider(context = androidApplication()) }

    single { PokedexDataObserver() }
}