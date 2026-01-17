package iut.fauryollivier.snoozespot.app.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import iut.fauryollivier.snoozespot.utils.Toaster
import iut.fauryollivier.snoozespot.utils.UiEvent

@Composable
fun ToasterComponent() {
    val context: Context = LocalContext.current

    LaunchedEffect(true) {
        Toaster.instance.events.collect { event ->
            when (event) {
                is UiEvent.ShowToast ->
                    Toast.makeText(context, context.getString(event.stringId), Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
}