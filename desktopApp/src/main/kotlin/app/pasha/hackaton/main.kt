package app.pasha.hackaton

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PashaHackaton2",
    ) {
        App()
    }
}
