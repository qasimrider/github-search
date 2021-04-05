package com.dawn.githubsearch

import android.app.Application
import com.dawn.business.useCasesDependencies
import com.dawn.network.networkModule
import com.dawn.repositories.repoDependencies

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        //region Koin Initializations
        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    useCasesDependencies,
                    repoDependencies,
                    networkModule,
                )
            )
        }
        
        //endregion
    }

}