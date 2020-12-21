package com.mieszko.mybmicalculator

import android.app.Application
import com.mieszko.mybmicalculator.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                listOf(
                    NetworkModule,
                    RepositoryModule,
                    UseCaseModule,
                    ViewModelModule,
                    MapperModule
                )
            )
        }
    }
}
