package com.dawn.common.mvi

import com.dawn.common.error.ErrorEntity
import com.dawn.common.functional.Either

interface IReducer<STATE, L : ErrorEntity, R : Any> {
    fun reduce(result: Either<L, R>, state: STATE): STATE
}