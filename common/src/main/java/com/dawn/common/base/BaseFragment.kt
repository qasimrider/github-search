package com.dawn.common.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.dawn.common.R
import com.dawn.common.error.ErrorEntity
import com.dawn.common.error.ErrorEntity.*
import com.dawn.common.extensions.invisible
import com.dawn.common.extensions.visible
import com.dawn.common.mvi.ViewAction
import com.dawn.common.mvi.ViewIntent
import com.dawn.common.mvi.ViewState


abstract class BaseFragment<INTENT : ViewIntent, ACTION : ViewAction, STATE : ViewState> :
    Fragment() {

    //region Props
    protected var binding: ViewDataBinding? = null
    protected var progressBar: ProgressBar? = null
    //endregion

    //region Progress bar
    protected fun showProgress(showProgress: Boolean, lockScreen: Boolean) {
        if (showProgress) {
            progressBar?.visible()

        } else {
            progressBar?.invisible()

        }
        if (lockScreen) {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            activity?.window?.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    //endregion

    //region Toast Message
    open fun showMessage(messageBody: String) {
        Toast.makeText(context, messageBody, Toast.LENGTH_LONG).show()
    }
    //endregion

    //region Error handling
    protected fun handleFailure(errorEntity: ErrorEntity?) {
        showProgress(false, lockScreen = false)
        when (errorEntity) {
            is ErrorEntity.AuthError -> showMessage(
                getString(R.string.failure_authorization)
            )
            is ErrorEntity.Forbidden -> showMessage(getString(R.string.failure_forbidden))
            is ErrorEntity.InternalServerError -> showMessage(
                getString(R.string.failure_forbidden)
            )
            is BadRequest -> showMessage(getString(R.string.failure_bad_request))
            is NotFound -> showMessage(getString(R.string.failure_not_found))
            is AndroidError -> showMessage(getString(R.string.failure_android_error))
            is UnSupportedMediaType -> showMessage(getString(R.string.failure_unsupportedmedia))
            is MalFormedJson -> showMessage(getString(R.string.failure_malformedJson))
            is IllegalStateException -> showMessage(getString(R.string.failure_malformedJson))
            is JsonSyntaxException -> showMessage(getString(R.string.failure_malformedJson))
            is UniqueConstraintError -> showMessage(getString(R.string.failure_unique_constraint))
            is ServerError -> showMessage(getString(R.string.failure_server_error))
            is NetworkConnection -> showMessage(getString(R.string.failure_network_connection))
        }
    }
    //endregion

    //region Abstracts
    protected abstract fun layoutResourceId(): Int
    protected open fun attachListeners() {}
    abstract fun getViewModel(): BaseViewModel<INTENT, ACTION, STATE>

    abstract fun initialize(savedInstanceState: Bundle?)
    //endregion

    //region LifeCycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId(), container, false)
        binding!!.lifecycleOwner = viewLifecycleOwner
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize(savedInstanceState)
        attachListeners()
    }
    //endregion

    //region State Management

    fun dispatchIntent(intent: INTENT) {
        getViewModel().dispatchIntent(intent)
    }
    //endregion


}