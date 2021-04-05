
package com.dawn.common.extensions


import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.dawn.common.error.ErrorEntity

fun <T : Any, L : LiveData<T>> AppCompatActivity.observe(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))
fun <L : LiveData<ErrorEntity>> AppCompatActivity.failure(liveData: L, body: (ErrorEntity?) -> Unit) =
        liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, body: (T) -> Unit) =
        liveData.observe(this, Observer(body))

fun <T : Any, L : LiveData<T>> Fragment.removeObserver(liveData: L) =
        liveData.removeObservers(this)
@Suppress("unused")
fun <L : LiveData<ErrorEntity>> Fragment.fault(liveData: L, body: (ErrorEntity?) -> Unit) =
        liveData.observe(viewLifecycleOwner, Observer(body))