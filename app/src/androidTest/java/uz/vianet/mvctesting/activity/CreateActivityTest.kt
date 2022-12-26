package uz.vianet.mvctesting.activity

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
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
class CreateActivityTest {
    private lateinit var  scenario: ActivityScenario<CreateActivity>
    @get:Rule
    var activityRule = ActivityTestRule(CreateActivity::class.java, false, false)

    @Before
    fun setup(){
        Intents.init()

        activityRule.launchActivity(Intent())
        scenario = launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)

    }

    @Test
    fun createNewPost() {
        val resultData = Intent()
        val title = "New Post Title"
        resultData.putExtra("title", title)

        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
        // Type user Id.
        onView(withId(R.id.et_userId))
            .perform(ViewActions.typeText("255"), ViewActions.closeSoftKeyboard())
        // Type Post Title.
        onView(withId(R.id.et_title))
            .perform(typeText(title), ViewActions.closeSoftKeyboard())
        // Type Post Title.
        onView(withId(R.id.et_body))
            .perform(typeText("New Post Body"), ViewActions.closeSoftKeyboard())
        // Click the submit button.
        onView(withId(R.id.btn_submitCreate)).perform(click())
        // Validate intending to another activity.
        intending(hasComponent(MainActivity::class.java.name)).respondWith(result)
    }
    /*@Test
    fun testForValidToast() {
        val resultData = Intent()
        val title = "New Post Title"

        // Click the submit button.
        onView(withId(R.id.btn_submitCreate)).perform(click())
        // Validate

        onView(withText("New Post Created"))
            .inRoot(withDecorView(Matchers.not(decorView)))// Here we use decorView
            .check(matches(isDisplayed()))

    }*/
}