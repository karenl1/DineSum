package com.a0xffffffff.dinesum;

import java.util.Date;

public class RequestTracker {

    private static RequestTracker mRequestTracker = new RequestTracker();

    private User mCurrentUser;
    private Request[] mNearbyRequests;

    private RequestTracker() {
        mRequestTracker = RequestTracker.getInstance();
        
    }

    public static RequestTracker getInstance() {
        return mRequestTracker;
    }

    public void filterNearbyRequests(Request[] allRequests) {
        Request[] nearbyRequests = new Request[0];
        for (Request request: allRequests) {
            // check if request is in the same city as the user
//            if (request.getRequestData().getmRestaurant().getRestaurantCity()) {
//                // TODO
//            }
        }
    }

    private void setNearbyRequests(Request[] nearbyRequests) {
        mNearbyRequests = nearbyRequests;
    }

    public Request[] getNearbyRequests() {
        return mNearbyRequests;
    }

}
