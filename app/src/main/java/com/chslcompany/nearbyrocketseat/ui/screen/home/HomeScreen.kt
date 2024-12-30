package com.chslcompany.nearbyrocketseat.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chslcompany.nearbyrocketseat.data.model.NearbyMarket
import com.chslcompany.nearbyrocketseat.ui.components.category.NearbyCategoryFilterChipList
import com.chslcompany.nearbyrocketseat.ui.components.googlemap.NearbyGoogleMap
import com.chslcompany.nearbyrocketseat.ui.components.market.NearbyMarketCardList
import com.chslcompany.nearbyrocketseat.ui.theme.Gray100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    onNavigateToMarketDetails: (NearbyMarket) -> Unit
) {
    LaunchedEffect(true) {
        onEvent(HomeUiEvent.OnFetchCategories)
    }

    val bottomSheetState = rememberBottomSheetScaffoldState()
    var isBottomSheetOpened by remember { mutableStateOf(true) }
    //codigo que pega tamanho total de tela
    val configuration = LocalConfiguration.current
    if (isBottomSheetOpened) {
        BottomSheetScaffold(
            modifier = modifier,
            scaffoldState = bottomSheetState,
            sheetContainerColor = Gray100,
            //codigo abaixo indica que o bottomsheet qdo aparece vai cobrir metadde da tela
            sheetPeekHeight = configuration.screenHeightDp.dp * 0.5f,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {
                if (!uiState.markets.isNullOrEmpty()) {
                    NearbyMarketCardList(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        markets = uiState.markets,
                        onMarketClick = { selectedMarket ->
                            onNavigateToMarketDetails(selectedMarket)
                        }
                    )
                }
            },
            content = {
                if (!uiState.categories.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = it.calculateBottomPadding().minus(8.dp))
                    ) {
                        NearbyGoogleMap(
                            uiState = uiState
                        )
                        NearbyCategoryFilterChipList(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .align(Alignment.TopStart),
                            categories = uiState.categories,
                            onSelectedCategoryChanged = { selectedCategory->
                                onEvent(HomeUiEvent.OnFetchMarkets(selectedCategory.id))
                            }
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(),
        onEvent = {},
        onNavigateToMarketDetails = {}
    )
}