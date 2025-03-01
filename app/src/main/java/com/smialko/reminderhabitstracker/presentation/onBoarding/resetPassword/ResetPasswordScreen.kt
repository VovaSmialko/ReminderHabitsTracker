package com.smialko.reminderhabitstracker.presentation.onBoarding.resetPassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.presentation.onBoarding.AuthViewModel
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ForgotPasswordHeadingTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ImageComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ReminderTextField
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.TextInfoComponent
import com.smialko.reminderhabitstracker.ui.theme.BrandColor

@Composable
fun ResetPasswordScreen(
    resetPasswordViewModel: ResetPasswordViewModel = hiltViewModel(),
    navController: NavHostController) {

    val password = resetPasswordViewModel.password

    var isShowPassword by remember {
        mutableStateOf(false)
    }
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column {
            ImageComponent(image = R.drawable.new_reset_password_svg)
            Spacer(modifier = Modifier.height(10.dp))
            ForgotPasswordHeadingTextComponent(action = "Reset")
            TextInfoComponent(
                textVal = stringResource(R.string.reset_password_text)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                ReminderTextField(
                    value = password.value,
                    onValueChange = {
                        resetPasswordViewModel.updatePassword(it)
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
                    visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(15.dp))
                ReminderTextField(
                    value = password.value,
                    onValueChange = {
                        resetPasswordViewModel.updatePassword(it)
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
                    visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
                )
            }
            SubmitResetActionButton(labelVal = "Submit", navController = navController)
        }
    }
}


@Composable
fun SubmitResetActionButton(labelVal: String, navController: NavHostController) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Text(
            text = labelVal,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.clickable {
                if (labelVal == "Submit") {
                    navController.navigate("ResetPassword")
                }
            }
        )
    }
}