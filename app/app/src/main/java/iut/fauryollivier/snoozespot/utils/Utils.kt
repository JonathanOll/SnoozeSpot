package iut.fauryollivier.snoozespot.utils

import android.content.Context
import android.net.Uri
import okhttp3.MultipartBody
import androidx.core.net.toUri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

fun buildFileParts(
    context: Context,
    uriStrings: List<String>
): List<MultipartBody.Part> {

    return uriStrings.map { uriString ->
        val uri = uriString.toUri()

        val inputStream = context.contentResolver.openInputStream(uri)
            ?: throw IllegalArgumentException("Cannot open URI: $uri")

        val bytes = inputStream.readBytes()

        val rawName = uri.lastPathSegment ?: "file"

        val mimeType = getMimeType(context, uri)
        val extension = android.webkit.MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(mimeType)
            ?: rawName.substringAfterLast('.', "")

        val fileName = if (rawName.contains(".")) {
            rawName
        } else {
            "$rawName.$extension"
        }

        val requestBody = bytes.toRequestBody(mimeType.toMediaType())

        MultipartBody.Part.createFormData(
            name = "file",
            filename = fileName,
            body = requestBody
        )
    }
}

fun getMimeType(context: Context, uri: Uri): String {
    return context.contentResolver.getType(uri) ?: "application/octet-stream"
}
