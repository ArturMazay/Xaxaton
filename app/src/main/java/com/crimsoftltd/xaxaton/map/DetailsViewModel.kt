package com.crimsoftltd.xaxaton.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.crimsoftltd.xaxaton.domain.Result
import com.crimsoftltd.xaxaton.domain.ILoadData
import com.crimsoftltd.xaxaton.domain.PlacesItemDomain
import kotlinx.coroutines.Dispatchers


class DetailsViewModel(
    private val destinationsRepository: ILoadData,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    //   private val cityId = savedStateHandle.get<Int>(KEY_ARG_DETAILS_CITY_NAME)
    //val cityid=savedStateHandle.id
    private val cityName = savedStateHandle.get<Int>(KEY_ARG_DETAILS_CITY_NAME)

    val cityDetails: LiveData<PlacesItemDomain> = liveData(Dispatchers.Default) {
       try {
           val destinations = destinationsRepository.loadDataForMap(id = 9)
           emit(destinations)
          // emit(Result.Success(destinations))
       }catch (e:Exception){

       }
    }

}




