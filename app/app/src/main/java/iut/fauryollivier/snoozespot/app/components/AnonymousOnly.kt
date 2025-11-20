package iut.fauryollivier.snoozespot.app.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import iut.fauryollivier.snoozespot.datastore.LocalStorage

@Composable
fun AnonymousOnly(content: @Composable () -> Unit) {
    val token by LocalStorage(LocalContext.current).getAuthToken.collectAsState(null)
    if (token == null)
        content()
}