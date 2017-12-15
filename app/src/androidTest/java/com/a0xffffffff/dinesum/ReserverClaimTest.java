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
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.action.ViewActions.swipeUp;

import android.support.test.uiautomator.*;
import android.view.View;
import android.widget.TextView;


/**
 * Instrumentation test for claiming new requests.
 * These tests will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class ReserverClaimTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    /**
     * Claim a pending request from the Request Feed.
     *
     * A pending request will be claimed with no errors.
     * The database should be updated and the changes should be reflected in the Reserver List.
     * The Request Feed should not contain the request.
     */
    @Test
    public void claimRequest() {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        // open request feed page
        onView(withId(R.id.menu_home)).perform(click());
        pauseTestFor(2000);
        // refresh the request feed
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);

        // select the pending request to be displayed
        UiObject pendingRequest = device.findObject(new UiSelector().textContains("Father's"));
        try {
            pendingRequest.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(500);

        // check request information for the correct info
        pauseTestFor(500);

        // claim this request
        onView(withId(R.id.request_info_button2)).perform(click());
        pauseTestFor(500);

        // check that request is in the outgoing requests page
        onView(withId(R.id.menu_acceptor)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.reserver_request_list)).perform(swipeUp());
        pauseTestFor(500);
        UiObject claimedRequest = device.findObject(new UiSelector().textContains("Father's"));
        try {
            claimedRequest.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(500);
        // verify that the claimed contains the correct data
        // get request data
        String restaurant_name = "Father's Office";
        String party_size = "Party of 2";
        String price = "$5";
        String status = "Claimed";
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
