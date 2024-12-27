package com.chslcompany.nearbyrocketseat.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NearbyRules(
    val id: String,
    val description: String,
    val marketId: String
)