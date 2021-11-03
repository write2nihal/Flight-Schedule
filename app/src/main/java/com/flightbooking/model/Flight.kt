package com.flightbooking.model

/**
 * Created by Nihal Srivastava on 03/11/21.
 */

data class Flight(
    val Airline: String,
    val Arrival: String,
    val Departure: String,
    val destination: String,
    val logo: String,
    val source: String,
    val ticketPrice: String
)