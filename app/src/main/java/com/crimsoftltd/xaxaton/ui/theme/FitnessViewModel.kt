package com.crimsoftltd.xaxaton.ui.theme

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.crimsoftltd.xaxaton.domain.*
import com.crimsoftltd.xaxaton.map.KEY_ARG_DETAILS_CITY_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Result
import kotlin.random.Random

const val MAX_PEOPLE = 4
class FitnessViewModel(val iLoadData: ILoadData) : ViewModel() {



    val data = liveData(Dispatchers.IO) {
        val modelList = iLoadData.loadData()

        emit(modelList.data.places)

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