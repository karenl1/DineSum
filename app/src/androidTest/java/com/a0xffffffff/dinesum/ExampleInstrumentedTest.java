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
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.text.InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE;
import static android.text.InputType.TYPE_TEXT_VARIATION_NORMAL;
import static android.text.InputType.TYPE_TEXT_VARIATION_FILTER;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.supportsInputMethods;

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
    public void createNewRequest() {
        pauseTestFor(1000);
        // refresh the request feed
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        // open "new request" page
        onView(withId(R.id.menu_add)).perform(click());
        pauseTestFor(500);

//        // type party name
//        onView(withId(R.id.editPartyName)).perform(typeText("keren"), closeSoftKeyboard());
//        pauseTestFor(500);
//        // type party number
//        onView(withId(R.id.editNumberInParty)).perform(typeText("4"), closeSoftKeyboard());
//        pauseTestFor(500);
//        // type price
//        onView(withId(R.id.editPrice)).perform(typeText("2"), closeSoftKeyboard());
//        pauseTestFor(500);

//        // use time widgets to add start time and end time (using default values)
//        onView(withId(R.id.editStartTime)).perform(click());
//        onView(withText("OK")).perform(click());
//        pauseTestFor(500);
//        onView(withId(R.id.editEndTime)).perform(click());
//        onView(withText("OK")).perform(click());

        // click on search box for restaurant
        onView(withId(R.id.place_autocomplete_fragment)).perform(click());

        pauseTestFor(500);
        // enter input text into search box

        // DON'T WORK
        //onView(hasFocus()).perform(typeText("Tsu"));
        //onView(withText("Search")).perform(typeText("Tsu"));
        //onView(withText("Hello world!")).check(matches(isDisplayed()));
        //onView(withHint("Search")).perform(typeText("Tsu"));
        //onView(withInputType(TYPE_TEXT_FLAG_AUTO_COMPLETE)).perform(typeText("Tsu"));
        //onView(withInputType(TYPE_TEXT_VARIATION_NORMAL)).perform(typeText("Tsu"));
        //onView(isSelected()).perform(typeText("Tsu"));
        //onView(isCompletelyDisplayed()).perform(typeText("Tsu"));
        //onView(withInputType(TYPE_TEXT_VARIATION_FILTER)).perform(typeText("Tsu"));
        //onView(isClickable()).perform(typeText("Tsu"));
        //onView(supportsInputMethods()).perform(typeText("Tsu"));onView(withId(R.id
        // .place_autocomplete_fragment)).perform(click(), typeText("Tsu"), closeSoftKeyboard());
        // onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("Tsu"), closeSoftKeyboard());
        // onView(withId(R.id.place_autocomplete_search_input)).perform(typeText("Tsu"), closeSoftKeyboard());

        pauseTestFor(2000);
    }


    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
