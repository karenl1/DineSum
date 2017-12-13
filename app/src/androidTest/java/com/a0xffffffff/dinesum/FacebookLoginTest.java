package com.a0xffffffff.dinesum;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;

import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.*;
//import android.support.test.uiautomator.UiDevice;
//import android.support.test.uiautomator.By;
//import android.support.test.uiautomator.Until;
import android.support.test.filters.SdkSuppress;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.webkit.WebView;
import android.widget.*;

import static org.hamcrest.CoreMatchers.is;


@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class FacebookLoginTest {
    @Test
    public void testFacebook_correctData_enterNotificationCenter() throws Exception {
        final UiDevice mDevice =
                UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        final int timeOut = 1000 * 60;

        // Login Activity
        onView(withId(R.id.login_button)).perform(click());

        // Facebook WebView - Page 1
        mDevice.wait(Until.findObject(By.clazz(WebView.class)), timeOut);

        // Set Login
        UiObject emailInput = mDevice.findObject(new UiSelector()
                .instance(0)
                .className(EditText.class));

        emailInput.waitForExists(timeOut);
        emailInput.setText("android_wppcmrq_facebook@tfbnw.net");

        // Set Password
        UiObject passwordInput = mDevice.findObject(new UiSelector()
                .instance(1)
                .className(EditText.class));

        passwordInput.waitForExists(timeOut);
        passwordInput.setText("testPassword");

        // Confirm Button Click
        UiObject buttonLogin = mDevice.findObject(new UiSelector()
                .instance(0)
                .className(Button.class));

        buttonLogin.waitForExists(timeOut);
        buttonLogin.clickAndWaitForNewWindow();

        // Facebook WebView - Page 2
        UiObject buttonOk = mDevice.findObject(new UiSelector()
                .instance(1)
                .className(Button.class));

        buttonOk.waitForExists(timeOut);
        buttonOk.click();

        // should be properly synchronised with Espresso via IdlingResource,
        // ConditionWatcher or any similar waiting solution
        Thread.sleep(15000);

        // NotificationCenter Activity
        //assertThat(getCurrentActivity() instanceof NotificationCenterActivity, is(true));
    }

}
