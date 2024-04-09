package net.bankingapp.ui.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

expect abstract class BaseViewModel<E : Event, S : UiState, A : Action>() {

    val coroutineScope: CoroutineScope
    val uiState: Flow<S>
    val action: Flow<A>
    abstract fun handleEvent(event: E)
    fun setUiState(reduce: S.() -> S)
    fun sendAction(builder: () -> A)
    fun setInitialState(state: S)
}