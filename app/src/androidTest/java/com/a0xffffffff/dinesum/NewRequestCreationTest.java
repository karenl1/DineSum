package com.a0xffffffff.dinesum;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.contrib.PickerActions.setTime;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static org.hamcrest.CoreMatchers.anything;

import android.support.test.uiautomator.*;
import android.widget.TimePicker;


/**
 * Instrumentation test for creating new requests.
 * These tests will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class NewRequestCreationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    /**
     * Create a valid new request.
     *
     * A new request will be created with no errors and should appear in the Request Feed and User Requests List.
     */
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
        onView(withId(R.id.request_info_no_reserver)).check(matches(withText("Your request has not been claimed yet")));
        onView(withId(R.id.request_info_price)).check(matches(withText("$2")));

        // Uncomment this test if you want to force failure
        //onView(withId(R.id.request_info_restaurant_name)).check(matches(withText("Failure")));

        pauseTestFor(2000);
    }

    /**
     * Create an invalid new request with an End Time that has already passed.
     *
     * A new request will be created with no errors but should not appear in the Request Feed.
     * It should appear User Requests List, and will be deleted after confirming its presence.
     */
    @Test
    public void createNewEndTimePastRequest() {
        pauseTestFor(2000);

        // open "new request" page
        onView(withId(R.id.menu_add)).perform(click());
        pauseTestFor(500);

        // click on search box for restaurant
        onView(withId(R.id.place_autocomplete_fragment)).perform(click());
        // input text for restaurant
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        UiObject autocompleteText = device.findObject(new UiSelector().textContains("Search"));
        try {
            autocompleteText.setText("Seoul");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        // select restaurant from list of search results
        UiObject selectTableCell = device.findObject(new UiSelector().textContains("Seoul Tofu"));
        try {
            selectTableCell.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        // use time widgets to add start time and end time (using default values)
        // end time will be too early
        onView(withId(R.id.editStartTime)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(12, 00));
        pauseTestFor(500);
        onView(withText("AM")).perform(click());
        onView(withText("OK")).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.editEndTime)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(setTime(12, 01));
        pauseTestFor(500);
        onView(withText("AM")).perform(click());
        onView(withText("OK")).perform(click());
        pauseTestFor(500);

        // type party name
        onView(withId(R.id.editPartyName)).perform(typeText("tooEarly"), closeSoftKeyboard());
        pauseTestFor(500);
        // type party number
        onView(withId(R.id.editNumberInParty)).perform(typeText("12"), closeSoftKeyboard());
        pauseTestFor(500);
        // type price
        onView(withId(R.id.editPrice)).perform(typeText("12"), closeSoftKeyboard());
        pauseTestFor(500);

        // submit new request
        onView(withId(R.id.submitButton)).perform(click());

        // refresh request feed
        pauseTestFor(500);
        onView(withId(R.id.action_refresh)).perform(click());

        // check that the request is not in the nearby request feed since end time is past
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        UiObject unclaimedRequest = device.findObject(new UiSelector().textMatches("Seoul Tofu"));
        // unclaimed request still exists, which is bad
        if (unclaimedRequest.exists()) {
            assertTrue("New request not found in request feed since end time past", false);
        }
        pauseTestFor(500);

        // check that request is in the requester page
        onView(withId(R.id.menu_requester)).perform(click());
        pauseTestFor(2000);
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        UiObject pendingRequest = device.findObject(new UiSelector().textContains("Seoul Tofu"));
        try {
            pendingRequest.click();
            pauseTestFor(1000);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        pauseTestFor(1000);
        // verify that the now pending request contains the correct data
        // get request data
        String restaurant_name = "Seoul Tofu";
        String no_reserver = "Your request has not been claimed yet";
        String price = "$12";
        String status = "Pending";
        onView(withId(R.id.request_info_restaurant_name)).check(matches(withText(restaurant_name)));
        onView(withId(R.id.request_info_no_reserver)).check(matches(withText(no_reserver)));
        onView(withId(R.id.request_info_price)).check(matches(withText(price)));
        onView(withId(R.id.request_info_status)).check(matches(withText(status)));

        // remove this request
        onView(withId(R.id.request_info_button2)).perform(click());
        pauseTestFor(500);

        pauseTestFor(2000);
    }

    /**
     * Create an invalid new request with a location outside of Los Angeles.
     *
     * A new request will be created with no errors but should not appear in the Request Feed.
     * It should appear User Requests List, and will be deleted after confirming its presence.
     */
    @Test
    public void createNewLocationOutsideRequest() {
        pauseTestFor(2000);

        // obtain initial length of Request Feed
        onView(withId(R.id.menu_home)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        int startRequestFeedSize = mActivityRule.getActivity().getHomeFragement().getRequests().size();

        // obtain initial length of User's Request List
        onView(withId(R.id.menu_requester)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        int startUserRequestSize = mActivityRule.getActivity().getRequesterFragment().getRequests().size();

        // open "new request" page
        onView(withId(R.id.menu_add)).perform(click());
        pauseTestFor(500);

        // click on search box for restaurant
        onView(withId(R.id.place_autocomplete_fragment)).perform(click());
        // input text for restaurant
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        UiObject autocompleteText = device.findObject(new UiSelector().textContains("Search"));
        try {
            autocompleteText.setText("Salt");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        // select restaurant from list of search results
        UiObject selectTableCell = device.findObject(new UiSelector().textContains("Salt & Straw"));
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
        onView(withId(R.id.editPartyName)).perform(typeText("badLocation"), closeSoftKeyboard());
        pauseTestFor(500);
        // type party number
        onView(withId(R.id.editNumberInParty)).perform(typeText("4"), closeSoftKeyboard());
        pauseTestFor(500);
        // type price
        onView(withId(R.id.editPrice)).perform(typeText("2"), closeSoftKeyboard());
        pauseTestFor(500);

        // submit new request
        onView(withId(R.id.submitButton)).perform(click());
        pauseTestFor(500);

        // refresh request feed and obtain new length
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);

        int endRequestFeedSize = mActivityRule.getActivity().getHomeFragement().getRequests().size();

        // obtain new length of User's Request List
        onView(withId(R.id.menu_requester)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.action_refresh)).perform(click());
        pauseTestFor(500);
        int endUserRequestSize = mActivityRule.getActivity().getRequesterFragment().getRequests().size();

        // assert Request Feed length did not change, and User Request list increased by 1
        assertTrue(startRequestFeedSize == endRequestFeedSize);
        assertTrue(startUserRequestSize + 1 == endUserRequestSize);

        // select the first cell in the Request Feed
        pauseTestFor(500);
        onData(anything()).inAdapterView(withId(R.id.requester_request_list)).atPosition(0).perform(click());

        // check request information for the correct info
        pauseTestFor(500);
        onView(withId(R.id.request_info_restaurant_name)).check(matches(withText("Salt & Straw")));
        onView(withId(R.id.request_info_no_reserver)).check(matches(withText("Your request has not been claimed yet")));

        // remove this request
        onView(withId(R.id.request_info_button2)).perform(click());
        pauseTestFor(500);

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
