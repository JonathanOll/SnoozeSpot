package iut.fauryollivier.snoozespot.app.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.utils.saveImageToGallery
import kotlinx.coroutines.launch


@Composable
fun ExpandableImageWithDownload(
    imageUri: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val downloadedText = stringResource(R.string.image_downloaded)

    ExpandableImage(
        imageUri = imageUri,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(48.dp)
                .clickable {
                    scope.launch {
                        saveImageToGallery(context, imageUri)
                        Toast.makeText(context, downloadedText, Toast.LENGTH_SHORT).show()
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Filled.Download, contentDescription = "Download", tint = Color.White)
        }
    }
}

@Composable
fun ExpandableImage(
    imageUri: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    content: @Composable () -> Unit = {}
) {
    val expended = remember { mutableStateOf(false) }

    AsyncImage(
        model = imageUri,
        contentDescription = contentDescription,
        modifier = modifier.clickable { expended.value = true },
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        error = painterResource(id = R.drawable.lobster),
        contentScale = contentScale
    )

    if (expended.value)
        ImagePreviewDialog(
            imageUri = imageUri,
            onDismiss = {
                expended.value = false
            },
            content = content
        )
}

@Composable
fun ImagePreviewDialog(
    imageUri: String,
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .blur(20.dp)
                .clickable { onDismiss() }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(0.7f)
                    .align(Alignment.Center),
                contentScale = ContentScale.Fit
            )

            content()
        }
    }
}