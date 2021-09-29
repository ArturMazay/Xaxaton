package com.crimsoftltd.xaxaton.domain

import com.crimsoftltd.xaxaton.NetworkModel
import com.crimsoftltd.xaxaton.network.ILoadDataFromNetwork

class NetworkRepository(val iLoadDataFromNetwork: ILoadDataFromNetwork) : ILoadData {

    var dataForMap:List<PlacesItemDomain> = listOf()

    private fun mapData(networkModel: NetworkModel): ModelDomain {
        return ModelDomain(data = DataDomain(places = networkModel.data?.places?.map { places ->
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
                lng = places?.lat,
                pictureUrl = places?.picture_url
            )
        }, count = networkModel.data?.count), status = networkModel.status)
    }

    override suspend fun loadData(): ModelDomain {
        val response = iLoadDataFromNetwork.loadDataApi()
        val data = mapData(response)
        dataForMap = data.data.places?.toList().orEmpty()
        return data
    }


    override suspend fun loadDataForMap(id: Int): List<PlacesItemDomain> =
        listOf(dataForMap.first { item->
            item.id==id
        })
}




