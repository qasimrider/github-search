package com.dawn.common.singleLiveData

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dawn.common.extensions.TAG
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val pending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {


        super.observe(owner, Observer<T> { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })

        if (hasActiveObservers()) {
            Log.w(TAG(), "single observer is notified of change ")
        }
    }

    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

}
