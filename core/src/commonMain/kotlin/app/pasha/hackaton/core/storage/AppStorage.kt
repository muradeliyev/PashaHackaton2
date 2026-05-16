package app.pasha.hackaton.core.storage

import kotlinx.coroutines.flow.Flow

interface AppStorage {
    suspend fun saveAccessToken(token: String)
    fun getAccessToken(): Flow<String?>
    suspend fun clearAccessToken()

    suspend fun savePreference(key: String, value: String)
    fun getPreference(key: String): Flow<String?>
    suspend fun clearAll()
}
