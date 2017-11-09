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

}
