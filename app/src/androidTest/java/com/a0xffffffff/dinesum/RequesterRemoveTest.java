package com.a0xffffffff.dinesum;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.scrollTo;
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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.CoreMatchers.anything;

import android.support.test.uiautomator.*;


/**
 * Instrumentation test for removing pending requests.
 * These tests will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class RequesterRemoveTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    /**
     * Remove a pending request from the database.
     *
     * A pending request will be removed with no errors.
     * The database should be updated and the changes should be reflected in the Requests List and Request Feed.
     * The request should no longer appear in either of the two lists.
     */
    @Test
    public void removeRequest() {
        UiDevice device = UiDevice.getInstance(getInstrumentation());

        pauseTestFor(2000);
        // refresh the request feed
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);

        // get the size of the Requests List and scroll to the bottom
        int sizeBeforeDelete = mActivityRule.getActivity().getRequesterFragment().getRequests().size();
        onData(anything()).inAdapterView(withId(R.id.requester_request_list)).atPosition(sizeBeforeDelete-1).perform(scrollTo());

        // select the user 'spending request to be displayed
        UiObject pendingRequest = device.findObject(new UiSelector().textContains("Chick-fil-A"));
        try {
            pendingRequest.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(500);

        // remove this request
        onView(withId(R.id.request_info_button2)).perform(click());
        pauseTestFor(500);

        // refresh Request List
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);

        // check that size of Requests List is 1 less than before
        int sizeAfterDelete = mActivityRule.getActivity().getRequesterFragment().getRequests().size();
        assertTrue(sizeBeforeDelete - 1 == sizeAfterDelete);

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
