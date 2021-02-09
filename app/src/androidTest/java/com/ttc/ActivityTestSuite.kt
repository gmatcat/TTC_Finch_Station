package com.ttc

import com.ttc.finch_station.TtcActivity
import com.ttc.finch_station.TtcActivityTest
import com.ttc.finch_station.ui.route_list.RouteListViewTest
import com.ttc.finch_station.ui.stop_list.StopListViewTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    TtcActivityTest::class
//    ,
//    RouteListViewTest::class,
//    StopListViewTest::class
)
class ActivityTestSuite