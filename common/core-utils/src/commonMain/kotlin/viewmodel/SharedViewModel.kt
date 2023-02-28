package viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

/**
 * Base VM
 */
abstract class SharedViewModel<STATE : Any, ACTION, EVENT>(initState: STATE) : ViewModel(), KoinComponent {

    private val _viewStates = MutableStateFlow(initState)

    private val _viewActions = MutableSharedFlow<ACTION?>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    fun viewStates(): WrappedStateFlow<STATE> {
        return WrappedStateFlow(_viewStates.asStateFlow())
    }

    fun viewActions(): WrappedSharedFlow<ACTION?> {
        return WrappedSharedFlow(_viewActions.asSharedFlow())
    }

    protected var viewState: STATE
        get() = _viewStates.value
        set(value) {
            _viewStates.value = value
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    protected var viewAction: ACTION?
        get() = if (_viewActions.replayCache.isNotEmpty()) {
            _viewActions.replayCache.last()
        } else {
            null
        }
        set(value) {
            if (value != null) {
                _viewActions.tryEmit(value)
                _viewActions.resetReplayCache()
            } else {
                _viewActions.resetReplayCache()
            }
        }

    public abstract fun obtainEvent(viewEvent: EVENT)

    protected fun withViewModelScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(block = block)
    }
}