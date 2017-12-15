package com.a0xffffffff.dinesum;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
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
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.InstrumentationRegistry.getInstrumentation;


import android.support.test.uiautomator.*;
import android.view.View;
import android.widget.TextView;


/**
 * Instrumentation test for unclaiming requests the user has previously claimed.
 * These tests will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class ReserverUnclaimTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    /**
     * Unclaim a request the user has previously claimed.
     *
     * A claimed request will be marked as pending with no errors.
     * The database should be updated and the changes should be reflected in Request Feed.
     * The Request Feed should contain the request again since it has been returned to the pending state.
     */
    @Test
    public void unclaimRequest() {
        pauseTestFor(1000);
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        // open incoming request page
        onView(withId(R.id.menu_acceptor)).perform(click());
        pauseTestFor(2000);
        // refresh the incoming request page
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);

        // select the pending request to be displayed
        UiObject claimedRequest = device.findObject(new UiSelector().textContains("Tsujita"));
        try {
            claimedRequest.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(500);

        // unclaim this request
        onView(withId(R.id.request_info_button2)).perform(click());
        pauseTestFor(500);

        // check that the request is not in the incoming request page anymore
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        UiObject unclaimedRequest = device.findObject(new UiSelector().textMatches("Tsujita"));
        // unclaimed request still exists, which is bad
        if (unclaimedRequest.exists()) {
            assertTrue("Unclaimed request no longer found in incoming requests", false);
        }
        pauseTestFor(500);

        // check that request is in the request feed (since it is now pending)
        onView(withId(R.id.menu_home)).perform(click());
        pauseTestFor(2000);
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.requestfeed_request_list)).perform(swipeUp());
        pauseTestFor(500);
        UiObject pendingRequest = device.findObject(new UiSelector().textContains("Tsujita"));
        try {
            pendingRequest.click();
            pauseTestFor(1000);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(1000);
        // verify that the now pending request contains the correct data
        // get request data
        String restaurant_name = "Tsujita LA Artisan Noodle";
        String party_size = "Party of 3";
        String price = "$3";
        String status = "Pending";
        onView(withId(R.id.request_info_restaurant_name)).check(matches(withText(restaurant_name)));
        onView(withId(R.id.request_info_party_size)).check(matches(withText(party_size)));
        onView(withId(R.id.request_info_price)).check(matches(withText(price)));
        onView(withId(R.id.request_info_status)).check(matches(withText(status)));

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
