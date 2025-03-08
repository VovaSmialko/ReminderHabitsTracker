package com.smialko.reminderhabitstracker.presentation.onBoarding.forgotPassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.navigation.Screen
import com.smialko.reminderhabitstracker.presentation.onBoarding.AuthViewModel
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ForgotPasswordHeadingTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ImageComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ReminderTextField
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.TextInfoComponent
import com.smialko.reminderhabitstracker.ui.theme.BrandColor
import com.smialko.reminderhabitstracker.utils.Response

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {

    var email by rememberSaveable { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                ImageComponent(image = R.drawable.modern_forgot_password)
                Spacer(modifier = Modifier.height(10.dp))
                ForgotPasswordHeadingTextComponent(action = "Forgot")
                TextInfoComponent(
                    textVal = stringResource(R.string.dont_worry)
                )
                Spacer(modifier = Modifier.height(20.dp))
                ReminderTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.email_field),
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    leadingIcon = {
                        Icon(painterResource(id = R.drawable.at_symbol), contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true
                )

                Button(
                    onClick = { authViewModel.resetPassword(email) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BrandColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.sumbit),
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                }
                val sendResetPasswordState by authViewModel.sendResetPassword.collectAsState()
                when (sendResetPasswordState) {
                    is Response.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = BrandColor)
                        }
                    }

                    is Response.Success -> {
                        val response = sendResetPasswordState as Response.Success<Boolean>
                        if (response.data) {
                            LaunchedEffect(key1 = true) {
                                snackbarHostState.showSnackbar(
                                    message = "Reset link has been sent to your email"
                                )
                                navController.navigate(Screen.Login.route)
                            }
                        }
                    }

                    is Response.Error -> {
                        val response = sendResetPasswordState as Response.Error
                        LaunchedEffect(sendResetPasswordState) {
                            snackbarHostState.showSnackbar(message = response.message)
                        }
                    }
                }
            }
        }
    }
}
