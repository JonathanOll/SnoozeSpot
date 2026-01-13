package iut.fauryollivier.snoozespot.api.enums

enum class StoredFileType(val value: Int) {
    UNKNOWN(0),

    IMAGE(1),
    VIDEO(2),
    AUDIO(3),
    DOCUMENT(4),

    OTHER(255);

    companion object {
        val ALL_VALUES = StoredFileType.entries.toTypedArray()
        fun fromInt(value: Int) = ALL_VALUES.firstOrNull { it.value == value }

        fun getPathForType(type: StoredFileType): String {
            return when (type) {
                UNKNOWN -> "unknown"
                IMAGE -> "images"
                VIDEO -> "videos"
                AUDIO -> "audios"
                DOCUMENT -> "documents"
                OTHER -> "others"
            }
        }
    }
}