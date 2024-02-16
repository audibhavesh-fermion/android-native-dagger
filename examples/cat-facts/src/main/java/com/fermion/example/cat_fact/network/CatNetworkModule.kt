package com.fermion.example.cat_fact.network

import androidx.annotation.NonNull
import com.fermion.android.base.network.NetworkFactory
import com.fermion.example.cat_fact.config.AppConfig
import dagger.Module
import dagger.Provides


@Module
class CatNetworkModule {

    private var appConfig: AppConfig = AppConfig()

    @Provides
    @NonNull
    fun provideCatFactApiService(
        @NonNull serviceFactory: NetworkFactory
    ): CatFactService {
        return serviceFactory.create(
            appConfig.baseUrl, CatFactService::class.java
        )
    }
}