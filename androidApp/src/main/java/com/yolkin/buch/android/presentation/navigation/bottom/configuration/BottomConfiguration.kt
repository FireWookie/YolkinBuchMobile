package com.yolkin.buch.android.presentation.navigation.bottom.configuration

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.BottomNavConfiguration
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabsNavModel

class BottomConfiguration : TabsNavModel<BottomNavConfiguration>() {
    override val navConfiguration: BottomNavConfiguration
        @Composable
        get() {
            return BottomNavConfiguration(
                backgroundColor = MaterialTheme.colors.background,
                selectedColor = MaterialTheme.colors.primary,
                unselectedColor = MaterialTheme.colors.primaryVariant
            )
        }

}