package com.dawn.common.base

import com.dawn.common.error.ErrorEntity
import com.dawn.common.functional.Either
import kotlinx.coroutines.*


/**
 * Provide the Implementation of the Async calls to implemented classes
 * [Type] is defined as the response of the caller function
 * [Params] are the parameters used to pass to function
 */
abstract class BaseUseCase<out Type, in Params> {

    /**
     * this should be called from Coroutine Context and implemented by all Use cases
     * */
    abstract suspend fun run(param: Params): Either<ErrorEntity, Type>


    /**
     * This is an operator function which is invoke from the viewmodel and this implements Coroutines
     * to make the job done on IO Threads
     */
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

    /**
     * When our Network Call don't need any parameter pass this class
     */
    class None
}

// use this way if you need the result of the operation in a concurrent way
//viewModelScope.launch {
//    val async = CoroutineScope(Dispatchers.IO)
//        .async {
//            run(params)
//        }
//
//    onResult(async.await())
//}