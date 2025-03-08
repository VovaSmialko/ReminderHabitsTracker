package com.smialko.reminderhabitstracker.presentation.onBoarding.signUp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.navigation.Screen
import com.smialko.reminderhabitstracker.navigation.rememberNavigationState
import com.smialko.reminderhabitstracker.presentation.onBoarding.AuthViewModel
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.HeadingTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ImageComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ReminderTextField
import com.smialko.reminderhabitstracker.ui.theme.BrandColor
import com.smialko.reminderhabitstracker.utils.Response
import com.smialko.reminderhabitstracker.utils.Toast
import com.smialko.reminderhabitstracker.utils.registerValidation.RegisterValidation


@Composable
fun SignupScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }

    var isShowPassword by remember {
        mutableStateOf(false)
    }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(key1 = true) {
        authViewModel.validation.collect { validationState ->

            emailError = if (validationState.email is RegisterValidation.Failed) {
                validationState.email.message
            } else {
                null
            }

            passwordError = if (validationState.password is RegisterValidation.Failed) {
                validationState.password.message
            } else {
                null
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .imePadding(),
        color = Color.White
    ) {
        Column() {
            ImageComponent(image = R.drawable.signup_svg)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingTextComponent(heading = "Sign Up")
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                ReminderTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .imePadding(),
                    value = email,
                    onValueChange = {
                        email = it
                    },
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
                    singleLine = true,
                    isError = emailError != null,
                    supportingText = {
                        emailError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                    }
                )
                ReminderTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = {
                        password = it
                    },
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
                    visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    isError = passwordError != null,
                    supportingText = {
                        passwordError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                    },
                )
                ReminderTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.full_name),
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    leadingIcon = {
                        Icon(painterResource(id = R.drawable.lockperson), contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                SignupTermsAndPrivacyText()
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp),
                            onClick = {
                                authViewModel.signUp(
                                    email = email,
                                    password = password,
                                    fullName = fullName
                                )
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BrandColor
                            ),
                        ) {
                            Text(
                                text = stringResource(R.string.register_button),
                                color = Color.White,
                                fontSize = 18.sp,
                            )
                        }
                        SnackbarHost(hostState = snackbarHostState)
                        val singUpState by authViewModel.signUpState.collectAsState()
                        when (singUpState) {
                            is Response.Loading -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(color = BrandColor)
                                }
                            }

                            is Response.Success -> {
                                val response = singUpState as Response.Success<Boolean>
                                if (response.data) {
                                    LaunchedEffect(key1 = true) {
                                        navController.navigate(Screen.ListTasks.route) {
                                            popUpTo(Screen.Login.route)
                                        }
                                    }
                                }
                            }

                            is Response.Error -> {
                                val response = singUpState as Response.Error
                                LaunchedEffect(singUpState) {
                                    snackbarHostState.showSnackbar(message = response.message)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        BottomSignupTextComponent(navController)
                    }
                }
            }
        }
    }
}

