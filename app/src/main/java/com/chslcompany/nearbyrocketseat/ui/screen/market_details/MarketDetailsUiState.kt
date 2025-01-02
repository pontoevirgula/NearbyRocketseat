package com.chslcompany.nearbyrocketseat.ui.screen.market_details

import com.chslcompany.nearbyrocketseat.data.model.NearbyRules

data class MarketDetailsUiState (
    val rules : List<NearbyRules>? = null,
    val coupon: String? = null
)
