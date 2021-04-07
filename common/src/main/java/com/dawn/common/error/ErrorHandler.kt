package com.dawn.common.error
import com.dawn.common.error.ErrorEntity
import retrofit2.Response

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity

    fun <T>getHttpErrors(errorResponse: Response<T>) :ErrorEntity
}