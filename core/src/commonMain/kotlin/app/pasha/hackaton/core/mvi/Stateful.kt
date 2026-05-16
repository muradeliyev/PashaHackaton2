package app.pasha.hackaton.core.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


interface Stateful<S> {

    val state: StateFlow<S>

    fun updateState(block: (S) -> S)
}


private class StatefulViewModel<S>(initialValue: S) : Stateful<S> {

    private val _state = MutableStateFlow(initialValue)
    override val state: StateFlow<S> = _state.asStateFlow()


    override fun updateState(block: (S) -> S) {
        _state.update(block)
    }
}


fun <S> statefulViewModel(initialValue: S): Stateful<S> = StatefulViewModel(initialValue)