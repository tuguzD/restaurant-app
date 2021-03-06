package io.github.tuguzd.restaurantapp.presentation.view.screen.main.account

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserData
import io.github.tuguzd.restaurantapp.domain.model.access_control.user.UserType
import io.github.tuguzd.restaurantapp.domain.util.randomNanoId
import io.github.tuguzd.restaurantapp.presentation.R
import io.github.tuguzd.restaurantapp.presentation.view.ui.theme.RestaurantAppTheme
import io.github.tuguzd.restaurantapp.presentation.viewmodel.main.MainViewModel
import java.util.*

/**
 * Application screen which represents *Account* main application destination.
 */
@Composable
fun AccountScreen(
    user: UserData,
    mainViewModel: MainViewModel = hiltViewModel(),
    onSignOut: () -> Unit,
) {
    val appName = stringResource(R.string.app_name)
    SideEffect {
        mainViewModel.updateTitle(appName)
        mainViewModel.updateFilled(isFilled = false)
    }

    Column(
        modifier = Modifier.padding(8.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        SubcomposeAsyncImage(
            model = user.imageUri,
            contentDescription = user.imageUri?.let { stringResource(R.string.user_avatar) },
            modifier = Modifier.size(144.dp).clip(MaterialTheme.shapes.medium),
            loading = {
                Box(
                    modifier = Modifier.placeholder(
                        visible = true, highlight = PlaceholderHighlight.fade(),
                    ),
                    content = {},
                )
            },
            error = {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = stringResource(R.string.avatar_not_loaded),
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)),
                )
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(text = user.username, style = MaterialTheme.typography.headlineSmall)
        if (user.userType != UserType.Client) {
            var text = "${user.userType}"
            user.serviceItem?.let { text += " in $it" }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text, style = MaterialTheme.typography.titleSmall,
            )
        }
        val email = user.email
        if (email != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = email, style = MaterialTheme.typography.titleMedium)
        }
        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onSignOut,
            colors = ButtonDefaults
                .buttonColors(containerColor = MaterialTheme.colorScheme.error),
        ) {
            Text(stringResource(R.string.sign_out))
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun AccountScreenPreview() {
    RestaurantAppTheme {
        Surface {
            val user = UserData(
                id = randomNanoId(),
                userType = UserType.Administrator,
                username = "tuguzD",
                email = "0damir.1tugushev@gmail.com",
                imageUri = "https://avatars.githubusercontent.com/u/56772528",
                datetimeCreate = Date().toString()
            )
            AccountScreen(user = user, onSignOut = {})
        }
    }
}
