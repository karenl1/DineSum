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
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

import android.support.test.uiautomator.*;
import android.view.View;
import android.widget.TextView;


public class BadLocationRequestTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void createNewRequest() {
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
        onView(withId(R.id.editPartyName)).perform(typeText("Matt"), closeSoftKeyboard());
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
        onView(withId(R.id.request_info_party_size)).check(matches(withText("Party of 4")));
        onView(withId(R.id.request_info_price)).check(matches(withText("$2")));

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
