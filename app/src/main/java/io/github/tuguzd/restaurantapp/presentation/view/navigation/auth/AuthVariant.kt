package io.github.tuguzd.restaurantapp.presentation.view.navigation.auth

import io.github.tuguzd.restaurantapp.domain.model.access_control.credential.UserCredentials

/**
 * Represents possible authentication variants provided by the application.
 */
sealed interface AuthVariant {
    /**
     * Represents authentication by user [credentials].
     */
    data class Credentials(val credentials: UserCredentials) : AuthVariant

    /**
     * Represents authentication by Google.
     */
    object Google : AuthVariant
}
