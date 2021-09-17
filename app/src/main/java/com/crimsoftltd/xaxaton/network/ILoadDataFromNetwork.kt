package com.crimsoftltd.xaxaton.network

import com.crimsoftltd.xaxaton.NetworkModel
import retrofit2.Response
import retrofit2.http.GET

interface ILoadDataFromNetwork {
    @GET("places")
    suspend fun loadDataApi(): NetworkModel
}