package net.creditagricole.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*
    The class intends to formalise and provide a common definition for the Unidirectional Data Flow UI pattern,
    to ease maintenance and avoid individual styling in the ViewModels.
    See: https://developer.android.com/topic/architecture/ui-layer#why-use-udf
 */
actual abstract class BaseViewModel<E : Event, S : UiState, A : Action> actual constructor() : ViewModel() {

    actual val coroutineScope: CoroutineScope = viewModelScope

    private val _uiState: MutableStateFlow<S?> = MutableStateFlow(null)
    actual val uiState: Flow<S> = _uiState.filterNotNull()

    private val _action: Channel<A> = Channel()
    actual val action by lazy { _action.receiveAsFlow() }

    actual abstract fun handleEvent(event: E)

    actual fun setInitialState(state: S) {
        _uiState.value = state
    }
    actual fun setUiState(reduce: S.() -> S) {
        _uiState.update {
            _uiState.value?.reduce()
        }
    }

    actual fun sendAction(builder: () -> A) {
        val effectValue = builder()
        coroutineScope.launch {
            _action.send(effectValue)
        }
    }
}