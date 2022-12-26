package uz.vianet.mvctesting.activity

import android.content.ComponentName
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.LayoutAssertions.noOverlaps
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.vianet.mvctesting.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var  scenario: ActivityScenario<MainActivity>
    @get:Rule
    val intentRule = IntentsTestRule(MainActivity::class.java)
    @Before
    fun setup(){
        scenario = launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }
    @Test
    fun shouldShowEmptyUI(){
        onView(isRoot()).check(noOverlaps())
        onView(withResourceName("floating")).check(matches(isDisplayed()))
      //  onView(withResourceName("some_ui_component")).check(matches(isDisplayed()))
       // onView(withResourceName("open_activity_btn")).check(matches(isDisplayed()))
       //onView(withResourceName("floating")).perform(click())
        //intended(hasComponent(ComponentName(ApplicationProvider.getApplicationContext(), CreateActivity::class.java.name)))
    }
    @Test
    fun verify_CreateActivity_is_started() {
        onView(withId(R.id.floating))
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))
            .perform(click())

        intended(hasComponent(CreateActivity::class.java.name))
    }
}