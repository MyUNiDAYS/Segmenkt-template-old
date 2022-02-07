package com.myunidays.segmenkt.models

import kotlinx.serialization.Serializable

@Serializable
data class App(
    val name: String?,
    val namespace: String?,
    val version: String?,
    val build: String?
)
