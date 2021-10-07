package com.crimsoftltd.xaxaton.domain

interface ILoadData {

    suspend fun loadData() : ModelDomain
    suspend fun loadDataForMap(id:Int) : PlacesItemDomain

}