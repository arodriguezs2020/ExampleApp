package com.example.exampleapp.presentation

import android.app.Application
import com.example.exampleapp.presentation.di.appModule
import com.example.exampleapp.presentation.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MagicApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin!
        startKoin {
            // Android context
            androidContext(this@MagicApplication)
            // modules
            modules(listOf(appModule, homeModule))
        }
    }
}