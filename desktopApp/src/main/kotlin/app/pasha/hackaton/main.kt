package app.pasha.hackaton

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.pasha.hackaton.di.setupKoin

fun main() {
    setupKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Bravo Demolisher",
        ) {
            App()
        }
    }
}
