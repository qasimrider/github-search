package com.dawn.common.base

import com.dawn.common.error.ErrorEntity
import com.dawn.common.functional.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<out Type, in Params>() {
    abstract suspend fun run(param: Params): Either<ErrorEntity, Type>
    operator fun invoke(
        viewModelScope: CoroutineScope,
        params: Params,
        onResult: (Either<ErrorEntity, Type>) -> Unit
    ) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO)
                .launch {
                    val result = run(params)
                    withContext(Dispatchers.Main)
                    {
                        onResult(result)
                    }
                }
        }
    }

    class None
}