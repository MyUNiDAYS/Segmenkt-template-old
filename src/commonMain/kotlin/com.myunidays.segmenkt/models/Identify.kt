package com.myunidays.segmenkt.models

import kotlinx.serialization.Serializable

@Serializable
data class Identify(
    val anonymousId: String? = null,
    val userId: String? = null,
    val traits: Map<String, String>? = null,
    val timestamp: String? = null
)
