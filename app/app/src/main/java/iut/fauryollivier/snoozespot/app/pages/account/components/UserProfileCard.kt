package iut.fauryollivier.snoozespot.app.pages.account.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO

@Composable
fun UserProfileCard(user: UserDTO) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar
        Image(
            painter = painterResource(R.drawable.lobster),
            contentDescription = "Avatar de ${user.username}",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nom
        Text(
            text = user.username,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Boutons
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { /* Action suivre */ }) {
                Icon(
                    Icons.Default.PersonAdd,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    stringResource(R.string.follow),
                    color = Color.White
                )
            }
            Button(onClick = { /* Action partager */ }) {
                Icon(
                    Icons.Default.Share,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    stringResource(R.string.share),
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Statistiques
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 2
        ) {
            StatItem(label = "Siestes", value = 1)
            StatItem(label = "Snooze Point", value = 2)
            StatItem(label = "Co-snoozer", value = 3)
            StatItem(label = "Snooze Spot", value = 4)
        }
    }
}

@Composable
fun StatItem(label: String, value: Int) {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(35.dp))
            .background(Color.LightGray)
            .padding(16.dp)
    ) {
        Text(text = value.toString(), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = label)
    }
}