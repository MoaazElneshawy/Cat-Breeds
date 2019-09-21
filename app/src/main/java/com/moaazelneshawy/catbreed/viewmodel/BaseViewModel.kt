package com.moaazelneshawy.catbreed.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(application: Application) : AndroidViewModel(application) {


    private var mNavigator: WeakReference<N>? = null

    var navigator: N?
        get() = mNavigator!!.get()
        set(navigator) {
            this.mNavigator = WeakReference<N>(navigator)
        }

    private var mIsLoading: Boolean = false
    var isLoading: Boolean
        get() = mIsLoading
        set(value) {
            this.mIsLoading = value
        }

    private var mIsFail: Boolean = false
    var isFailed: Boolean
        get() = mIsFail
        set(value) {
            this.mIsFail = value
        }


}