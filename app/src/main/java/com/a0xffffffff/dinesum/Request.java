package com.a0xffffffff.dinesum;

import android.util.Log;

public class Request {

    private static final String TAG = "Request";  // for debugging

    private String mRequestID;  // will be generated by Firebase when adding to database
    private User mRequester;  // cannot be modified
    private User mReserver;  // will be initialized after request is claimed
    private RequestState mRequestState;
    private RequestData mRequestData;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public Request() {

    }

    public Request(User requester, RequestData requestData) {
        // generate unique request ID using Firebase
        FirebaseManager firebaseManager = FirebaseManager.getInstance();
        mRequestID = firebaseManager.getNewRequestID();
        mRequester = requester;
        mRequestState = new RequestStatePending();
        mRequestData = requestData;
    }

    public String getRequestID() {
        return mRequestID;
    }

    public User getRequester() {
        return mRequester;
    }

    public User getReserver() {
        return mReserver;
    }

    public void setReserver(User reserver) {
        mReserver = reserver;
    }

    public RequestState getRequestState() {
        return mRequestState;
    }

    public void setRequestState(RequestState requestState) {
        mRequestState = requestState;
    }

    public RequestData getRequestData() {
        return mRequestData;
    }

    public void setRequestData(RequestData requestData) {
        mRequestData = requestData;
    }

}
