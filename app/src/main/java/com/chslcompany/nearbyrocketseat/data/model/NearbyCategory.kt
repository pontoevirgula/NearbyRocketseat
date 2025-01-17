package com.chslcompany.nearbyrocketseat.data.model

import androidx.annotation.DrawableRes
import com.chslcompany.nearbyrocketseat.ui.components.category.CategoryFilterChipView
import kotlinx.serialization.Serializable

@Serializable
data class NearbyCategory(
    val id: String,
    val name: String
){
    @get:DrawableRes
    val icon: Int?
        get() = CategoryFilterChipView.fromDescription(description = name)?.icon
}
