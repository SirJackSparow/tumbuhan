package com.example.alcatel_dasar_android.data

data class Model(
    val plantId:String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7,
    val imageUrl: String = ""
)