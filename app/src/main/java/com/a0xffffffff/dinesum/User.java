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
    private long mPoints;

    /**
     * Creates a User instance.
     */
    public User()
    {
        mUserID = "";
        mPoints = 50;                           //set rating to 0?
    }

    public long getPoints()
    {
        return mPoints;
    }

    public void setPoints(long points) {
        mPoints = points;
    }

    public String getUserID() {
        if (mUserID.equals(""))
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
