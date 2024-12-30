package com.chslcompany.nearbyrocketseat.core.network

import com.chslcompany.nearbyrocketseat.core.network.KtorHttpClient.httpClientAndroid
import com.chslcompany.nearbyrocketseat.data.model.NearbyCategory
import com.chslcompany.nearbyrocketseat.data.model.NearbyCoupon
import com.chslcompany.nearbyrocketseat.data.model.NearbyMarket
import com.chslcompany.nearbyrocketseat.data.model.NearbyMarketDetails
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object NearbyRemoteDataSource {

    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"
    private const val LOCAL_HOST_PHYSICAL_BASE_URL = "http://SEU_SUB_DOMINIO_AQUI:3333"

    private const val BASE_URL = LOCAL_HOST_EMULATOR_BASE_URL

    // 1 - Busca de categorias
    // 2 - Busca de Locais (com base em uma categoria)
    // 3 - Busca de detalhes de um local (com base em um local especifico)
    // 4 - Gerar cupom a partir de leitura do qrcode

    suspend fun getCategories(): Result<List<NearbyCategory>> = try {
        val categories = httpClientAndroid.get("$BASE_URL/categories")
            .body<List<NearbyCategory>>()

        Result.success(categories)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarkets(categoryId: String): Result<List<NearbyMarket>> = try {
        val markets = httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}")
            .body<List<NearbyMarket>>()

        Result.success(markets)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarketDetails(marketId: String): Result<NearbyMarketDetails> = try {
        val market = httpClientAndroid.get("$BASE_URL/markets/${marketId}")
            .body<NearbyMarketDetails>()

        Result.success(market)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun fetchCoupon(marketId: String): Result<NearbyCoupon> = try {
        val coupon = httpClientAndroid.patch("$BASE_URL/coupons/${marketId}")
            .body<NearbyCoupon>()

        Result.success(coupon)
    } catch (e: Exception) {
        Result.failure(e)
    }

}