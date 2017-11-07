package com.a0xffffffff.dinesum;

import java.util.Date;
import java.util.Calendar;

public class RequestData {

    private Date mStartTime;
    private Date mEndTime;
    private String mPartyName;
    private int mNumParty;
    private Restaurant mRestaurant;
    private double mPayment;
    private Date mRequestCreateTime;

    public RequestData() {
    }

    public RequestData(Date startTime, Date endTime, String partyName, int numParty,
                       Restaurant restaurant, double payment, Date requestCreateTime) {

        this.mStartTime = startTime;
        this.mEndTime = endTime;
        this.mPartyName = partyName;
        this.mNumParty = numParty;
        this.mRestaurant = restaurant;
        this.mPayment = payment;
        this.mRequestCreateTime = requestCreateTime;

    }

    public Date getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(Date mStartTime) {
        this.mStartTime = mStartTime;
    }

    public Date getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(Date mEndTime) {
        this.mEndTime = mEndTime;
    }

    public double getmPayment() {
        return mPayment;
    }

    public void setmPayment(double mPayment) {
        this.mPayment = mPayment;
    }

    public String getmPartyName() {
        return mPartyName;
    }

    public void setmPartyName(String mPartyName) {
        this.mPartyName = mPartyName;
    }

    public Date getmRequestCreateTime() {
        return mRequestCreateTime;
    }

    public void setmRequestCreateTime(Date mRequestCreateTime) {
        this.mRequestCreateTime = mRequestCreateTime;
    }

    public void setmRestaurant(Restaurant mRestaurant) {
        this.mRestaurant = mRestaurant;
    }

    public Restaurant getmRestaurant() {
        return mRestaurant;
    }

    public int getmNumParty() {
        return mNumParty;
    }

    public void setmNumParty(int mNumParty) {
        this.mNumParty = mNumParty;
    }

}
