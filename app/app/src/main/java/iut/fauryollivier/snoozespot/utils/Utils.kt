package iut.fauryollivier.snoozespot.utils

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.UUID

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

fun buildFilePart(
    context: Context,
    uriString: String
): MultipartBody.Part {
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

    return MultipartBody.Part.createFormData(
        name = "file",
        filename = fileName,
        body = requestBody
    )
}

fun getMimeType(context: Context, uri: Uri): String {
    return context.contentResolver.getType(uri) ?: "application/octet-stream"
}

suspend fun saveImageToGallery(context: Context, uri: String): String =
    withContext(Dispatchers.IO) {

        val fileName = "${UUID.randomUUID()}.jpg"

        val inputStream: InputStream =
            if (uri.startsWith("http://") || uri.startsWith("https://")) {
                URL(uri).openStream()
            } else {
                context.contentResolver.openInputStream(uri.toUri())
                    ?: throw IllegalArgumentException("Impossible d'ouvrir l'URI : $uri")
            }

        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/SnoozeSpot")
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }

        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val imageUri = context.contentResolver.insert(collection, values)
            ?: throw IOException("Impossible d'insérer dans MediaStore")

        context.contentResolver.openOutputStream(imageUri).use { output ->
            inputStream.copyTo(output!!)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, 0)
            context.contentResolver.update(imageUri, values, null, null)
        }

        fileName
    }




