import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val isAndroid: Boolean
        get() = false
}

actual fun getPlatform(): Platform = IOSPlatform()