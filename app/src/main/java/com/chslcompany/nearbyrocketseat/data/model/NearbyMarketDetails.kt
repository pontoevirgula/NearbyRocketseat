package com.chslcompany.nearbyrocketseat.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NearbyMarketDetails(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String,
    val qtdCoupons: Int,
    val couponsRules: List<NearbyRules>,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String,
    val cover: String
)
