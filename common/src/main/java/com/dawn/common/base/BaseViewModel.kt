package com.dawn.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dawn.common.error.ErrorEntity
import com.dawn.common.mvi.IModel
import com.dawn.common.mvi.ViewAction
import com.dawn.common.mvi.ViewIntent
import com.dawn.common.mvi.ViewState
import com.dawn.common.singleLiveData.SingleLiveEvent

abstract class BaseViewModel<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState>() :
    ViewModel(), IModel<STATE, INTENT> {


    var errorEntity: SingleLiveEvent<ErrorEntity> = SingleLiveEvent()
    var operationStatus: SingleLiveEvent<Operation> = SingleLiveEvent()


    protected val mState = MutableLiveData<STATE>()
    override val state: LiveData<STATE>
        get() {
            return mState
        }

    final override fun dispatchIntent(intent: INTENT) {

    }

    abstract fun intentToAction(intent: INTENT): ACTION
    abstract fun handleAction(action: ACTION)

    protected fun handleFailure(errorEntity: ErrorEntity) {
        this.errorEntity.value = errorEntity
    }

    sealed class Operation {
        object Started : Operation()
        object Completed : Operation()
    }

}