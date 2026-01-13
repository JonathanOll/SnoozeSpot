package iut.fauryollivier.snoozespot.app.pages.account.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.app.ScaffoldController
import iut.fauryollivier.snoozespot.app.destinations.RegisterScreenDestination
import iut.fauryollivier.snoozespot.datastore.LocalStorage
import iut.fauryollivier.snoozespot.utils.UiEvent

@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    scaffoldController: ScaffoldController,
    modifier: Modifier = Modifier,
    vm: LoginViewModel = viewModel()
) {
    LaunchedEffect(true) {
        scaffoldController.topBar.value = { }
        scaffoldController.showBottomBar.value = true
    }

    val context = LocalContext.current

    LaunchedEffect(true) {
        vm.events.collect { event ->
            when (event) {
                is UiEvent.ShowToast ->
                    Toast.makeText(context, context.getString(event.stringId), Toast.LENGTH_SHORT).show() } }
    }

    val username by vm.username.collectAsState()
    val password by vm.password.collectAsState()

    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { vm.onUsernameChanged(it) },
            label = { Text(stringResource(R.string.username)) }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { vm.onPasswordChanged(it) },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = { vm.login(LocalStorage(context), { navigator.navigateUp() }) }
        ) {
            Text(stringResource(R.string.login), color = Color.White)
        }

        Text(
            stringResource(R.string.register_instead),
            modifier = Modifier.clickable { navigator.popBackStack(); navigator.navigate(RegisterScreenDestination) }
        )
    }
}