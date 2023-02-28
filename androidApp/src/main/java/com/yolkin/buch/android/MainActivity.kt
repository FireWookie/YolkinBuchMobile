package com.yolkin.buch.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yolkin.buch.Greeting
import com.yolkin.buch.MR
import com.yolkin.buch.android.presentation.navigation.NavigationTree
import com.yolkin.buch.android.presentation.navigation.generateGraph
import com.yolkin.buch.android.ui.theme.Theme
import dev.icerock.moko.resources.StringResource
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.extensions.setupWithActivity
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.configuration.DefaultModalConfiguration
import ru.alexgladkov.odyssey.core.configuration.DisplayType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootController = RootComposeBuilder().apply { generateGraph() }.build()
        rootController.setupWithActivity(this)
        setContent {
            Theme {
                CompositionLocalProvider(
                    LocalRootController provides rootController
                ) {
                    ModalNavigator(
                        configuration = DefaultModalConfiguration(
                            backgroundColor = MaterialTheme.colors.background,
                            displayType = DisplayType.FullScreen
                        )
                    ) {
                        Navigator(
                            startScreen = NavigationTree.Auth.AuthFlow.name
                        )
                    }
                }
            }
        }
    }
}
