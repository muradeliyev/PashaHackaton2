package app.pasha.hackaton

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import app.pasha.hackaton.di.setupKoin
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    setupKoin()
    ComposeViewport(document.body!!) {
        App()
    }
}
