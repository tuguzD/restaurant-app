package io.github.tuguzd.restaurantapp.presentation.view.screen.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.navigation.auth.AuthVariant
import io.github.tuguzd.restaurantapp.presentation.view.ui.util.GoogleAuthButton
import io.github.tuguzd.restaurantapp.presentation.view.ui.util.PasswordTextField
import io.github.tuguzd.restaurantapp.presentation.viewmodel.auth.AuthViewModel
import io.github.tuguzd.restaurantapp.presentation.viewmodel.auth.isValid

/**
 * Base screen content for all authentication screens.
 */
@OptIn(ExperimentalTextApi::class)
@Composable
fun AuthScreenContent(
    title: String,
    onAuth: (AuthVariant) -> Unit,
    alternativeDestinationDescription: String,
    alternativeDestinationText: String,
    onAlternativeNavigate: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.headlineLarge)
    Spacer(modifier = Modifier.height(32.dp))

    OutlinedTextField(
        value = authViewModel.state.username,
        onValueChange = { authViewModel.updateUsername(it) },
        label = { Text(stringResource(R.string.username)) },
        singleLine = true, modifier = Modifier.fillMaxWidth(0.8f),
    )
    Spacer(modifier = Modifier.height(16.dp))

    PasswordTextField(
        password = authViewModel.state.password,
        onPasswordChange = { authViewModel.updatePassword(it) },
        modifier = Modifier.fillMaxWidth(0.8f),
        passwordVisible = authViewModel.state.passwordVisible,
        onPasswordVisibleChange = { authViewModel.updatePasswordVisible(it) },
    )
    Spacer(modifier = Modifier.height(32.dp))

    Button(
        onClick = { onAuth(AuthVariant.Credentials) },
        enabled = authViewModel.state.isValid,
        content = { Text(title) },
    )
    Spacer(modifier = Modifier.height(24.dp))

    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
            append(alternativeDestinationDescription)
            append(' ')
        }

        // move here to avoid strange compilation error from Compose
        val style = SpanStyle(color = MaterialTheme.colorScheme.primary)
        withAnnotation(tag = "alternative", annotation = alternativeDestinationDescription) {
            withStyle(style = style) {
                append(alternativeDestinationText)
            }
        }
    }
    ClickableText(
        text = annotatedString,
        style = MaterialTheme.typography.labelLarge,
    ) { offset ->
        annotatedString
            .getStringAnnotations(tag = "alternative", start = offset, end = offset)
            .firstOrNull()?.let { onAlternativeNavigate() }
    }

    Spacer(modifier = Modifier.height(32.dp))
    Divider(
        modifier = Modifier.fillMaxWidth(0.45f),
        color = MaterialTheme.colorScheme.primary,
        thickness = 2.dp,
    )
    Spacer(modifier = Modifier.height(32.dp))

    GoogleAuthButton(onClick = { onAuth(AuthVariant.Google) })
}
