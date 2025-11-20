package iut.fauryollivier.snoozespot.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LocalStorage(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("localStorage")

        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
        private val USER_KEY = stringPreferencesKey("user")

        private val gson = Gson()
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

    fun startObserving(scope: CoroutineScope) {
        scope.launch {
            getAuthToken.collect {
                TokenHolder.token = it
            }
        }
    }

    val getUser: Flow<UserDTO?> = context.dataStore.data.map {
        it[USER_KEY]?.let { json ->
            gson.fromJson(json, UserDTO::class.java)
        }
    }

    suspend fun saveUser(user: UserDTO?) {
        context.dataStore.edit {
            if (user != null)
                it[USER_KEY] = gson.toJson(user)
            else
                it.remove(USER_KEY)
        }
    }
}

object TokenHolder {
    var token: String? = null
}