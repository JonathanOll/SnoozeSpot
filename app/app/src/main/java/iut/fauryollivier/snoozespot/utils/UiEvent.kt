package iut.fauryollivier.snoozespot.utils

sealed class UiEvent {
    data class ShowToast(val stringId: Int) : UiEvent()
}

