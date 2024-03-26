package com.example.islamiapplication.model

import com.google.gson.annotations.SerializedName

data class RadiosItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("recent_date")
    val recentDate: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("url")
    val url: String? = null,

    var isPlayed: Boolean = false
)