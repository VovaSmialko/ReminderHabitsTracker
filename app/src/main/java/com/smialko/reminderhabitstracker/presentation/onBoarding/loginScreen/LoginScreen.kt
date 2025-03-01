package com.smialko.reminderhabitstracker.presentation.onBoarding.loginScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.navigation.Screen
import com.smialko.reminderhabitstracker.presentation.onBoarding.AuthViewModel
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.HeadingTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ImageComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ReminderTextField
import com.smialko.reminderhabitstracker.ui.theme.BrandColor
import com.smialko.reminderhabitstracker.utils.Response
import com.smialko.reminderhabitstracker.utils.Toast

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val email = authViewModel.email
    val password = authViewModel.password

    var isShowPassword by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .imePadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            ImageComponent(image = R.drawable.modern_habits_svg)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingTextComponent(heading = "Login")
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                ReminderTextField(
                    value = email.value,
                    onValueChange = {
                        authViewModel.updateEmail(it)
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
                ReminderTextField(
                    value = password.value,
                    onValueChange = {
                        authViewModel.updatePassword(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.password_field),
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    leadingIcon = {
                        Icon(painterResource(id = R.drawable.lock), contentDescription = null)
                    },
                    trailingIcon = {
                        val description = if (isShowPassword) "Show Password" else "Hide Password"
                        val iconImage =
                            if (isShowPassword) R.drawable.eyeclosedfill else R.drawable.eye_closed
                        IconButton(onClick = {
                            isShowPassword = !isShowPassword
                        }) {
                            Icon(
                                painter = painterResource(id = iconImage),
                                contentDescription = description,
                                tint = MaterialTheme.colorScheme.primary,
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    singleLine = true,
                    visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ForgotPasswordTextComponent(navController)
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        BottomComponent(
                            onLoginClick = {
                                authViewModel.signIn(
                                    email = email.value,
                                    password = password.value
                                )
                            },
                            navController
                        )
                        when (val response = authViewModel.signInState.value) {
                            is Response.Loading -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(color = BrandColor)
                                }
                            }

                            is Response.Success -> {
                                if (response.data) {
                                    LaunchedEffect(key1 = true) {
                                        navController.navigate(Screen.ListTasks.route) {
                                            popUpTo(Screen.Login.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                }
                            }

                            is Response.Error -> {
                                Toast(message = response.message)
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        BottomLoginTextComponent(
                            initialText = "Haven't we seen you around here before? ",
                            action = "Join our family!",
                            navController
                        )
                    }
                }
            }
        }
    }
}
