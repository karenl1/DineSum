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

import android.support.test.uiautomator.*;
import android.view.View;
import android.widget.TextView;

/**
 * Instrumentation tests for marking requests as "pending".
 * These tests will execute on an Android device.
 */
public class RequesterMarkAsPendingTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    /**
     * Mark a claimed request as pending, removing the reserver from the request.
     *
     * A claimed request will be marked as pending with no errors.
     * The database should be updated and the changes should be reflected in the Requests List.
     * The Request Feed should also contain the request again since it has been returned to the pending state.
     */
    @Test
    public void markRequestAsPending() {
        UiDevice device = UiDevice.getInstance(getInstrumentation());

        pauseTestFor(2000);
        // refresh the request page
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);

        // select the claimed request to be marked as pending
        UiObject completedRequest = device.findObject(new UiSelector().textContains("Fat Sal's"));
        try {
            completedRequest.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(500);

        // mark this request as pending
        onView(withId(R.id.request_info_button2)).perform(click());
        pauseTestFor(500);

        // check that request is marked as paid
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        UiObject claimedRequest = device.findObject(new UiSelector().textContains("Fat Sal's"));
        try {
            claimedRequest.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(500);
        // verify that the claimed contains the correct data
        // get request data
        String restaurant_name = "Fat Sal's Deli";
        String no_reserver = "Your request has not been claimed yet";
        String price = "$2";
        String status = "Pending";
        onView(withId(R.id.request_info_restaurant_name)).check(matches(withText(restaurant_name)));
        onView(withId(R.id.request_info_no_reserver)).check(matches(withText(no_reserver)));
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
