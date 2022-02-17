import com.myunidays.segmenkt.*

fun main() {
    val segmentConfig = Configuration(
        writeKey = WriteKey(
            android = "123",
            ios = "222"
        ),
        context = null
    )
    Analytics.setupWithConfiguration(segmentConfig)
    Analytics.shared().track("Cool Event")
    Analytics.shared().identify("1")
    Analytics.shared().group("1")
    Analytics.shared().screen("Cool Screen")
}
