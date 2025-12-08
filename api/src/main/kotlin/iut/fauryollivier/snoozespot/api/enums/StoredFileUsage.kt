package iut.fauryollivier.snoozespot.api.enums

enum class StoredFileUsage(val value: Int) {
    UNKNOW(0),

    SPOT_ATTRIBUTES_ICON(1),

    PROFILE_PICTURE(16),
    SPOT_MEDIA(17),
    POST_MEDIA(18),
    COMMENT_MEDIA(19),
    OTHER(255);

    companion object {
        val ALL_VALUES = entries.toTypedArray()
        fun fromInt(value: Int) = ALL_VALUES.firstOrNull { it.value == value }

        fun getPathForUsage(usage: StoredFileUsage): String {
            return when(usage) {
                UNKNOW -> "unknown"
                SPOT_ATTRIBUTES_ICON -> "spot_attributes_icons"
                PROFILE_PICTURE -> "profile_pictures"
                SPOT_MEDIA -> "spot_media"
                POST_MEDIA -> "post_media"
                COMMENT_MEDIA -> "comment_media"
                OTHER -> "others"
            }
        }
    }
}