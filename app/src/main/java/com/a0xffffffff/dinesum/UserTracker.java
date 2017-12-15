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

    /**
     * Retrieves points of the user with the specified UserID
     * @param userID the specified UserID
     * @return the number of points the specific user has
     */
    public long getUserPointsFromDatabase(String userID) {
        for (User u : mAllUsers) {
            if (u.getUserID().equals(userID))
                return u.getPoints();
        }
        return -1;
    }

    /**
     * Adds points to the user with the specified UserID
     * @param userID the specified UserID
     * @param points the number of points to be added (can be negative)
     * @return the User object that was modified
     */
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
