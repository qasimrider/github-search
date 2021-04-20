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


/**
 * This class is base class for all the view models to use this class you have to provide below properties
 * [INTENT] Intention of view model to be used
 * [ACTION] Action taken on the basis  of intent
 * [STATE] what are the possible state of the views using this view model
 */
abstract class BaseViewModel<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState> :
    ViewModel(), IModel<STATE, INTENT> {


    //region Live Data
    /**
     * Handle the errors of the view model
     */
    var errorEntity: SingleLiveEventMutableLiveData<ErrorEntity> = SingleLiveEventMutableLiveData()

    protected val mState = MutableLiveData<STATE>()
    override val state: LiveData<STATE>
        get() {
            return mState
        }
    //endregion

    //region State Management
    final override fun dispatchIntent(intent: INTENT) {
        handleAction(intentToAction(intent))
    }

    abstract fun intentToAction(intent: INTENT): ACTION

    abstract fun handleAction(action: ACTION)
    //endregion

    //region Failure Handling
    protected fun handleFailure(errorEntity: ErrorEntity) {
        this.errorEntity.value = errorEntity
    }
    //endregion
}