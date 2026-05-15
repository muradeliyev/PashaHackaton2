package app.pasha.hackaton

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform