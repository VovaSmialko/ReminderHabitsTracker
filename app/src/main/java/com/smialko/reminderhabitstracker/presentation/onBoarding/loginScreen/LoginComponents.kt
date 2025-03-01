package com.smialko.reminderhabitstracker.presentation.onBoarding.loginScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.navigation.Screen
import com.smialko.reminderhabitstracker.navigation.rememberNavigationState
import com.smialko.reminderhabitstracker.ui.theme.BgSocial
import com.smialko.reminderhabitstracker.ui.theme.BrandColor

@Composable
fun BottomComponent(
    onLoginClick: () -> Unit,
    navController: NavHostController
) {
    Column {
        LoginActionButton(onLoginClick) // callback
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
            onClick = { /*TODO*/ }, //Goggle login
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
fun LoginActionButton(
    onLoginClick: () -> Unit
) {
    Button(
        onClick = { onLoginClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Text(
            text = stringResource(R.string.login_button),
            color = Color.White,
            fontSize = 18.sp,
        )
    }
}

@Composable
fun ForgotPasswordTextComponent(navController: NavHostController) {
    Text(
        text = stringResource(R.string.forgot_password),
        color = BrandColor,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier.clickable {
            navController.navigate(Screen.ForgotPassword.route)
        }
    )
}


@Composable
fun BottomLoginTextComponent(
    initialText: String,
    action: String,
    navController: NavHostController
) {
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
                if (span.item == "Join our family!") {
                    navController.navigate(Screen.SingUp.route) {
                        launchSingleTop = true
                    }
                }
            }
    })
}