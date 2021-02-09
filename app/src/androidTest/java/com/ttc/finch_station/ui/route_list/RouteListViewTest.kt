package com.ttc.finch_station.ui.route_list

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.launchFragmentInHiltContainer
import com.ttc.finch_station.R
import com.ttc.finch_station.data.model.business.Stop
import com.ttc.finch_station.ui.stop_list.StopListView
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.android.synthetic.main.fragment_route_list.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Found no way to test lifecycle  methods in Dagger Hilt in Fragments
 */
@ExperimentalCoroutinesApi
@HiltAndroidTest
class RouteListViewTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var fragment: StopListView

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun fragmentStarted() {
        //Give
        var bundle: Bundle = Bundle()
        bundle.putParcelable("stop", Stop("agency", "name", listOf()))

        //When
        launchFragmentInHiltContainer<RouteListView>(fragmentArgs = bundle) {}

        //Then
        onView(withId(R.id.rv_stop_routes)).check(
            matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.GONE
                )
            )
        )
    }

}
