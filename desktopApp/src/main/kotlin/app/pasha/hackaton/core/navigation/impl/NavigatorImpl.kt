package app.pasha.hackaton.core.navigation.impl

import androidx.compose.runtime.mutableStateListOf
import app.pasha.hackaton.core.navigation.Screen
import app.pasha.hackaton.core.navigation.Navigator
import app.pasha.hackaton.presentation.home.HomeScreen
import javax.inject.Inject


class NavigatorImpl @Inject constructor() : Navigator {

    override val backStack = mutableStateListOf<Screen>(HomeScreen())


    override fun navigateTo(screen: Screen) {
        backStack.add(screen)
    }

    override fun back() {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }
}
