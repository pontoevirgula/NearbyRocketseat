package com.chslcompany.nearbyrocketseat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.chslcompany.nearbyrocketseat.data.model.NearbyMarket
import com.chslcompany.nearbyrocketseat.ui.screen.HomeScreen
import com.chslcompany.nearbyrocketseat.ui.screen.MarketDetailsScreen
import com.chslcompany.nearbyrocketseat.ui.screen.SplashScreen
import com.chslcompany.nearbyrocketseat.ui.screen.WelcomeScreen
import com.chslcompany.nearbyrocketseat.ui.screen.route.Home
import com.chslcompany.nearbyrocketseat.ui.screen.route.Splash
import com.chslcompany.nearbyrocketseat.ui.screen.route.Welcome
import com.chslcompany.nearbyrocketseat.ui.theme.NearbyRocketseatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NearbyRocketseatTheme {
                val navController = rememberNavController()
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
                            }
                        )
                    }
                    composable<NearbyMarket> {
                        val selectedMarket = it.toRoute<NearbyMarket>()
                        MarketDetailsScreen(
                            market = selectedMarket,
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
