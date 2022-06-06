package io.github.tuguzd.restaurantapp.presentation.view.ui.util

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentialsData
import io.github.tuguzd.restaurantapp.domain.util.checkPassword
import io.github.tuguzd.restaurantapp.domain.util.checkUsername
import io.github.tuguzd.restaurantapp.presentation.view.navigation.auth.AuthVariant

/**
 * Material Design contained button which is enabled
 * only when [username] and [password] are correct.
 */
@Composable
fun CredentialsAuthButton(
    username: String,
    password: String,
    modifier: Modifier = Modifier,
    onClick: (AuthVariant.Credentials) -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = {
            val credentials = UserCredentialsData(username, password)
            onClick(AuthVariant.Credentials(credentials))
        },
        modifier = modifier,
        enabled = checkUsername(username) && checkPassword(password),
        content = content,
    )
}
