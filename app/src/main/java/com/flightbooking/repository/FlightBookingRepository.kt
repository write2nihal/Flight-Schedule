package com.flightbooking.repository

import com.flightbooking.api.ApiService
import javax.inject.Inject

/**
 * Created by Nihal Srivastava on 03/11/21.
 */

class FlightBookingRepository
@Inject constructor(private val apiService: ApiService) {
    suspend fun getSchedule() = apiService.getFlightSchedule()

}