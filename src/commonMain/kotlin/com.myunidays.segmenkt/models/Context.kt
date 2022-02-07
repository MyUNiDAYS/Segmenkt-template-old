package com.myunidays.segmenkt.models

import kotlinx.serialization.Serializable

@Serializable
data class Context(
    val active: Boolean? = null,
    val app: App? = null,
    val campaign: Map<String, String>? = null,
    val device: Map<String, String>? = null,
    val ip: String? = null,
    val library: Library? = null,
    val locale: String? = null,
    val location: Map<String, String>? = null,
    val network: Map<String, String>? = null,
    val os: OperatingSystem? = null,
    val page: Map<String, String>? = null,
    val referrer: Map<String, String>? = null,
    val screen: Map<String, String>? = null,
    val timezone: String? = null,
    val groupId: String? = null,
    val traits: Map<String, String>? = null,
    val userAgent: String? = null
)
