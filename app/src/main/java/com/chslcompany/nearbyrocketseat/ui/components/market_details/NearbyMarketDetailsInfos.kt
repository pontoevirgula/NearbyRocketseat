package com.chslcompany.nearbyrocketseat.ui.components.market_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chslcompany.nearbyrocketseat.R
import com.chslcompany.nearbyrocketseat.data.model.NearbyMarket
import com.chslcompany.nearbyrocketseat.data.model.mock.mockMarkets
import com.chslcompany.nearbyrocketseat.ui.theme.Gray400
import com.chslcompany.nearbyrocketseat.ui.theme.Gray500
import com.chslcompany.nearbyrocketseat.ui.theme.MyTypography

@Composable
fun NearbyMarketDetailsInfos(modifier: Modifier = Modifier, market: NearbyMarket) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Informações", style = MyTypography.headlineSmall, color = Gray400)

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_ticket),
                    tint = Gray500,
                    contentDescription = "Ícone Cupons"
                )
                Text(
                    text = "${market.qtdCoupons} cupons disponíveis",
                    style = MyTypography.labelMedium,
                    color = Gray500
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_map_pin),
                    tint = Gray500,
                    contentDescription = "Ícone Endereço"
                )
                Text(
                    text = market.address,
                    style = MyTypography.labelMedium,
                    color = Gray500
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_phone),
                    tint = Gray500,
                    contentDescription = "Ícone Telefone"
                )
                Text(
                    text = market.phone,
                    style = MyTypography.labelMedium,
                    color = Gray500
                )
            }
        }
    }
}

@Preview
@Composable
private fun MarketDetailsInfosPreview() {
    NearbyMarketDetailsInfos(
        modifier = Modifier.fillMaxWidth(),
        market = mockMarkets.first()
    )
}