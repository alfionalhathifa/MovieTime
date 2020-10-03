package com.zendev.movietime

import android.app.Application
import com.zendev.movietime.base.di.useCaseModule
import com.zendev.movietime.base.di.viewModelModule
import com.zendev.movietime.core.di.databaseModule
import com.zendev.movietime.core.di.networkModule
import com.zendev.movietime.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
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