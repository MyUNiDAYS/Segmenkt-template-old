package com.myunidays.segmenkt.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Track(
    val userId: String? = null,
    val anonymousId: String? = null,
    val event: String,
    val properties: Map<String, String>? = null,
    val timestamp: String? = null,
    val context: Context? = null,
) {
    @Transient
    val isValid: Boolean = userId != null || anonymousId != null
}
