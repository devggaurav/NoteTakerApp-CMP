interface Platform {
    val isAndroid: Boolean
}

expect fun getPlatform(): Platform