package io.github.tuguzd.restaurantapp.buildconfig.android.dependencies

object JetpackCompose {
    const val composeVersion = "1.2.0-beta02"

    object Core {
        const val ui = "androidx.compose.ui:ui:$composeVersion"
        const val activity = "androidx.activity:activity-compose:1.4.0"
        const val navigation = "androidx.navigation:navigation-compose:2.4.2"
    }

    object Material {
        const val core = "com.google.android.material:material:1.6.0"

        object Icons {
            const val dependency =
                "androidx.compose.material:material-icons-core:$composeVersion"
            const val extention =
                "androidx.compose.material:material-icons-extended:$composeVersion"
        }

        object You {
            private const val material3Version = "1.0.0-alpha12"

            const val dependency = "androidx.compose.material3:material3:$material3Version"
            const val resizableLayout =
                "androidx.compose.material3:material3-window-size-class:$material3Version"
        }
    }

    object Accompanist {
        const val accompanistVersion = "0.24.9-beta"

        object Navigation {
            const val material =
                "com.google.accompanist:accompanist-navigation-material:$accompanistVersion"
            const val animation =
                "com.google.accompanist:accompanist-navigation-animation:$accompanistVersion"
        }

        const val pager = "com.google.accompanist:accompanist-pager:$accompanistVersion"
        const val placeholder =
            "com.google.accompanist:accompanist-placeholder-material:$accompanistVersion"
        const val refreshSwipe =
            "com.google.accompanist:accompanist-swiperefresh:$accompanistVersion"
    }

    object AndroidStudio {
        const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    }

    object ThirdParty {
        object SharedElement {
            const val url = "https://s01.oss.sonatype.org/content/repositories/snapshots"
            const val dependency = "com.mxalbert.sharedelements:shared-elements:0.1.0-SNAPSHOT"
        }

        const val coil = "io.coil-kt:coil-compose:2.1.0"
        const val scalableDp = "com.intuit.sdp:sdp-android:1.0.6"
        const val navigationHilt = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object InstrumentTest {
        const val jUnit4 = "androidx.compose.ui:ui-test-junit4:$composeVersion"
        const val layout = "androidx.compose.ui:ui-test-manifest:$composeVersion"
    }
}
