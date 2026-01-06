package iut.fauryollivier.snoozespot.app.pages.account

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iut.fauryollivier.snoozespot.datastore.LocalStorage
import iut.fauryollivier.snoozespot.repositories.UsersRepository
import kotlinx.coroutines.launch

class AccountViewModel: ViewModel() {

    fun logout(context: Context) {
        viewModelScope.launch {
            LocalStorage(context).saveAuthToken(null)
            LocalStorage(context).saveUser(null)
            // TODO envoyer une requête à l'api pour invalider le token
        }
    }

    fun changeProfilePic(context: Context, uri: Uri) {
        viewModelScope.launch {
            val result = UsersRepository.changeProfilePic(context, uri.toString())
        }
    }

}