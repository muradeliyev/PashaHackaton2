package app.pasha.hackaton.core.storage

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserRepository {
    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    fun updateUserName(name: String) {
        _userName.value = name
    }
}
