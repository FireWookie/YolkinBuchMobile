package com.yolkin.buch

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform