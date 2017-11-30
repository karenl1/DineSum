package com.a0xffffffff.dinesum;

import android.location.Location;
import java.util.ArrayList;
import java.util.List;
import com.facebook.Profile;

/**
 * Represents a User.
 */
public class User {

    private String mUserID;
    private double mPoints;

/**
 * Creates a User instance.
 */
    public User()
    {
        mUserID = "";
        mPoints = 3;                           //set rating to 0?
    }

    public double getPoints()
    {
        return mPoints;
    }

    public void setPoints(double points) {
        mPoints = points;
    }

    public String getUserID() {
        if (mUserID == "")
            mUserID = User.getUserFBID();
        return mUserID;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }

    public static String getUserFBID() {
        return Profile.getCurrentProfile().getId();
    }
}


//4 FUNCTIONS that add/remove requests/reservations: add firebase