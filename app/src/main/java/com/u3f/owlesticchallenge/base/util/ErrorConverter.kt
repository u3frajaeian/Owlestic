package com.u3f.owlesticchallenge.base.util

import com.google.gson.Gson
import com.u3f.owlesticchallenge.data.remote.model.ErrorDTO
import com.u3f.owlesticchallenge.data.remote.model.toError
import com.u3f.owlesticchallenge.domain.model.error.NetworkError
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

object ErrorConverter {
    fun castError(errorBody: ResponseBody?): NetworkError {
        errorBody?.let {
            return Gson().fromJson(it.string(), ErrorDTO::class.java).toError()
        } ?: return NetworkError("مشکلی پیش آمده")
    }

    fun castAPIError(exception: HttpException): NetworkError {
        try {
            exception.response()?.errorBody()?.string()?.let { errorString ->
                return Gson().fromJson(errorString, ErrorDTO::class.java).toError()
            } ?: return NetworkError("مشکلی پیش آمده")
        } catch (e: Exception) {
            return NetworkError("مشکلی پیش آمده")
        }
    }

    fun castIOError(exception: IOException): NetworkError {
        try {
            exception.message?.let { errorString ->
                return Gson().fromJson(errorString, ErrorDTO::class.java).toError()
            } ?: return NetworkError("عدم اتصال به اینترنت")
        } catch (e: Exception) {
            return NetworkError("عدم اتصال به اینترنت")
        }
    }
}