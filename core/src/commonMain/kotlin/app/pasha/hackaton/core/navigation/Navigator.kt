package app.pasha.hackaton.core.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList


interface Navigator {

    val backStack: SnapshotStateList<Screen>


    fun navigateTo(screen: Screen)

    fun back()
}
