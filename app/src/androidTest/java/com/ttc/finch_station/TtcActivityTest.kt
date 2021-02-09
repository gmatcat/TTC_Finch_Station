package com.ttc.finch_station

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(AndroidJUnit4ClassRunner::class)
class TtcActivityTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(TtcActivity::class.java)


    @Test
    fun activityStarted_isToolbarDisplayed() {
        onView(withId(R.id.my_toolbar)).check(matches(isDisplayed()))
    }



}
