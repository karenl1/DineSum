package com.a0xffffffff.dinesum;

import android.location.Location;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String mName;
    private Location mCurrentLocation;
    //private double mRating;
    private List<Request> mRequests;
    private List<Request> mReservations;

    //Constructor
    public User(String name, Location currentLocation)
    {
        mName = name;
        mCurrentLocation = currentLocation;
        //mRating = rating;                           //set rating to 0?
        mRequests = new ArrayList<Request>();
        mReservations = new ArrayList<Request>();
    }

    public String getName()
    {
        return mName;
    }

    public Location getCurrentLocation()
    {
        return mCurrentLocation;
    }

    /*public double getRating()
    {
        return mRating;
    }*/

    public boolean addRequest(Request request)
    {
        mRequests.add(request);
        return true;
    }

    public boolean removeRequest(Request request)
    {
        if (mRequests.contains(request))
        {
            mRequests.remove(request);
            return true;
        }
        return false;
    }

    public List<Request> getRequests()
    {
        return mRequests;
    }

    public boolean addReservation(Request reservation)
    {
        mReservations.add(reservation);
        return true;
    }

    public boolean removeReservation(Request reservation)
    {
        if (mReservations.contains(reservation))
        {
            mReservations.remove(reservation);
            return true;
        }
        return false;
    }

    public List<Request> getReservations()
    {
        return mReservations;
    }

    //Implement Rating Feature if time
    /*public boolean updateRating(double rating)
    {
        return true;
    }*/
}


//4 FUNCTIONS that add/remove requests/reservations: add firebase