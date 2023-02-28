import exception.exceptionModule
import json.serializationModule
import ktor.ktorModule
import settings.settingsModule

val coreModule = listOf(ktorModule, serializationModule, settingsModule, exceptionModule)