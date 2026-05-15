package app.pasha.hackaton.core.navigation.impl

import androidx.compose.runtime.mutableStateListOf
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.core.navigation.Screen


class NavigatorImpl : Navigator {

    override val backStack = mutableStateListOf<Screen>()


    override fun navigateTo(screen: Screen) {
        backStack.add(screen)
    }

    override fun back() {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }
}
