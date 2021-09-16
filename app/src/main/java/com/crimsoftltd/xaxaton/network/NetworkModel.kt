package com.crimsoftltd.xaxaton

import com.google.gson.annotations.SerializedName

data class NetworkModel(
    @SerializedName("data")
    val data: NetworkData?=null,
    @SerializedName("status")
    val status: String?=null
)

data class NetworkData(
    @SerializedName("places")
    val places: List<NetworkPlacesItem?>?=null,
    @SerializedName("count")
    val count: Int?=null
)

data class NetworkPlacesItem(
    @SerializedName("address")
    val address: String?=null,
    @SerializedName("lng")
    val lng: Double?=null,
    @SerializedName("city")
    val city: String?=null,
    @SerializedName("district")
    val district: String?=null,
    @SerializedName("name")
    val name: String?=null,
    @SerializedName("id")
    val id: Int?=null,
    @SerializedName("work_hours")
    val workHours: String?=null,
    @SerializedName("categories")
    val categories: List<Int?>?=null,
    @SerializedName("region")
    val region: String?=null,
    @SerializedName("lat")
    val lat: Double?=null
)
