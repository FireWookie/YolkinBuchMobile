package com.yolkin.buch.android.app

import PlatformSDK
import SignInViewModel
import android.app.Application
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module
import platform.PlatformConfiguration

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        PlatformSDK.init(
            PlatformConfiguration(this),
            androidModule
        )
    }
}

val androidModule = module {
    viewModel {
       SignInViewModel()
    }
}