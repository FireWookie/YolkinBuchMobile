package com.yolkin.buch.android.presentation.navigation

import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.generateGraph() {
    authFlow()
    mainFlow()
}