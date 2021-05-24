package com.akbaradi.tourism

import android.app.Application
import com.akbaradi.tourism.core.di.databaseModule
import com.akbaradi.tourism.core.di.networkModule
import com.akbaradi.tourism.core.di.repositoryModule
import com.akbaradi.tourism.di.useCaseModule
import com.akbaradi.tourism.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}