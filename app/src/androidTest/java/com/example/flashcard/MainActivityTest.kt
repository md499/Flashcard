package com.example.flashcard

import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test

class MainActivityTest {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun before() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity { activity ->
            usernameEditText = activity.findViewById(R.id.user)
            passwordEditText = activity.findViewById(R.id.pass)
            loginButton = activity.findViewById(R.id.login)
        }
    }

    @Test
    fun testCorrectLogin() {
        // Simulate a correct login
        onView(withId(R.id.user))
            .perform(clearText(), typeText("user"), closeSoftKeyboard()) // Clear and enter username

        // Ensure a short delay to handle keyboard properly
       // Thread.sleep(500)

        onView(withId(R.id.pass))
            .perform(clearText(), typeText("cs501"), closeSoftKeyboard()) // Clear and enter password

        onView(withId(R.id.login)).perform(click())

        // Verify that the toast message is "Login Successful"
        onView(withText("Login Successful")).check(matches(isDisplayed()))
    }

    @Test
    fun testIncorrectLogin() {
        // Simulate an incorrect login
        onView(withId(R.id.user))
            .perform(clearText(), typeText("medha"), closeSoftKeyboard()) // Clear and enter username

        // Ensure a short delay to handle keyboard properly
       // Thread.sleep(500)

        onView(withId(R.id.pass))
            .perform(clearText(), typeText("boston"), closeSoftKeyboard()) // Clear and enter password

        onView(withId(R.id.login)).perform(click())


        // Verify that the toast message is "Login Failed"
        onView(withText("Login Failed. Please check your credentials.")).check(matches(isDisplayed()))
    }
}

