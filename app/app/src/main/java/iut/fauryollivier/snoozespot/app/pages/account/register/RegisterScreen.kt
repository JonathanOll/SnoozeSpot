package iut.fauryollivier.snoozespot.app.pages.account.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.ScaffoldController
import iut.fauryollivier.snoozespot.app.destinations.LoginScreenDestination
import iut.fauryollivier.snoozespot.app.pages.account.login.RegisterViewModel

@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator,
    scaffoldController: ScaffoldController,
    modifier: Modifier = Modifier,
    vm: RegisterViewModel = viewModel()
) {

    val context = LocalContext.current

    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = vm.username,
            onValueChange = { vm.onUsernameChanged(it) },
            label = { Text(stringResource(R.string.username)) }
        )

        OutlinedTextField(
            value = vm.password,
            onValueChange = { vm.onPasswordChanged(it) },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = { vm.login(context, navigator) }
        ) {
            Text(stringResource(R.string.register))
        }

        Text(
            stringResource(R.string.loginInstead),
            modifier = Modifier.clickable { navigator.popBackStack(); navigator.navigate(LoginScreenDestination) }
        )
    }
}