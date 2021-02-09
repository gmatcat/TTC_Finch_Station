package com.ttc.finch_station.ui.stop_list

import android.os.Bundle
import com.launchFragmentInHiltContainer
import com.ttc.finch_station.data.model.business.Stop
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.ttc.finch_station.R
import com.ttc.finch_station.data.common.data.Resource
import com.ttc.finch_station.data.common.data.Status
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Found no way to test lifecycle methods in Dagger Hilt in Fragments
 */
@ExperimentalCoroutinesApi
@HiltAndroidTest
class StopListViewTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var fragment: StopListView

    @Before
    fun setUp() {
        hiltRule.inject()
    }

//    @Test
//    fun when_handle_resource_loading_return_loading_state(){
//        //Given
//
//        //When
//        fragment.handleResource(Resource(Status.LOADING, null, ""))
//
//        //Then
//        onView(withId(R.id.lav_loading)).check(matches(ViewMatchers.withEffectiveVisibility( ViewMatchers.Visibility.VISIBLE)))
//        onView(withId(R.id.rv_station_stops)).check(matches(ViewMatchers.withEffectiveVisibility( ViewMatchers.Visibility.GONE)))
//        onView(withId(R.id.cl_something_went_wrong)).check(matches(ViewMatchers.withEffectiveVisibility( ViewMatchers.Visibility.GONE)))
//    }


//    @Test
//    fun when_handle_resource_loadingx_return_loading_state() {
//        //Given
//        var bundle: Bundle = Bundle()
//        bundle.putParcelable("stop", Stop("agency", "name", listOf()))
//        launchFragmentInHiltContainer<StopListView>(fragmentArgs = bundle) {}
//
//        //When
//        fragment.handleResource(Resource(Status.LOADING, null, ""))
//
//        //Then
//        onView(withId(R.id.lav_loading)).check(matches(ViewMatchers.withEffectiveVisibility( ViewMatchers.Visibility.GONE)))
//        onView(withId(R.id.rv_station_stops)).check(matches(ViewMatchers.withEffectiveVisibility( ViewMatchers.Visibility.GONE)))
//        onView(withId(R.id.cl_something_went_wrong)).check(matches(ViewMatchers.withEffectiveVisibility( ViewMatchers.Visibility.GONE)))
//    }



}
