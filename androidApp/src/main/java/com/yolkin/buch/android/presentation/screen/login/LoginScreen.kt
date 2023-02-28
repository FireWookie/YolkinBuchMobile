package com.yolkin.buch.android.presentation.screen.login

import SignInViewModel
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.yolkin.buch.MR
import com.yolkin.buch.android.R
import com.yolkin.buch.android.presentation.navigation.NavigationTree
import com.yolkin.buch.android.ui.components.DefaultButton
import com.yolkin.buch.android.ui.components.DefaultTextField
import dev.icerock.moko.mvvm.flow.compose.observeAsActions
import org.koin.androidx.compose.getViewModel
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun LoginScreen() {
    val rootController = LocalRootController.current
    val viewModel: SignInViewModel = getViewModel()
    val context = LocalContext.current
    val login by viewModel.login.collectAsState()
    val password by viewModel.password.collectAsState()
    val isLoginButtonEnabled: Boolean by viewModel.isButtonEnabled.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(id = R.drawable.logotype),
            contentDescription = null,
            modifier = Modifier.size(90.dp)
        )
        Spacer(modifier = Modifier.height(120.dp))
        Text(
            text = stringResource(id = MR.strings.auth.resourceId),
            fontSize = 20.sp,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(30.dp))
        DefaultTextField(
            value = login,
            placeholderText = stringResource(id = MR.strings.login.resourceId),
            modifier = Modifier
        ) {
            viewModel.login.value = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        DefaultTextField(
            value = password,
            placeholderText = stringResource(id = MR.strings.password.resourceId),
            modifier = Modifier
        ) {
            viewModel.password.value = it
        }
        Spacer(modifier = Modifier.height(50.dp))
        DefaultButton(
            textButton = stringResource(id = MR.strings.sign_in.resourceId),
            enabled = isLoginButtonEnabled
        ) {
            viewModel.loginPressed()
        }

        viewModel.actions.observeAsActions { action ->
            when(action) {
                SignInViewModel.Action.LoginSuccess -> {
                    rootController.findRootController().present(NavigationTree.Main.Dashboard.name)
                }
                SignInViewModel.Action.NetworkException -> {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}