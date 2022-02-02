package com.myunidays.segmenkt

import com.myunidays.segmenkt.exceptions.FailedSegmentRequestException
import com.myunidays.segmenkt.exceptions.InvalidRequestDataException
import com.myunidays.segmenkt.exceptions.MissingKeyException
import com.myunidays.segmenkt.models.*
import io.ktor.client.request.*
import io.ktor.http.*

private var _segment: Segment = Segment()
val segment: Segment get() = _segment

class Segment(private val writeKey: WriteKey? = null) {

    companion object {
        fun initialize(writeKey: WriteKey) {
            Log.i("Segment Initializing New Key")
            _segment = Segment(writeKey)
        }
    }

    private val baseUrl = "https://api.segment.io/v1"

    private suspend fun makePostRequest(path: String, postBody: Any) {
        Log.i("makePostRequest for $path and body $postBody")
        if (writeKey?.keyForPlatform() == null) { throw MissingKeyException() }
        httpClient.post<Response>("$baseUrl/$path") {
            header("Authorization", "Basic ${writeKey!!.keyForPlatform()}")
            contentType(ContentType.Application.Json)
            body = postBody
        }.also {
            if (!it.success) {
                throw FailedSegmentRequestException("Segment request failed")
            }
        }
    }

    suspend fun identify(identify: Identify) {
        makePostRequest("identify", identify)
    }

    suspend fun track(event: Track) {
        val eventWithContext = event.copy(context = Context(library = library, os = operatingSystem))
        if (!eventWithContext.isValid) { throw InvalidRequestDataException("Invalid tracking event, please set userid or anomid") }
        makePostRequest("track", eventWithContext)
    }

    suspend fun page() { TODO() }
    suspend fun screen() { TODO() }
    suspend fun group() { TODO() }
    suspend fun alias() { TODO() }
    suspend fun batch() { TODO() }
}
