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
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.CoreMatchers.anything;

import android.support.test.uiautomator.*;


/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RequestCancelTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void cancelFirstRequest() {
        pauseTestFor(2000);

        while (onData(anything()).inAdapterView(withId(R.id.requester_request_list)).atPosition(0) != null) {
            // select the first cell in the Request Feed
            pauseTestFor(500);
            onData(anything()).inAdapterView(withId(R.id.requester_request_list)).atPosition(0).perform(click());

            try {
                pauseTestFor(500);
                onView(withId(R.id.request_info_button2)).perform(click());
            }
            catch (Exception e) {
                break;
            }

            pauseTestFor(1000);
            onView(withId(R.id.action_refresh)).perform(click());
        }
        pauseTestFor(2000);

    }

    /*
    @Test
    public void createNewRequest() {
        pauseTestFor(2000);
        // refresh the request feed
        //onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        // open "new request" page
        onView(withId(R.id.menu_add)).perform(click());
        pauseTestFor(500);

        // click on search box for restaurant
        onView(withId(R.id.place_autocomplete_fragment)).perform(click());
        // input text for restaurant
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        UiObject autocompleteText = device.findObject(new UiSelector().textContains("Search"));
        try {
            autocompleteText.setText("Tsu");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        // select restaurant from list of search results
        UiObject selectTableCell = device.findObject(new UiSelector().textContains("Tsujita"));
        try {
            selectTableCell.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        // use time widgets to add start time and end time (using default values)
        onView(withId(R.id.editStartTime)).perform(click());
        onView(withText("OK")).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.editEndTime)).perform(click());
        onView(withText("OK")).perform(click());
        pauseTestFor(500);

        // type party name
        onView(withId(R.id.editPartyName)).perform(typeText("keren"), closeSoftKeyboard());
        pauseTestFor(500);
        // type party number
        onView(withId(R.id.editNumberInParty)).perform(typeText("4"), closeSoftKeyboard());
        pauseTestFor(500);
        // type price
        onView(withId(R.id.editPrice)).perform(typeText("2"), closeSoftKeyboard());
        pauseTestFor(500);

        // submit new request
        onView(withId(R.id.submitButton)).perform(click());

        // refresh request feed
        pauseTestFor(500);
        onView(withId(R.id.action_refresh)).perform(click());

        // select the first cell in the Request Feed
        pauseTestFor(500);
        onData(anything()).inAdapterView(withId(R.id.requestfeed_request_list)).atPosition(0).perform(click());

        // check request information for the correct info
        pauseTestFor(500);
        onView(withId(R.id.request_info_restaurant_name)).check(matches(withText("Tsujita LA Artisan Noodle Annex")));
        onView(withId(R.id.request_info_party_size)).check(matches(withText("Party of 4")));
        onView(withId(R.id.request_info_price)).check(matches(withText("$2")));

        // Uncomment this test because it should fail
        //onView(withId(R.id.request_info_restaurant_name)).check(matches(withText("Failure")));

        pauseTestFor(2000);
    }
    */

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
