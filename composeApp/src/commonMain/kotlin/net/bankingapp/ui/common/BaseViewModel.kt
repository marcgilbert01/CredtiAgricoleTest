package net.bankingapp.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

abstract class BaseViewModel<E : Event, S : UiState, A : Action> : ViewModel(), KoinComponent {


    private val _uiState: MutableStateFlow<S?> = MutableStateFlow(null)
    val uiState: Flow<S> = _uiState.filterNotNull()

    private val _action: Channel<A> = Channel()
    val action by lazy { _action.receiveAsFlow() }

    abstract fun handleEvent(event: E)

    fun setInitialState(state: S) {
        _uiState.value = state
    }
    fun setUiState(reduce: S.() -> S) {
        _uiState.update {
            _uiState.value?.reduce()
        }
    }
    fun sendAction(builder: () -> A) {
        val effectValue = builder()
        viewModelScope.launch {
            _action.send(effectValue)
        }
    }
}