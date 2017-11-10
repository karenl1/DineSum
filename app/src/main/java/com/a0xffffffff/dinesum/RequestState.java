package com.a0xffffffff.dinesum;

abstract public class RequestState {

    // not using enum because hard to use with Firebase
    public static final String PENDING = "pending";
    public static final String CLAIMED = "claimed";
    public static final String COMPLETED = "completed";
    public static final String PAID = "paid";

    private String mRequestStateName;

    public RequestState() {

    }

    public String getRequestStateName() {
        return mRequestStateName;
    }

    public void setRequestStateName(String requestStateName) {
        mRequestStateName = requestStateName;
    }
}
