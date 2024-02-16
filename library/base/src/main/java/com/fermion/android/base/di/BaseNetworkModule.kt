package com.fermion.android.base.di

import androidx.annotation.NonNull
import com.fermion.android.base.config.Environment
import com.fermion.android.base.config.NetworkConfig
import com.fermion.android.base.config.RunConfig
import com.fermion.android.base.network.BaseApiService
import com.fermion.android.base.network.NetworkFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers.IO
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ServiceLoader
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


/**
 * Created by Bhavesh Auodichya.
 *
 * Base Network Module for injecting dependencies related to network
 *
 *@since 1.0.0
 */
@Module
class BaseNetworkModule {

    private val runConfig: RunConfig = RunConfig()


    /**
     * Created by Bhavesh Auodichya.
     *
     * Provides Base Api service for making network call.
     *
     * Use this as template for creating your specific api service class.
     *@since 1.0.0
     */
    @Provides
    @NonNull
    fun provideBaseApiService(
        @NonNull serviceFactory: NetworkFactory,
    ): BaseApiService {
        return serviceFactory.create(runConfig.baseUrl, BaseApiService::class.java)
    }

    /**
     * Created by Bhavesh Auodichya.
     *
     *Provides singleton Gson object
     *@since 1.0.0
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        for (factory in ServiceLoader.load(TypeAdapterFactory::class.java)) {
            builder.registerTypeAdapterFactory(factory)
        }
        return builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").setLenient().create()
    }

    /**
     * Created by Bhavesh Auodichya.
     *
     *Provides coroutine context
     *@since 1.0.0
     */
    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return IO
    }

    /**
     * Created by Bhavesh Auodichya.
     *
     *Provided gson factory
     *@since 1.0.0
     *@param gson:Gson
     */
    @Provides
    @Singleton
    fun provideFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    /**
     * Created by Bhavesh Auodichya.
     *
     *Provided http logging interceptor.
     *
     *If production environment interceptor will not print network logs.
     *@since 1.0.0
     * @return HttpLoggingInterceptor
     */
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level =
            if (runConfig.environment != Environment.PROD) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }


    /**
     * Created by Bhavesh Auodichya.
     *
     * Provides primary OkHttpClient.
     *
     * You can introduce you custom client with annotating it with your custom qualifier.
     *
     * This client is set with custom header and overall timeout of 2 minutes
     *@since 1.0.0
     *@param interceptor: HttpLoggingInterceptor
     *@return OkHttpClient
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        // must be the last interceptor to catch and log modified requests
        okHttpBuilder.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + NetworkConfig.authToken).build()
            try {
                val hasMultipart: Boolean = request.headers.names().contains("multipart")
                interceptor.setLevel(if (hasMultipart) HttpLoggingInterceptor.Level.HEADERS else HttpLoggingInterceptor.Level.BODY)
                chain.proceed(request)
            } catch (e: Exception) {
                Response.Builder().request(request).protocol(Protocol.HTTP_1_1).code(404)
                    .message("Connection Timeout")
                    .body(e.localizedMessage?.toResponseBody("text/plain".toMediaType())).build()
            }
        }
        if (runConfig.environment != Environment.PROD) {
            okHttpBuilder.addNetworkInterceptor(interceptor)
        }
        return okHttpBuilder
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .build()
    }
}
