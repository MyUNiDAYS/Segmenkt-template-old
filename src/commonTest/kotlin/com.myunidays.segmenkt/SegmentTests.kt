package com.myunidays.segmenkt

/*import com.myunidays.segmenkt.exceptions.MissingKeyException
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

class SegmentTests {

    @BeforeTest
    fun before() {
        val mockEngine = HttpClient(
            MockEngine {
                respond(
                    content = ByteReadChannel("""{"success":true}"""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        ) {
            install(JsonFeature) { serializer = KotlinxSerializer() }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
        Segment.initialize(
            httpClient = mockEngine,
            writeKey = WriteKey(
                android = "1",
                ios = "2"
            )
        )
    }

    @Test
    fun `MissingKey check`() = runBlocking {
        Segment.initialize(WriteKey(android = null, ios = null))
        try {
            segment.identify("2")
        } catch (exception: MissingKeyException) {
            return@runBlocking
        }
        fail("Expected Missing Key Exception")
    }

    @Test
    fun `Check simple identify request`() = runBlocking {
        segment.identify("2")
    }

    @Test
    fun `Check simple track request`() = runBlocking {
        segment.track("Test", properties = emptyMap())
    }

    @Test
    fun `Check screen tracking`() = runBlocking {
        segment.screen(
            "Home",
            mapOf(
                "Feed Type" to "private"
            )
        )
    }

    @Test
    fun `Check group tracking`() = runBlocking {
        val traits = mapOf(
            "name" to "Initech",
            "industry" to "Technology",
            "employees" to "329",
            "plan" to "enterprise",
            "total billed" to "830"
        )
        segment.group("0e8c78ea9d97a7b8185e8632", traits)
    }

    @Test
    fun `Check alias tracking`() = runBlocking {
        segment.alias("2")
    }
}*/
