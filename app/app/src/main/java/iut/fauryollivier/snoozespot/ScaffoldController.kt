package iut.fauryollivier.snoozespot

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

class ScaffoldController (
    val showBottomBar: MutableState<Boolean>,
    val topBar: MutableState<@Composable () -> Unit>
)