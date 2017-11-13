package com.a0xffffffff.dinesum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import android.util.Log;

public class RequestTracker {

    private static RequestTracker mRequestTracker = new RequestTracker();

    private User mCurrentUser;
    private ArrayList<Request> mAllRequests;
    private ArrayList<Request> mNearbyRequests;

    private RequestTracker() {
    }

    public static RequestTracker getInstance() {
        return mRequestTracker;
    }

    public ArrayList<Request> getFilteredNearbyRequests(ArrayList<Request> allRequests) {
        ArrayList<Request> filteredNearbyRequests = new ArrayList<Request>();
        for (Request request: allRequests) {
            // check if request is in the same city as the user
//            if (request.getRequestData().getmRestaurant().getRestaurantCity()) {
//                // TODO
//            }
        }
        return filteredNearbyRequests;
    }

    public void setNearbyRequests(ArrayList<Request> nearbyRequests) {
        mNearbyRequests = nearbyRequests;
    }

    public void setAllRequests(ArrayList<Request> allRequests) {
        mAllRequests = allRequests;
    }

    public ArrayList<Request> getNearbyRequests() {
        return mNearbyRequests;
    }

    public ArrayList<Request> getmAllRequests() { return mAllRequests; }

    public void printAllRequests() {
        for (Request request: mAllRequests){
            Log.d("PRINT REQUESTS", "requestID: " + request.getRequesterID() + " " + "partyname: " + request.getRequestData().getPartyName());
        }
    }
}
