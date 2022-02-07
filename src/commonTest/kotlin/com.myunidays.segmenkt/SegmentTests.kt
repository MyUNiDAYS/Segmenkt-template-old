package com.myunidays.segmenkt

import com.myunidays.segmenkt.exceptions.InvalidRequestDataException
import com.myunidays.segmenkt.exceptions.MissingKeyException
import com.myunidays.segmenkt.models.Identify
import com.myunidays.segmenkt.models.Track
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
        val mockEngine = HttpClient(MockEngine {
            respond(
                content = ByteReadChannel("""{"success":true}"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }) {
            install(JsonFeature) { serializer = KotlinxSerializer() }
            install(Logging) { level = LogLevel.ALL }
        }
        Segment.initialize(
            mockEngine,
            WriteKey(
            android = "1",
            ios = "2"
        ))
    }

    @Test
    fun `MissingKey check`() = runBlocking {
        Segment.initialize(WriteKey(android = null, ios = null))
        try {
            segment.identify(Identify(userId = "2"))
        } catch (exception: MissingKeyException) {
            return@runBlocking
        }
        fail("Expected Missing Key Exception")
    }

    @Test
    fun `Invalid track check`() = runBlocking {
        try {
            segment.track(Track(event = "Test"))
        } catch (exception: InvalidRequestDataException) {
            return@runBlocking
        }
        fail("Expected InvalidRequestDataException")
    }

    @Test
    fun `Check simple identify request`() = runBlocking {
        val identify = Identify(
            userId = "2"
        )
        segment.identify(identify)
    }

    @Test
    fun `Check simple track request`() = runBlocking {
        val event = Track(
            userId = "2",
            event = "Test"
        )
        println(event)
        segment.track(event)
    }
}
