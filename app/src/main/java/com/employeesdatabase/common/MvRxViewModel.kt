package com.employeesdatabase.common

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Success
import com.employeesdatabase.common.useCase.NoParameterUseCase
import com.employeesdatabase.common.useCase.UseCase
import kotlinx.coroutines.SupervisorJob

abstract class MvRxViewModel<State : MvRxState>(
    private val initialState: State
) : BaseMvRxViewModel<State>(initialState, debugMode = BuildConfig.DEBUG) {

    private val viewModelJob = SupervisorJob()

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }

    suspend fun <Type : Any, Param> UseCase<Type, Param>.execute(
        params: Param,
        stateReducer: State.(Async<Type>) -> State
    ) {
        setState { stateReducer(Loading()) }
        invoke(
            params,
            onSuccess = { setState { stateReducer(Success(it)) } },
            onFailure = { setState { stateReducer(Fail(it)) } }
        )
    }

    suspend fun <Type : Any, Type2, Param> UseCase<Type, Param>.execute(
        params: Param,
        mapper: ((Type) -> Type2),
        stateReducer: State.(Async<Type2>) -> State
    ) {
        setState { stateReducer(Loading()) }
        invoke(
            params,
            onSuccess = { setState { stateReducer(Success(mapper(it))) } },
            onFailure = { setState { stateReducer(Fail(it)) } }
        )
    }

    suspend fun <Type : Any, Type2> NoParameterUseCase<Type>.execute(
        mapper: ((Type) -> Type2),
        stateReducer: State.(Async<Type2>) -> State
    ) {
        setState { stateReducer(Loading()) }
        invoke(
            onSuccess = { setState { stateReducer(Success(mapper(it))) } },
            onFailure = { setState { stateReducer(Fail(it)) } }
        )
    }

    suspend fun <Type : Any> NoParameterUseCase<Type>.execute(
        stateReducer: State.(Async<Type>) -> State
    ) {
        setState { stateReducer(Loading()) }
        invoke(
            onSuccess = { setState { stateReducer(Success(it)) } },
            onFailure = { setState { stateReducer(Fail(it)) } }
        )
    }
}
