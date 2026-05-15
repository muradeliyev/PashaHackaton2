package app.pasha.hackaton.core.navigation.di

import app.pasha.hackaton.core.navigation.Screen
import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
annotation class DestinationKey(
    val value: KClass<out Screen>
)
