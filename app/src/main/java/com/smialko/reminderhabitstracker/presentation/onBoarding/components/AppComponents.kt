package com.smialko.reminderhabitstracker.presentation.onBoarding.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.ui.theme.BgSocial
import com.smialko.reminderhabitstracker.ui.theme.BorderColor
import com.smialko.reminderhabitstracker.ui.theme.BrandColor


@Composable
fun ImageComponent(image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp)
    )
}

@Composable
fun HeadingTextComponent(heading: String) {
    Text(
        text = heading,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 39.sp,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ForgotPasswordHeadingTextComponent(action: String) {
    Column {
        Text(
            text = action,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 39.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Password?",
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-18).dp),
            fontSize = 39.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(labelVal: String, icon: Int) {
    var textVal by remember {
        mutableStateOf("")
    }
    val typeOfKeyboard: KeyboardType = when (labelVal) {
        "email ID" -> KeyboardType.Email
        "mobile" -> KeyboardType.Phone
        else -> KeyboardType.Text
    }

    OutlinedTextField(
        value = textVal,
        onValueChange = {
            textVal = it
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BrandColor,
            unfocusedBorderColor = BorderColor,
            focusedTextColor = Color.Black,
            focusedLeadingIconColor = BrandColor,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = MaterialTheme.colorScheme.primary)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "at_symbol"
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = typeOfKeyboard,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputComponent(labelVal: String) {
    var password by remember {
        mutableStateOf("")
    }
    var isShowPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
        },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BrandColor,
            unfocusedBorderColor = BorderColor,
            focusedTextColor = Color.Black
        ),
        shape = MaterialTheme.shapes.small,
        placeholder = {
            Text(text = labelVal, color = MaterialTheme.colorScheme.primary)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.lock),
                contentDescription = "at_symbol",
                tint = MaterialTheme.colorScheme.primary
            )
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

@Composable
fun ForgotPasswordTextComponent(navController: NavHostController) {
    Text(
        text = stringResource(R.string.forgot_password),
        color = BrandColor,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.clickable {
            navController.navigate("ForgotPassword")
        }
    )
}

@Composable
fun ActionButton(labelVal: String, navController: NavHostController) {
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

@Composable
fun BottomComponent(navController: NavHostController) {
    Column {
        ActionButton(labelVal = "Continue", navController = navController)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "OR",
                modifier = Modifier.padding(10.dp),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = BgSocial
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "google icon"
                )
                Text(
                    text = "Login With Google",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun BottomLoginTextComponent(initialText: String, action: String, navController: NavHostController) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = action, annotation = action)
            append(action)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
                Log.d("BottomLoginTextComponent", "${span.item} is Clicked")
                if (span.item == "Join our coven!") {
                    navController.navigate("SignupScreen")
                }
            }
    })
}

@Composable
fun SignupTermsAndPrivacyText() {
    val initialText = "Join our coven and accept our "
    val termsNConditionText = "Terms & Conditions"
    val andText = " and "
    val privacyPolicyText = "Privacy Policy."
    val lastText = " Don't be afraid, we don't bite!"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = termsNConditionText, annotation = termsNConditionText)
            append(termsNConditionText)
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(andText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(lastText)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
                Log.d("SignupTermsAndPrivacyText", span.item)
            }
    })
}

@Composable
fun BottomSignupTextComponent(navController: NavHostController) {
    val initialText = "Are you a familiar spirit? "
    val loginText = "Log In"
    val lastText = " again and join our Halloween party!"

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(initialText)
        }
        withStyle(style = SpanStyle(color = BrandColor, fontWeight = FontWeight.Bold)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append(lastText)
        }
    }

    ClickableText(text = annotatedString, onClick = {
        annotatedString.getStringAnnotations(it, it)
            .firstOrNull()?.also { span ->
                if (span.item == "Log In") {
                    navController.navigate("LoginScreen")
                }
            }
    })

}

@Composable
fun TextInfoComponent(textVal: String) {
    Text(text = textVal, color = MaterialTheme.colorScheme.primary)
}