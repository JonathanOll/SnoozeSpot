package iut.fauryollivier.snoozespot.utils

import iut.fauryollivier.snoozespot.R

enum class ErrorMessage(var stringId: Int) {
    UNEXPECTED_ERROR(R.string.unexpected_error),
    LOADING_ERROR(R.string.loading_error),
    COULD_NOT_FETCH_ERROR(R.string.fetch_error)
}