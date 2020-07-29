package com.mayandro.cleanarchbaseproject

import android.app.Application
import com.mayandro.cleanarchbaseproject.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    networkModule,
                    dataModule,
                    useCaseModule,
                    uiModule,
                    utilityModule
                )
            )
        }
    }
}