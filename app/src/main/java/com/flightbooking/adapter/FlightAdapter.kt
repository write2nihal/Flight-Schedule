package com.flightbooking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.flightbooking.databinding.FlightLayoutAdapterBinding
import com.flightbooking.model.Flight
import com.flightbooking.util.BookingUtils

/**
 * Created by Nihal Srivastava on 03/11/21.
 */
class FlightAdapter : RecyclerView.Adapter<FlightAdapter.FlightViewHolder>() {

    inner class FlightViewHolder(val binding: FlightLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Flight>() {
        override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var updateList: List<Flight>
        get() = differ.currentList
        set(value) {
            differ.submitList(null)
            differ.submitList(value)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        return FlightViewHolder(
            FlightLayoutAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val data = updateList[position]

        holder.binding.apply {
            tvAirline.text = data.Airline
            tvPrice.text = "INR " + data.ticketPrice
            tvSource.text = data.source
            tvDest.text = data.destination
            tvArrival.text = BookingUtils.dateTime(data.Arrival)
            tvDeparture.text = BookingUtils.dateTime(data.Departure)
            Glide.with(imageView).load(data.logo).into(imageView)
        }
    }

    override fun getItemCount() = updateList.size

}