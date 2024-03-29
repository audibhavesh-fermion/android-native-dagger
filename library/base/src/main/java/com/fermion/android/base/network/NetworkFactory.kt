package com.fermion.android.base.network

import androidx.annotation.NonNull
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Bhavesh Auodichya.
 *
 * **Info** : Network factory builds retrofit and other factory object
 *
 *@since 1.0.0
 */
class NetworkFactory {

    @NonNull
    private val factory: Converter.Factory

    @NonNull
    private val okHttpClient: OkHttpClient

    @Inject
    constructor(factory: Converter.Factory, okHttpClient: OkHttpClient) {
        this.factory = factory
        this.okHttpClient = okHttpClient
    }

    fun <T> create(baseUrl: String, serviceClass: Class<T>): T {
        return create(baseUrl, okHttpClient, serviceClass)
    }

    fun <T> create(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        serviceClass: Class<T>
    ): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(factory)
            .client(okHttpClient)
            .build()
            .create(serviceClass)
    }
}