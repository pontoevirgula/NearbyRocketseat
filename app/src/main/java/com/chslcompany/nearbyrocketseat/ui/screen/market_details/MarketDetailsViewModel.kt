package com.chslcompany.nearbyrocketseat.ui.screen.market_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chslcompany.nearbyrocketseat.core.network.NearbyRemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketDetailsViewModel : ViewModel() {
    private val _uiStateDetails = MutableStateFlow(MarketDetailsUiState())
    val uiStateDetails = _uiStateDetails.asStateFlow()

    fun onEventDetails(event: MarketDetailsUiEvent) {
        when (event) {
            is MarketDetailsUiEvent.OnFetchRules -> fetchRules(event.marketId)
            is MarketDetailsUiEvent.OnFetchCoupon -> fetchCoupon(event.qrCodeContent)
            MarketDetailsUiEvent.OnResetCoupon -> resetCoupon()
        }
    }

    fun fetchRules(marketId: String) {
        viewModelScope.launch {
            _uiStateDetails.update { currentUiState ->
                NearbyRemoteDataSource.getMarketDetails(marketId).fold(
                    onSuccess = { marketDetails ->
                        currentUiState.copy(
                            rules = marketDetails.couponsRules
                        )
                    },
                    onFailure = {
                        currentUiState.copy(
                            rules = emptyList()
                        )
                    }
                )
            }
        }
    }

    fun fetchCoupon(qrCodeContent: String) {
        viewModelScope.launch {
            _uiStateDetails.update { currentUiState ->
                NearbyRemoteDataSource.fetchCoupon(qrCodeContent).fold(
                    onSuccess = { nearbyCoupon ->
                        currentUiState.copy(
                            coupon = nearbyCoupon.coupon
                        )
                    },
                    onFailure = {
                        currentUiState.copy(
                            coupon = ""
                        )
                    }
                )
            }
        }
    }

    fun resetCoupon() {
        _uiStateDetails.update { currentUiState ->
            currentUiState.copy(
                coupon = null
            )
        }
    }
}