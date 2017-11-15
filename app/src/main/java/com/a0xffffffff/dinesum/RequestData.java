package com.a0xffffffff.dinesum;

import java.util.Date;
import java.util.Calendar;

/**
 * Stores a Request's Data (time, party, restaurant, and payment information).
 */
public class RequestData {

    private String mStartTime;
    private String mEndTime;
    private String mPartyName;
    private int mNumParty;
    private Restaurant mRestaurant;
    private double mPayment;

    /**
     * Creates a RequestData instance.
     */
    public RequestData() {
    }

    /**
     * Creates a RequestData instance.
     * @param startTime The earliest time the requester would like the reservation to be placed.
     * @param endTime The time at which the request is cancelled if it has not been completed.
     * @param partyName The name the reservation should be placed under.
     * @param numParty Number of people in the party.
     * @param restaurantID The restaurant's unique ID.
     * @param payment The amount the requester is willing to pay for a reservation.
     */
    public RequestData(String startTime, String endTime, String partyName, int numParty,
                       Restaurant restaurant, double payment) {

        this.mStartTime = startTime;
        this.mEndTime = endTime;
        this.mPartyName = partyName;
        this.mNumParty = numParty;
        this.mRestaurant = restaurant;
        this.mPayment = payment;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public void setStartTime(String startTime) {
        this.mStartTime = startTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public void setEndTime(String endTime) {
        this.mEndTime = endTime;
    }

    public double getPayment() {
        return mPayment;
    }

    public void setPayment(double payment) {
        this.mPayment = payment;
    }

    public String getPartyName() {
        return mPartyName;
    }

    public void setPartyName(String partyName) {
        this.mPartyName = partyName;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.mRestaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return mRestaurant;
    }

    public int getNumParty() {
        return mNumParty;
    }

    public void setNumParty(int numParty) {
        this.mNumParty = numParty;
    }

}
