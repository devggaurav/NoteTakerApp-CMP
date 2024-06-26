import android.os.Build

class AndroidPlatform : Platform {
    override val isAndroid: Boolean
        get() = true
}

actual fun getPlatform(): Platform = AndroidPlatform()