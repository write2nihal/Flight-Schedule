package com.flightbooking.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.flightbooking.R
import com.flightbooking.adapter.FlightAdapter
import com.flightbooking.databinding.FragmentHomeBinding
import com.flightbooking.model.Flight
import com.flightbooking.util.BookingUtils
import com.flightbooking.util.PopUpManager
import com.flightbooking.viewmodel.FlightsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Nihal Srivastava on 03/11/21.
 */
@AndroidEntryPoint
class FlightsFragment : Fragment(R.layout.fragment_home), View.OnClickListener {

    private lateinit var data: List<Flight>
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var flightAdapter: FlightAdapter
    private val viewModel: FlightsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.demo.ivToolbarMenu.setOnClickListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BookingUtils.showLoader(requireActivity())
        setUpRv()
    }

    private fun setUpRv() {
        flightAdapter = FlightAdapter()
        binding.rvFlight.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = flightAdapter
        }

        viewModel.flightResponse.observe(requireActivity(), { result ->
            data = result.flights
            flightAdapter.updateList = result.flights
            BookingUtils.hideLoader(requireActivity())
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_toolbar_menu -> {
                manageSort()
            }
        }
    }

    private fun manageSort() {
        val list = listOf("By Departure ", "By Price ")
        val mPopUpWindow = PopUpManager.showPopUp(
            context = requireContext(),
            items = list,
            anchor = binding.demo.ivToolbarMenu,
            cellLayoutRes = R.layout.popup_window,
            backgroundDrawableRes = R.drawable.tool_tip
        )

        mPopUpWindow.setOnItemClickListener { _, _, index, _ ->
            when (index) {
                0 -> {
                    val sortedByDeparture = data.sortedByDescending { it.Departure }
                    flightAdapter.updateList = sortedByDeparture
                }
                1 -> {
                    val sortedByPrice = data.sortedBy { it.ticketPrice }
                    flightAdapter.updateList = sortedByPrice
                }
            }
            mPopUpWindow.dismiss()
        }
        mPopUpWindow.show()

    }
}