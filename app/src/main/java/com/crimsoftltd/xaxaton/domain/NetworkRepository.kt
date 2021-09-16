package com.crimsoftltd.xaxaton.domain

import com.crimsoftltd.xaxaton.NetworkModel
import com.crimsoftltd.xaxaton.network.ILoadDataFromNetwork

class NetworkRepository(val iLoadDataFromNetwork: ILoadDataFromNetwork) : ILoadData {


    private fun mapData(networkModel: NetworkModel): ModelDomain {
        return ModelDomain(data = networkModel.data.let { networkData ->
            DataDomain(places = networkModel.data?.places?.map { places ->
                PlacesItemDomain(
                    id = places?.id,
                    address = places?.address,
                    lat = places?.lat,
                    city = places?.city,
                    district = places?.district,
                    name = places?.name,
                    workHours = places?.workHours,
                    categories = places?.categories?.map { },
                    region = places?.region,
                    lng = places?.lat
                )
            }, count = networkData?.count)
        }, status = networkModel.status)
    }

    override suspend fun loadData():ModelDomain {
        val response = iLoadDataFromNetwork.loadDataApi()
        return mapData(response)
    }
}


