package com.chslcompany.nearbyrocketseat.ui.components.market_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chslcompany.nearbyrocketseat.data.model.NearbyRules
import com.chslcompany.nearbyrocketseat.data.model.mock.mockRules
import com.chslcompany.nearbyrocketseat.ui.theme.Gray400
import com.chslcompany.nearbyrocketseat.ui.theme.Gray500
import com.chslcompany.nearbyrocketseat.ui.theme.MyTypography

@Composable
fun NearbyMarketDetailsRules(modifier: Modifier = Modifier, rules: List<NearbyRules>) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Regulamento", style = MyTypography.headlineSmall, color = Gray400)

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = rules.joinToString(separator = "\n", transform = { "• ${it.description}" }),
            style = MyTypography.labelMedium,
            lineHeight = 24.sp,
            color = Gray500
        )
    }
}

@Preview
@Composable
private fun MarketDetailsRulesPreview() {
    NearbyMarketDetailsRules(
        modifier = Modifier.fillMaxWidth(),
        rules = mockRules
    )
}