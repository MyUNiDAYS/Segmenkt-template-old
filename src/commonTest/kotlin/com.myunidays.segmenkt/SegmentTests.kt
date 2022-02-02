package com.myunidays.segmenkt

import com.myunidays.segmenkt.exceptions.InvalidRequestDataException
import com.myunidays.segmenkt.exceptions.MissingKeyException
import com.myunidays.segmenkt.models.Identify
import com.myunidays.segmenkt.models.Track
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

class SegmentTests {

    @BeforeTest
    fun before() {
        Segment.initialize(WriteKey(
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
        segment.track(event)
    }
}
