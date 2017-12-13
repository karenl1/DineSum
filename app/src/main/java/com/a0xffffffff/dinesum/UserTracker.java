package com.a0xffffffff.dinesum;

import java.util.ArrayList;


/**
 * Stores list of users received from the FirebaseManager
 * keeps track of ratings for users
 */
public class UserTracker {

    private static UserTracker mUserTracker = new UserTracker();

    private ArrayList<User> mAllUsers = new ArrayList<User>();

    private UserTracker() {
    }

    public static UserTracker getInstance() {
        return mUserTracker;
    }

    public void setAllUsers(ArrayList<User> allUsers) { mAllUsers = allUsers; }

    public ArrayList<User> getAllUsers() { return mAllUsers; }

    public double getUserPointsFromDatabase(String userID) {
        for (User u : mAllUsers) {
            if (u.getUserID().equals(userID))
                return u.getPoints();
        }
        return -1;
    }

    public User updateUsersPoints(String userID, long points) {
        for (User u : mAllUsers) {
            if (u.getUserID().equals(userID)) {
                u.setPoints(u.getPoints() + points);
                return u;
            }
        }
        return null;
    }
}
