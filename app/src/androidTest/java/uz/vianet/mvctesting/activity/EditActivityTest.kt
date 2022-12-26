package uz.vianet.mvctesting.activity

import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.vianet.mvctesting.R

@RunWith(AndroidJUnit4::class)
@LargeTest
class EditActivityTest {
    private lateinit var  scenario: ActivityScenario<EditActivity>
    @get:Rule
    val activityRule = ActivityScenarioRule(EditActivity::class.java)


    @Before
    fun setUp() {
        val title = "Old Post Title"
        val uid = "255"
        val body = "New Post Body"
        Intents.init()

        scenario =  activityRule.scenario
        scenario = launchActivity()
        scenario.moveToState(Lifecycle.State.RESUMED)


    }

    @Test
    fun initViews() {

    }

    @Test
    fun editPost() {
    }

    @Test
    fun apiPostUpdate() {
    }
}