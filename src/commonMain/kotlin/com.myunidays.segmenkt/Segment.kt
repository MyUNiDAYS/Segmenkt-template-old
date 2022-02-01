package com.myunidays.segmenkt

import com.myunidays.segmenkt.exceptions.FailedSegmentRequest
import com.myunidays.segmenkt.exceptions.InvalidRequestData
import com.myunidays.segmenkt.models.Identify
import com.myunidays.segmenkt.models.Response
import com.myunidays.segmenkt.models.Track
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object Segment {

    private val baseUrl = "https://api.segment.io/v1"
    var writeKey: WriteKey? = null

    private suspend fun makePostRequest(path: String, postBody: Any) {
        httpClient.post<Response>("$baseUrl/$path") {
            header("Authorization", "Basic ${writeKey!!.keyForPlatform()}")
            contentType(ContentType.Application.Json)
            body = postBody
        }.also {
            if(!it.success) {
                throw FailedSegmentRequest("Segment request failed")
            }
        }
    }

    suspend fun identify(identify: Identify) {
        makePostRequest("identify", identify)
    }

    suspend fun track(event: Track) {
        if (!event.isValid) { throw InvalidRequestData("Invalid tracking event") }
        makePostRequest("track", event)
    }

    suspend fun page() { TODO() }
    suspend fun screen() { TODO() }
    suspend fun group() { TODO() }
    suspend fun alias() { TODO() }
    suspend fun batch() { TODO() }
}
