package com.crimsoftltd.xaxaton.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.crimsoftltd.xaxaton.domain.ILoadData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val FUTBALL = 4
class FitnessViewModel(val iLoadData: ILoadData) : ViewModel() {



    val data = liveData(Dispatchers.IO) {
        try {
            val modelList = iLoadData.loadData()
            emit(modelList.data.places)

        }catch (e:Exception){
            emit(e)
        }

    }

    val dataMaps = viewModelScope.launch(Dispatchers.IO) {
        val dataM = iLoadData.loadData()
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