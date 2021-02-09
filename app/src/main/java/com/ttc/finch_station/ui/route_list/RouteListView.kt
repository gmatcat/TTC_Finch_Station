package com.ttc.finch_station.ui.route_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ttc.finch_station.R
import com.ttc.finch_station.data.base.BaseFragment
import com.ttc.finch_station.data.common.logDebug
import com.ttc.finch_station.data.common.logInfo
import com.ttc.finch_station.data.model.business.Stop
import com.ttc.finch_station.databinding.FragmentRouteListBinding
import com.ttc.finch_station.ui.route_list.adapter.RouteListAdapter

class RouteListView : BaseFragment() {


    lateinit var binding: FragmentRouteListBinding


    private lateinit var routeListAdapter: RouteListAdapter

    private val args: RouteListViewArgs by navArgs()

    //UI Elements
    private lateinit var rvStopRoutes: RecyclerView


    //UI Data members
    private lateinit var stop: Stop

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logDebug{}
        return inflater.inflate(R.layout.fragment_route_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logDebug{}
        binding = FragmentRouteListBinding.bind(view)
        stop = args.stop
        setupUI()
    }

    private fun setupUI() {
        logInfo({}, stop.toString())
        routeListAdapter =
            RouteListAdapter(
                stop.routes,
                requireContext()
            )
        binding.let {
            rvStopRoutes = it.rvStopRoutes
            rvStopRoutes.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = routeListAdapter
            }

        }
    }


    override fun onResume() {
        super.onResume()
        logDebug{}
    }

    override fun onPause() {
        super.onPause()
        logDebug{}
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