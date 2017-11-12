package com.a0xffffffff.dinesum;

import java.util.ArrayList;
import java.util.Date;

public class RequestTracker {

    private static RequestTracker mRequestTracker = new RequestTracker();

    private User mCurrentUser;
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

    private void setNearbyRequests(ArrayList<Request> nearbyRequests) {
        mNearbyRequests = nearbyRequests;
    }

    public ArrayList<Request> getNearbyRequests() {
        return mNearbyRequests;
    }

}
