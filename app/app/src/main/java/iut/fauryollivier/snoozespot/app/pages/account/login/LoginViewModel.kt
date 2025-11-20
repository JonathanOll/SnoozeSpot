package iut.fauryollivier.snoozespot.app.pages.account.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.UserAuthRequest
import iut.fauryollivier.snoozespot.datastore.LocalStorage
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    fun onUsernameChanged(new: String) {
        username = new
    }

    fun onPasswordChanged(new: String) {
        password = new
    }

    fun login(context: Context, navigator: DestinationsNavigator) {
        viewModelScope.launch {
            val response = NetworkDataSource.api.authLoginPost(UserAuthRequest(username, password))
            if (response.isSuccessful) {
                val token = response.body()!!.accessToken
                LocalStorage(context).saveAuthToken(token)
                navigator.navigateUp()
            } else {
                Toast.makeText(context, context.getString(R.string.couldNotLogin), Toast.LENGTH_LONG).show()
            }
        }
    }

}