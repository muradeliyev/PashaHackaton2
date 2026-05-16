package app.pasha.hackaton.core.error

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

interface ErrorReporter {
    val errorState: State<String?>
    fun reportError(message: String)
    fun clearError()
}

class ErrorReporterImpl : ErrorReporter {
    private val _errorState = mutableStateOf<String?>(null)
    override val errorState: State<String?> = _errorState

    override fun reportError(message: String) {
        _errorState.value = message
    }

    override fun clearError() {
        _errorState.value = null
    }
}
