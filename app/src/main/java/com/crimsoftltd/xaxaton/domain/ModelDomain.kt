package com.crimsoftltd.xaxaton.domain

data class ModelDomain(
    val data: DataDomain,
    val status: String?
)

data class DataDomain(
    val places: List<PlacesItemDomain>?,
    val count: Int?
)

data class PlacesItemDomain(
    val address: String?,
    val lng: Double?,
    val city: String?,
    val district: String?,
    val name: String?,
    val id: Int?,
    val workHours: String?,
    val categories: List<Unit>?,
    val region: String?,
    val lat: Double?
)
