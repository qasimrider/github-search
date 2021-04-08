package com.dawn.common.error

import retrofit2.Response

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity

    fun <T> getHttpErrors(errorResponse: Response<T>): ErrorEntity
}