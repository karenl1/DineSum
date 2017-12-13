package com.a0xffffffff.dinesum;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    /*
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.a0xffffffff.dinesum", appContext.getPackageName());
    }
    */

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void pressRefreshButton() {
        pauseTestFor(1000);
        onView(withId(R.id.action_refresh)).perform(click());
        onView(withId(R.id.menu_add)).perform(click());

        pauseTestFor(500);
        onView(withId(R.id.place_autocomplete_fragment)).perform(click());
        pauseTestFor(500);
        onView(hasFocus()).perform(typeText("Tsu"));
        //onView(withText("Search")).perform(typeText("Tsu"));
        pauseTestFor(2000);

        onView(withId(R.id.editStartTime)).perform(click());
        onView(withText("OK")).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.editEndTime)).perform(click());
        onView(withText("OK")).perform(click());


        //onView(withText("Hello world!")).check(matches(isDisplayed()));
    }


    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
