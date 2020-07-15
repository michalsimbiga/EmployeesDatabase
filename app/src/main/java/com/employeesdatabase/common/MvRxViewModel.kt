package com.employeesdatabase.common

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.MvRxState
import kotlinx.coroutines.SupervisorJob

abstract class MvRxViewModel<State : MvRxState>(
    private val initialState: State
) : BaseMvRxViewModel<State>(initialState, debugMode = BuildConfig.DEBUG) {

    private val viewModelJob = SupervisorJob()
//    protected val backgroundScope = CoroutineScope(viewModelJob + Dispatchers.Default)

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }
}