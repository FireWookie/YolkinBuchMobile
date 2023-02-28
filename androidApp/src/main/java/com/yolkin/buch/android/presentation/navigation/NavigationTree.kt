package com.yolkin.buch.android.presentation.navigation

object NavigationTree {

    enum class Splash {
        SplashScreen
    }

    enum class Auth {
        AuthFlow,
        Login
    }

    enum class Main {
        Dashboard,
        Work,
        AddWork,
        CurrentWork,
        Profile,
        Transaction,
        AddTransaction,
        CurrentTransaction
    }
}