package com.uwaisalqadri.mangaku.data.souce.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CoverImageItem(
    val large: String? = "",
    val medium: String? = "",
    val original: String? = "",
    val small: String? = "",
    val tiny: String? = ""
)
