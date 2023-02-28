package com.yolkin.buch.android.presentation.navigation.bottom

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yolkin.buch.MR
import com.yolkin.buch.android.R
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabConfiguration
import ru.alexgladkov.odyssey.compose.navigation.bottom_bar_navigation.TabItem

class HomeTab: TabItem() {
    override val configuration: TabConfiguration
        @Composable
        get() {
            return TabConfiguration(
                title = stringResource(id = MR.strings.job.resourceId),
                selectedColor = MaterialTheme.colors.primary,
                unselectedColor = MaterialTheme.colors.primaryVariant,
                titleStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                selectedIcon = painterResource(id = R.drawable.home),
                unselectedIcon = painterResource(id = R.drawable.home)
            )
        }
}

class ProfileTab: TabItem() {
    override val configuration: TabConfiguration
        @Composable
        get() {
            return TabConfiguration(
                title = stringResource(id = MR.strings.profile.resourceId),
                selectedColor = MaterialTheme.colors.primary,
                unselectedColor = MaterialTheme.colors.primaryVariant,
                titleStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                ),
                selectedIcon = painterResource(id = R.drawable.profile),
                unselectedIcon = painterResource(id = R.drawable.profile)
            )
        }
}