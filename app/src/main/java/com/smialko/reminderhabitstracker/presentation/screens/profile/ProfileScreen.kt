package com.smialko.reminderhabitstracker.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.components.DrawableButton
import com.smialko.reminderhabitstracker.components.IconButton
import com.smialko.reminderhabitstracker.navigation.Screen
import com.smialko.reminderhabitstracker.ui.theme.BrandColor
import com.smialko.reminderhabitstracker.ui.theme.Dimension
import com.smialko.reminderhabitstracker.utils.Response


@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {

    val generalOptions = remember {
        listOf(Screen.Settings)
    }
    val personalOptions = remember {
        listOf(Screen.PrivacyPolicies, Screen.TermsConditions)
    }

    val singOutOptions = remember {
        listOf(Screen.Login)
    }


    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        when (val getUserDataState = profileViewModel.getUserData.value) {
            is Response.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = BrandColor)
                }
            }

            is Response.Success -> {
                val userData = getUserDataState.data
                if (userData != null) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = Dimension.pagePadding),
                        verticalArrangement = Arrangement.spacedBy(Dimension.pagePadding),
                    ) {
                        item {
                            Text(
                                text = stringResource(id = R.string.your_profile),
                                fontSize = 20.sp,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        //Header
                        item {
                            ProfileHeaderSection(
                                name = userData.fullName,
                                email = userData.email
                            )
                        }
                        //General
                        item {
                            Text(
                                text = stringResource(R.string.general),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        items(generalOptions) { option ->
                            ProfileOptionItem(
                                icon = option.icon,
                                title = option.title,
                                onOptionClicked = {},
                            )
                        }
                        //Personal
                        item {
                            Text(
                                text = "Personal",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        items(personalOptions) { option ->
                            ProfileOptionItem(
                                icon = option.icon,
                                title = option.title,
                                onOptionClicked = {},
                            )
                        }
                        item {
                            Text(
                                text = "Personal",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        items(singOutOptions) { option ->
                            ProfileOptionItem(
                                icon = R.drawable.logout,
                                title = R.string.Log_Out,
                                onOptionClicked = {
                                    profileViewModel.signOut()
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(Screen.Profile.route)
                                    }
                                },
                            )
                        }
                    }
                }
            }

            is Response.Error -> {
                LaunchedEffect(getUserDataState) {
                    snackbarHostState.showSnackbar(
                        message = getUserDataState.message,
                        duration = SnackbarDuration.Long
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Unable to load profile",
                            style = MaterialTheme.typography.titleMedium,

                            )
                        Button(
                            onClick = { profileViewModel.refreshUserInfo() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BrandColor
                            )
                        ) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileOptionItem(icon: Int?, title: Int?, onOptionClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth()
            .clickable { onOptionClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimension.pagePadding),
    ) {
        DrawableButton(
            painter = rememberAsyncImagePainter(model = icon),
            backgroundColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f),
            iconTint = MaterialTheme.colorScheme.primary,
            onButtonClicked = {},
            iconSize = Dimension.smIcon,
            paddingValue = PaddingValues(Dimension.md),
            shape = CircleShape,
        )
        title?.let {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        IconButton(
            icon = Icons.Default.KeyboardArrowRight,
            backgroundColor = MaterialTheme.colorScheme.background,
            iconTint = MaterialTheme.colorScheme.onBackground,
            onButtonClicked = {},
            iconSize = Dimension.smIcon,
            paddingValue = PaddingValues(Dimension.md),
            shape = CircleShape,
        )
    }
}

@Composable
fun ProfileHeaderSection(name: String, email: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimension.pagePadding),
    ) {
        Image(
            modifier = Modifier
                .size(Dimension.xlIcon)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.loft_avatar),
            contentDescription = null,
        )

        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = email ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            )
        }
    }
}