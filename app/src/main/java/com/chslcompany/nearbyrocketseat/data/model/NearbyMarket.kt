package com.chslcompany.nearbyrocketseat.data.model

data class NearbyMarket(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String,
    val qtdCoupons: Int,
    val couponsNearbyRules: List<NearbyRules> = emptyList(),
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val phone: String,
    val cover: String,
)
