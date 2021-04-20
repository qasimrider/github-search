package com.dawn.common.mvi

import androidx.lifecycle.LiveData


/**
 * This class should implemented by all the Viewmodels, sole purpose of this is to provide handles
 * to ViewModels to play with MVI(Model-View-Intent)
 */
interface IModel<STATE, INTENT> {

    /**
     * This holdsthe state of the viewModel
     */
    val state: LiveData<STATE>

    /**
     * This method is used by fragment to dispatch the initial intent of the view model
     */
    fun dispatchIntent(intent: INTENT)
}