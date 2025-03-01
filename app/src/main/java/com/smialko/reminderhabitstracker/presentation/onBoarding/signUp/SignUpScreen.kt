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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.wear.compose.foundation.ExperimentalWearFoundationApi
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.navigation.Screen
import com.smialko.reminderhabitstracker.presentation.onBoarding.AuthViewModel
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.HeadingTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ImageComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ReminderTextField
import com.smialko.reminderhabitstracker.ui.theme.BrandColor
import com.smialko.reminderhabitstracker.utils.Response
import com.smialko.reminderhabitstracker.utils.Toast

@OptIn(ExperimentalWearFoundationApi::class)
@Composable
fun SignupScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val coroutineScope = rememberCoroutineScope()
    val firstName = authViewModel.firstName
    val secondName = authViewModel.secondName
    val email = authViewModel.email
    val password = authViewModel.password

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
                    value = email.value,
                    onValueChange = {
                        authViewModel.updateEmail(it)
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
                    singleLine = true
                )
                ReminderTextField(
                    value = firstName.value,
                    onValueChange = { authViewModel.updateFirstName(it) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.first_name),
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
                ReminderTextField(
                    value = secondName.value,
                    onValueChange = {
                        authViewModel.updateSecondName(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.second_name),
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
                ReminderTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password.value,
                    onValueChange = { authViewModel.updatePassword(it) },
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            SignupTermsAndPrivacyText()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    Button(
                        onClick = {
                            authViewModel.signUp(
                                email = email.value,
                                password = password.value,
                                firstName = firstName.value,
                                lastName = secondName.value
                            )
                            navController.navigate(Screen.ListTasks.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BrandColor
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 40.dp),
                    ) {
                        Text(
                            text = stringResource(R.string.register_button),
                            color = Color.White,
                            fontSize = 18.sp,
                        )
                        when (val response = authViewModel.signUpState.value) {
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
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    BottomSignupTextComponent(navController)
                }
            }
        }
    }
}

