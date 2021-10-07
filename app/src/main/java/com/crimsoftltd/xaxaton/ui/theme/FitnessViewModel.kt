package com.crimsoftltd.xaxaton.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.crimsoftltd.xaxaton.domain.ILoadData
import com.crimsoftltd.xaxaton.domain.PlacesItemDomain
import com.crimsoftltd.xaxaton.domain.Result
import kotlinx.coroutines.Dispatchers


class FitnessViewModel(val iLoadData: ILoadData) : ViewModel() {
    var id:Int=0

    val data = liveData(Dispatchers.IO) {
        try {
            val modelList = iLoadData.loadData()
            emit(modelList.data.places)

        } catch (e: Exception) {
            emit(e)
        }

    }

    val dataMaps:LiveData<PlacesItemDomain> = liveData(Dispatchers.Default) {
        try {
            val dataM = iLoadData.loadData()
            dataM.data.places?.forEach { dataP ->
                id = dataP.id?.toInt() ?: 0
            }
         val datalist= iLoadData.loadDataForMap(id)
            emit(datalist)
        } catch (e: Exception) {

        }
    }

    /* fun updatePeople(people: Int) {
         viewModelScope.launch {
             if (people > MAX_PEOPLE) {
                 _suggestedDestinations.value = emptyList()
             } else {
                 val newDestinations = withContext(Dispatchers.Default) {
                     destinationsRepository.destinations
                         .shuffled(Random(people * (1..100).shuffled().first()))
                 }
                 _suggestedDestinations.value = newDestinations
             }
         }
     }

     fun toDestinationChanged(newDestination: String) {
         viewModelScope.launch {
             val newDestinations = withContext(Dispatchers.Default) {
                 destinationsRepository.destinations
                     .filter { it.city.nameToDisplay.contains(newDestination) }
             }
             _suggestedDestinations.value = newDestinations
         }
     }*/
    /*    val data = liveData(Dispatchers.IO) {
            val modelList = iLoadData.loadData()

            when (modelList) {
                is Status.Failure -> {

                }
                is Status.Success -> {

                }

                is Status.Loading ->{


                }
            }

            emit(modelList)


        }*/
}