package com.fermion.android.base.network

import retrofit2.Response
import timber.log.Timber

/**
 * Created by Bhavesh Auodichya.
 *
 * **Info** : Base Data Source provide result wrapper for network  or other common SST
 *
 *@since 1.0.0
 */
abstract class BaseDataSource {
    protected suspend fun <T> getNetworkResult(call: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return NetworkResult.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): NetworkResult<T> {
        Timber.e(message)
        return NetworkResult.error("Network call has failed for a following reason: $message")
    }
}