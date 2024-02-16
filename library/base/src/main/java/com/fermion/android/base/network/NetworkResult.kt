package com.fermion.android.base.network

import com.fermion.android.base.constants.Status

/**
 * Created by Bhavesh Auodichya.
 *
 * **Note** Network Result class provides success, error, and loading data events
 *
 * @property isSuccess if api call is successful or not (if http status code is 200)
 *@since 1.0.0
 */
class NetworkResult<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T): NetworkResult<T> {
            return NetworkResult(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): NetworkResult<T> {
            return NetworkResult(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): NetworkResult<T> {
            return NetworkResult(Status.LOADING, data, null)
        }
    }

    val isSuccess: Boolean = status == Status.SUCCESS

}