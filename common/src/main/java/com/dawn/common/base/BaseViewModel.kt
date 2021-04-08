package com.dawn.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dawn.common.error.ErrorEntity
import com.dawn.common.mvi.IModel
import com.dawn.common.mvi.ViewAction
import com.dawn.common.mvi.ViewIntent
import com.dawn.common.mvi.ViewState
import com.dawn.common.singleLiveData.SingleLiveEventMutableLiveData

abstract class BaseViewModel<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState> :
    ViewModel(), IModel<STATE, INTENT> {


    //region Live Data
    var errorEntity: SingleLiveEventMutableLiveData<ErrorEntity> = SingleLiveEventMutableLiveData()
    protected val mState = MutableLiveData<STATE>()
    override val state: LiveData<STATE>
        get() {
            return mState
        }
    //endregion

    final override fun dispatchIntent(intent: INTENT) {
        handleAction(intentToAction(intent))
    }

    abstract fun intentToAction(intent: INTENT): ACTION
    abstract fun handleAction(action: ACTION)

    protected fun handleFailure(errorEntity: ErrorEntity) {
        this.errorEntity.value = errorEntity
    }
}