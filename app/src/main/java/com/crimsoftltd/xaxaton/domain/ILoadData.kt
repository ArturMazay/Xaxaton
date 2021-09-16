package com.crimsoftltd.xaxaton.domain

interface ILoadData {

    suspend fun loadData() : ModelDomain

}