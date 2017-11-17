package com.a0xffffffff.dinesum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Stores lists of requests received from the FirebaseManager
 * sorted based on if the user created the request,
 * if the user claimed/completed the request,
 * or if the request is in the vicinity of the user.
 */
public class RequestTracker {

    private static RequestTracker mRequestTracker = new RequestTracker();

    private User mCurrentUser;
    private ArrayList<Request> mAllRequests;
    private ArrayList<Request> mNearbyRequests;
    private ArrayList<Request> mUserRequests;
    private ArrayList<Request> mUserReservations;

    private RequestTracker() {
    }

    public static RequestTracker getInstance() {
        return mRequestTracker;
    }

    public void setNearbyRequests(ArrayList<Request> nearbyRequests) {
        mNearbyRequests = nearbyRequests;
    }

    public void setAllRequests(ArrayList<Request> allRequests) {
        mAllRequests = allRequests;
    }

    public void setUserRequests(ArrayList<Request> userRequests) {
        mUserRequests = userRequests;
    }

    public void setUserReservations(ArrayList<Request> userReservations) {
        mUserReservations = userReservations;
    }

    public ArrayList<Request> getNearbyRequests() {
        return mNearbyRequests;
    }

    public ArrayList<Request> getAllRequests() { return mAllRequests; }

    public ArrayList<Request> getUserRequests() {
        return mUserRequests;
    }

    public ArrayList<Request> getUserReservations() {
        return mUserReservations;
    }

}
