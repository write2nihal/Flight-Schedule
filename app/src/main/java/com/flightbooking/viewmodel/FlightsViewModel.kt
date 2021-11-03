package com.flightbooking.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flightbooking.model.FlightData
import com.flightbooking.repository.FlightBookingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Nihal Srivastava on 03/11/21.
 */

@HiltViewModel
class FlightsViewModel
@Inject
constructor(private val repository: FlightBookingRepository) : ViewModel() {

    private val _response = MutableLiveData<FlightData>()
    val flightResponse: LiveData<FlightData>
        get() = _response

    init {
        getDetails()
    }

    private fun getDetails() = viewModelScope.launch {
        repository.getSchedule().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("response", "error: ${response.code()}")
            }
        }
    }


}