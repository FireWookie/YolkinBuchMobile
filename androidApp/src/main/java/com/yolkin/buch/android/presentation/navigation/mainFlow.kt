package com.yolkin.buch.android.presentation.navigation

import androidx.compose.material.Text
import com.yolkin.buch.android.presentation.navigation.bottom.HomeTab
import com.yolkin.buch.android.presentation.navigation.bottom.ProfileTab
import com.yolkin.buch.android.presentation.navigation.bottom.configuration.BottomConfiguration
import com.yolkin.buch.android.presentation.screen.transaction.TransactionScreen
import com.yolkin.buch.android.presentation.screen.work.WorkModeScreen
import com.yolkin.buch.android.presentation.screen.work.WorkScreen
import ru.alexgladkov.odyssey.compose.extensions.bottomNavigation
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.mainFlow() {
    bottomNavigation(
        name = NavigationTree.Main.Dashboard.name,
        tabsNavModel = BottomConfiguration()
    ) {
        tab(HomeTab()) {
            screen(name = NavigationTree.Main.Work.name) {
                WorkScreen()
            }
            screen(name = NavigationTree.Main.AddWork.name) {
                WorkModeScreen()
            }
        }
        tab(ProfileTab()) {
            screen(name = NavigationTree.Main.Transaction.name) {
                TransactionScreen()
            }
        }
    }
}