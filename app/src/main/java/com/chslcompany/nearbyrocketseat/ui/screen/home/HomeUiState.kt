package com.chslcompany.nearbyrocketseat.ui.screen.home

import com.chslcompany.nearbyrocketseat.data.model.NearbyCategory
import com.chslcompany.nearbyrocketseat.data.model.NearbyMarket
import com.google.android.gms.maps.model.LatLng

data class HomeUiState(
    val categories : List<NearbyCategory>? = null,
    val markets : List<NearbyMarket>? = null,
    val marketLocations : List<LatLng>? = null,
)
