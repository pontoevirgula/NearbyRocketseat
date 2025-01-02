package com.chslcompany.nearbyrocketseat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.chslcompany.nearbyrocketseat.data.model.NearbyMarket
import com.chslcompany.nearbyrocketseat.ui.screen.home.HomeScreen
import com.chslcompany.nearbyrocketseat.ui.screen.home.HomeViewModel
import com.chslcompany.nearbyrocketseat.ui.screen.market_details.MarketDetailsScreen
import com.chslcompany.nearbyrocketseat.ui.screen.market_details.MarketDetailsViewModel
import com.chslcompany.nearbyrocketseat.ui.screen.route.Home
import com.chslcompany.nearbyrocketseat.ui.screen.route.Splash
import com.chslcompany.nearbyrocketseat.ui.screen.route.Welcome
import com.chslcompany.nearbyrocketseat.ui.screen.splash.SplashScreen
import com.chslcompany.nearbyrocketseat.ui.screen.welcome.WelcomeScreen
import com.chslcompany.nearbyrocketseat.ui.theme.NearbyRocketseatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyRocketseatTheme {
                val navController = rememberNavController()
                val homeViewModel by viewModels<HomeViewModel>()
                val marketDetailsViewModel by viewModels<MarketDetailsViewModel>()
                val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()
                val marketDetailsUiState by marketDetailsViewModel.uiStateDetails.collectAsStateWithLifecycle()
                NavHost(
                    navController = navController,
                    startDestination = Splash
                ) {
                    composable<Splash> {
                        SplashScreen(
                            modifier = Modifier.fillMaxWidth(),
                             onNavigateToWelcome = {
                                navController.navigate(Welcome)
                            }
                        )
                    }
                    composable<Welcome> {
                        WelcomeScreen(
                            modifier = Modifier.fillMaxWidth(),
                            onNavigateToHome = {
                                navController.navigate(Home)
                            }
                        )
                    }
                    composable<Home> {
                        HomeScreen(
                            modifier = Modifier.fillMaxWidth(),
                            onNavigateToMarketDetails = { selectedMarket ->
                                navController.navigate(selectedMarket)
                            },
                            uiState = homeUiState,
                            onEvent = homeViewModel::onEvent
                        )
                    }
                    composable<NearbyMarket> {
                        val selectedMarket = it.toRoute<NearbyMarket>()
                        MarketDetailsScreen(
                            market = selectedMarket,
                            uiState = marketDetailsUiState,
                            event = marketDetailsViewModel::onEventDetails,
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
