package com.google.advancedsoft

import android.app.Application
import com.google.advancedsoft.di.networkModule
import com.google.advancedsoft.di.viewModelsModule
import org.koin.core.context.startKoin

class AdvancedSoftApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { modules(listOf(networkModule, viewModelsModule)) }
    }
}