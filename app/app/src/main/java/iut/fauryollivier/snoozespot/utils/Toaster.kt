package iut.fauryollivier.snoozespot.utils

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class Toaster {
    companion object {
        var instance: Toaster = Toaster()
    }

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    suspend fun toast(stringId: Int) {
        _events.emit(UiEvent.ShowToast(stringId))
    }
}