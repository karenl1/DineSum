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

    private ArrayList<Request> mAllRequests = new ArrayList<Request>();

    private FirebaseManager() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRequestDatabase = mFirebaseDatabase.getReference("requests");

<<<<<<< 6bcbd629682abd52d2da92304366a9ab3b369b1a
<<<<<<< f13ffa2f3f61b794cb33551f21f826f037f70373
        // TODO: get user city instead of using default value
        String userCity = "Los Angeles";
        String userID = User.getUserFBID();

        // attach listener for all requests
=======
        // listener to get requests whenever requests database changes
>>>>>>> Add skeleton code for initial load of data from Firebase
=======
        // listener to get requests whenever requests database changes
>>>>>>> Add skeleton code for initial load of data from Firebase
        mRequestDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request newRequest = parseJson(requestSnapshot);
                    Log.d("All requests", "requestID: " + newRequest.getRequestID());

                    mAllRequests.add(newRequest);
                    // test to make sure data is being read
                    //Log.d(TAG, "partyName updated: " + mPartyName);
                }
                // save all requests
                RequestTracker.getInstance().setAllRequests(mAllRequests);

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

        // attach listener for nearby requests
        mRequestDatabase.orderByChild("requestData/restaurant/restaurantCity").equalTo(userCity)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                        ArrayList<Request> nearbyRequests = new ArrayList<Request>();
                        for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                            Request newRequest = parseJson(requestSnapshot);
                            Log.d("Nearby requests", "requestID: " + newRequest.getRequestID());
                            nearbyRequests.add(newRequest);
                        }
                        // save nearby requests
                        RequestTracker.getInstance().setNearbyRequests(nearbyRequests);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
                    }
                });

        // attach listener for user requests (requests created by the user)
        mRequestDatabase.orderByChild("requesterID").equalTo(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                        ArrayList<Request> userRequests = new ArrayList<Request>();
                        for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                            Request newRequest = parseJson(requestSnapshot);
                            Log.d("User requests", "requestID: " + newRequest.getRequestID());
                            userRequests.add(newRequest);
                        }
                        // save nearby requests
                        RequestTracker.getInstance().setUserRequests(userRequests);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
                    }
                });

        // attach listener for user reservations (requests claimed/completed by the user)
        mRequestDatabase.orderByChild("reserverID").equalTo(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                        ArrayList<Request> userReservations = new ArrayList<Request>();
                        for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                            Request newRequest = parseJson(requestSnapshot);
                            Log.d("User reservations", "requestID: " + newRequest.getRequestID());
                            userReservations.add(newRequest);
                        }
                        // save nearby requests
                        RequestTracker.getInstance().setUserReservations(userReservations);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
                    }
                });

    }

    /**
     * Parse JSON object from database into a Request object
     * @param  DataSnapshot JSON object read from Firebase
     * @return Request data contained in the JSON object
     */
    public Request parseJson(DataSnapshot requestSnapshot) {
        String requestID = (String) requestSnapshot.child("requestID").getValue();
        String requesterID = (String) requestSnapshot.child("requesterID").getValue();

        // request state info
        DataSnapshot requestState = requestSnapshot.child("requestState");
        String stateStr = (String) requestState.child("temp").getValue();

        // request info
        DataSnapshot requestData = requestSnapshot.child("requestData");

        String partyName = (String) requestData.child("partyName").getValue();
        int numParty = ((Long) requestData.child("numParty").getValue()).intValue();
        String startTime = (String) requestData.child("startTime").getValue();
        String endTime = (String) requestData.child("endTime").getValue();
        double payment = ((Long) requestData.child("payment").getValue()).doubleValue();

        // restaurant info
        DataSnapshot restaurant_info = requestData.child("restaurant");
        String restaurantID = (String) restaurant_info.child("restaurantID").getValue();

        // Create RequestData object
        RequestData newRequestData = new RequestData(startTime, endTime, partyName, numParty, restaurantID, (double) payment);
        Request newRequest = new Request(requesterID, newRequestData, requestID);

        return newRequest;
    }

    /**
     * Return the single unique instance of FirebaseManager
     * @return FirebaseManager unique instance
     */
    public static FirebaseManager getInstance() {
        return mFirebaseManager;
    }

    public DatabaseReference getRequestDatabase() {
        return mRequestDatabase;
    }

    /**
     * Return reference to the request node in Firebase
     * @return DatabaseReference request node reference
     */
    public DatabaseReference getRequestDatabase() {
        return mRequestDatabase;
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

}