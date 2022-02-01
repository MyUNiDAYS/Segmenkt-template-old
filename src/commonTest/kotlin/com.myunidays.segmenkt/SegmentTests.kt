package com.myunidays.segmenkt

import com.myunidays.segmenkt.models.Identify
import com.myunidays.segmenkt.models.Track
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class SegmentTests {

    @BeforeTest
    fun before() {
        Segment.writeKey = ""
    }

    @Test
    fun `Check simple identify request`() = runBlocking {
        val identify = Identify(
            userId = "2"
        )
        Segment.identify(identify)
    }

    @Test
    fun `Check simple track request`() = runBlocking {
        val event = Track(
            userId = "2",
            event = "Test"
        )
        Segment.track(event)
    }
}
