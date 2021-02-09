package com.ttc.finch_station.ui.stop_list

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.airbnb.lottie.LottieAnimationView
import com.ttc.finch_station.R
import com.ttc.finch_station.TtcActivity
import com.ttc.finch_station.data.base.BaseFragment
import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.common.data.Status
import com.ttc.finch_station.data.common.logDebug
import com.ttc.finch_station.data.common.logInfo
import com.ttc.finch_station.data.model.business.Station
import com.ttc.finch_station.data.model.business.Stop
import com.ttc.finch_station.databinding.FragmentStopListBinding
import com.ttc.finch_station.databinding.PageSomethingWentWrongBinding
import com.ttc.finch_station.ui.stop_list.adapter.OnViewRoutesCallback
import com.ttc.finch_station.ui.stop_list.adapter.StopListAdapter
import com.ttc.finch_station.vm.TtcViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StopListView : BaseFragment(),
    OnViewRoutesCallback {


    lateinit var binding: FragmentStopListBinding
    lateinit var somethingWentWrongBinding: PageSomethingWentWrongBinding

    //Framework classes
    private val ttcViewModel by activityViewModels<TtcViewModel>()
    private lateinit var stopListAdapter: StopListAdapter
    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when(intent.action){
                TtcActivity.NETWORK_CONNECTED -> loadData()
                TtcActivity.NETWORK_DISCONNECTED -> {}
            }
        }
    }

    //UI Elements
    private lateinit var rvStationStops: RecyclerView
    private lateinit var loadingSpinner: LottieAnimationView
    private lateinit var srlRefresh: SwipeRefreshLayout
    private lateinit var clSomethingWentWrong: ConstraintLayout
    private lateinit var btnRetry: Button

    //UI Data members
    var station: Station? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logDebug{}
        return inflater.inflate(R.layout.fragment_stop_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logDebug{}
        binding = FragmentStopListBinding.bind(view)
        setupUI()
        setupObserver()
    }




    private fun setupUI() {
        logDebug{}
        stopListAdapter =
            StopListAdapter(
                listOf(),
                this,
                requireContext()
            )
        binding.let { it ->
            loadingSpinner = it.lavLoading
            rvStationStops = it.rvStationStops
            rvStationStops.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = stopListAdapter
            }
            srlRefresh = it.srlRefresh
            srlRefresh.setOnRefreshListener {
                loadData()
            }
            somethingWentWrongBinding = it.clSomethingWentWrong
            somethingWentWrongBinding.let { pageSomethingWentWrongBinding ->
                clSomethingWentWrong = pageSomethingWentWrongBinding.clMain
                btnRetry = pageSomethingWentWrongBinding.btnRetry
                btnRetry.setOnClickListener{
                    loadData()
                }
            }



        }
    }

    private fun loadData() {
        logDebug{}
        if(!loadingSpinner.isVisible) {
            srlRefresh.isRefreshing = false
            ttcViewModel.fetchStationRoutes()
        }
    }

    private fun setupObserver() {
        logDebug{}
        ttcViewModel.stationRoutes.observe(viewLifecycleOwner, Observer {
            handleResource(it)
        })
    }

    fun handleResource(it: Resource<Station>) {
        logInfo({}, it.toString())
        when (it.status) {
            Status.SUCCESS -> {
                station = it.data
                it.data?.let { station ->
                    renderList(station.stops)
                }
                loadingSpinner.visibility = View.GONE
                clSomethingWentWrong.visibility = View.GONE
                rvStationStops.visibility = View.VISIBLE
            }
            Status.LOADING -> {
                loadingSpinner.visibility = View.VISIBLE
                loadingSpinner.playAnimation()
                rvStationStops.visibility = View.GONE
                clSomethingWentWrong.visibility = View.GONE
            }
            Status.GENERIC_ERROR, Status.NETWORK_ERROR, Status.IO_ERROR -> {
                loadingSpinner.visibility = View.GONE
                loadingSpinner.pauseAnimation()
                rvStationStops.visibility = View.GONE
                clSomethingWentWrong.visibility = View.VISIBLE
            }
        }
    }


    private fun renderList(stops: List<Stop>?) {
        logInfo({}, stops.toString())
        stopListAdapter.updateData(stops)
        stopListAdapter.notifyDataSetChanged()
    }

    override fun onViewRoutes(stop: Stop) {
        logInfo({}, stop.toString())
        val action =
            StopListViewDirections.actionStopListViewToRouteListView(
                stop
            )
        findNavController().navigate(action)
    }

    private fun getReceiverFilters() : IntentFilter {
        val intentFilter = IntentFilter()
        intentFilter.addAction(TtcActivity.NETWORK_CONNECTED)
        intentFilter.addAction(TtcActivity.NETWORK_DISCONNECTED)
        return intentFilter
    }

    override fun onResume() {
        super.onResume()
        logDebug{}
        requireActivity().registerReceiver(broadcastReceiver, getReceiverFilters())
    }

    override fun onPause() {
        super.onPause()
        logDebug{}
        requireActivity().unregisterReceiver(broadcastReceiver)
    }

    override fun onStop() {
        super.onStop()
        logDebug{}
    }

    override fun onDestroy() {
        super.onDestroy()
        logDebug{}
    }
}