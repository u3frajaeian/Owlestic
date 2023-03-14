package com.u3f.owlesticchallenge.base.common

import com.u3f.owlesticchallenge.domain.model.error.NetworkError


sealed class Response<out T : Any> {
    data class Success<out T : Any>(val data: T) : Response<T>()
    data class Error(val error: NetworkError) : Response<Nothing>()
    object Loading : Response<Nothing>()
}