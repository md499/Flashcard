package com.example.flashcard

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test


class NewActivityTest {
    @Test
    fun testYourUIBehavior() {
        // Write your UI test here
        onView(withId(R.id.yourViewId)).perform(click())
        onView(withId(R.id.anotherViewId)).check(matches(isDisplayed()))
        // Add more actions and assertions as needed
    }




}