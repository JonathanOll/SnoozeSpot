package iut.fauryollivier.snoozespot.app.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import iut.fauryollivier.snoozespot.R

@Composable
fun ImagePicker(list: SnapshotStateList<Uri> ,modifier: Modifier = Modifier) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris: List<Uri> ->
        list.addAll(uris.filter { it !in list })
    }
    val selected = remember { mutableStateOf<Uri?>(null) }
    var confirmDelete = remember { mutableStateOf(false) }

    LazyRow(modifier = modifier
        .fillMaxWidth()
        .height(120.dp)
        .padding(10.dp)) {
        items(list) { uri->
            AsyncImage(
                model = uri,
                contentDescription = null,
                modifier = Modifier
                    .size(110.dp)
                    .clickable { selected.value = uri },
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.width(10.dp))
        }
        item {
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .border(1.dp, Color.DarkGray, RoundedCornerShape(4.dp))
                    .clickable {
                        launcher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text("+", fontSize = 32.sp, color = Color.DarkGray)
            }
        }
    }

    if (selected.value != null) {
        ImagePreviewDialog(selected.value!!, {
            confirmDelete.value = true
            }, {
                confirmDelete.value = false
                selected.value = null
        })

        if (confirmDelete.value)
            DeleteImageDialog({
                list.remove(selected.value)
                selected.value = null
                confirmDelete.value = false
            }, {
                confirmDelete.value = false
            })
    }
}

@Composable
fun ImagePreviewDialog(
    imageUri: Uri,
    onDelete: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(
        onDismissRequest = { onCancel() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.25f))
                .blur(20.dp)
                .clickable { onCancel() }
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

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .size(48.dp)
                    .clickable { onCancel() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Retour", tint = Color.White)
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(48.dp)
                    .clickable { onDelete() },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Supprimer", tint = Color.White)
            }
        }
    }
}

@Composable
fun DeleteImageDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(R.string.confirm_title)) },
        text = { Text(stringResource(R.string.confirm_pic_delete))},
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(R.string.cancel))
            }
        }
    )

}