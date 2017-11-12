package com.a0xffffffff.dinesum;

import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class FirebaseManager {

    private static final String TAG = "FirebaseManager";  // for debugging

    // singleton class
    private static FirebaseManager mFirebaseManager = new FirebaseManager();

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRequestDatabase;

    private ArrayList<Request> requests = new ArrayList<Request>();

    private FirebaseManager() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRequestDatabase = mFirebaseDatabase.getReference("requests");

        // attach listener for requests database
        mRequestDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {

                    String requestID = (String) requestSnapshot.child("requestID").getValue();
                    String requesterID = (String) requestSnapshot.child("requesterID").getValue();

                    // request state info
                    DataSnapshot requestState = requestSnapshot.child("requestState");
                    String stateStr = (String) requestState.child("temp").getValue();

                    // request info
                    DataSnapshot requestData = requestSnapshot.child("requestData");

                    String mPartyName = (String) requestData.child("mPartyName").getValue();
                    int mNumParty = ((Long) requestData.child("mNumParty").getValue()).intValue();
                    String mStartTime = (String) requestData.child("mStartTime").getValue();
                    String mEndTime = (String) requestData.child("mEndTime").getValue();
                    double mPayment = ((Long) requestData.child("mPayment").getValue()).doubleValue();

                    // restaurant info
                    DataSnapshot restaurant_info = requestData.child("mRestaurant");
                    String restaurantID = (String) restaurant_info.child("restaurantID").getValue();

                    // Create RequestData object
                    RequestData newRequestData = new RequestData(mStartTime, mEndTime, mPartyName, mNumParty, restaurantID, (double) mPayment);
                    Request newRequest = new Request(requesterID, newRequestData, requestID);

                    //Log.d(TAG, "requestID: " + newRequest.getRequestID());

                    requests.add(newRequest);

                    // test to make sure data is being read
                    //Log.d(TAG, "partyName updated: " + mPartyName);

                }
                /*
                int i = 0;
                for (Request x: requests)
                {
                    Log.d(TAG, "requesterID: " + x.getRequesterID() + " " + i);
                    i++;
                }
                */
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });
    }

    /**
     * Return the single unique instance of FirebaseManager
     * @return Firebase unique instance
     */
    public static FirebaseManager getInstance() {
        return mFirebaseManager;
    }

    /**
     * Return a new unique ID for a new request
     * @return String unique ID
     */
    public String getNewRequestID() {
        return mRequestDatabase.push().getKey();
    }

    /**
     * Write a request object to database
     * @param  Request request object to be written
     * @return boolean true if write succeeded
     */
    public boolean writeRequest(Request request) {
        mRequestDatabase.child(request.getRequestID()).setValue(request);
        // wrote successfully to database
        return true;
        // TODO: error handling
    }

    /**
     * Fetch request objects from database given a request ID
     * @param  String    requestID
     * @return Request   request object
     */
    public Request getRequest(String requestID) {
        // TODO
        return new Request();
    }

    /**
     * Fetch request objects from database for all requests a user has made
     * @param  User      requester
     * @return Request[] array of request objects
     */
    public static Request[] getRequesterRequests(User requester) {
        // TODO
        return new Request[0];
    }

    /**
     * Fetch request objects from database for all requests a user has completed
     * @param  User      reserver
     * @return Request[] array of request objects
     */
    public static Request[] getReserverRequests(User reserver) {
        // TODO
        return new Request[0];
    }

    /**
     * Fetch request objects from database for requests near the user
     * @param  String    location
     * @return Request[] array of request objects
     */
    // TODO: change the type of param (String is just placeholder)
    public static Request[] getNearbyRequests(String location) {
        // TODO
        return new Request[0];
    }
}