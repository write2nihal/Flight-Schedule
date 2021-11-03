package com.flightbooking.api

import com.flightbooking.model.FlightData
import com.flightbooking.util.Constants
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Nihal Srivastava on 03/11/21.
 */
interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getFlightSchedule(): Response<FlightData>

}