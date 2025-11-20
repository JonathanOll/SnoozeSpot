package iut.fauryollivier.snoozespot.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalStorage(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("authToken")
        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    val getAuthToken: Flow<String?> = context.dataStore.data.map {
        it[AUTH_TOKEN_KEY]
    }

    suspend fun saveAuthToken(token: String?) {
        context.dataStore.edit {
            if (token != null)
                it[AUTH_TOKEN_KEY] = token
            else
                it.remove(AUTH_TOKEN_KEY)
        }
    }
}
