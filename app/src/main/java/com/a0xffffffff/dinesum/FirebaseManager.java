package com.a0xffffffff.dinesum;

import android.content.Context;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.text.*;
import java.util.List;

/**
 * Interacts directly with Firebase Realtime Database.
 * Serves as an interface for other parts of the system to write to or read from Firebase.
 */
public class FirebaseManager {

    private static final String TAG = "FirebaseManager";  // for debugging

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRequestDatabase;
    private DatabaseReference mUserDatabase;

    private OnDataReadyListener mListener;

    public FirebaseManager(Context context) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRequestDatabase = mFirebaseDatabase.getReference("requests");
        mUserDatabase = mFirebaseDatabase.getReference("users");
        mListener = (OnDataReadyListener) context;
    }

    /**
     * Attach listeners to Firebase to fetch request objects when changes in Firebase
     * database occur. Listeners are for all requests, nearby requests in the same city as the
     * user, requests created by the user, and requests claimed/completed by the user.
     * @param userID   The current user's unique Facebook ID.
     * @param userCity The user's current city based on their Android location.
     */
    public void attachFirebaseListeners(String userID, String userCity) {
        DatabaseReference requestDatabase = getRequestDatabase();
        DatabaseReference userDatabase = getUserDatabase();

        Log.d("UserDB Check", "Test" + getUserDatabase());

        // attach listener for all requests
        requestDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                ArrayList<Request> allRequests = new ArrayList<Request>();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request newRequest = parseRequestJson(requestSnapshot);
//                    Log.d("All requests", "requestID: " + newRequest.getRequestID());
                    allRequests.add(newRequest);
                }
                // save all requests
                Collections.reverse(allRequests);
                RequestTracker.getInstance().setAllRequests(allRequests);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
            }
        });

        // attach listener for nearby requests
        requestDatabase.orderByChild("requestData/restaurant/restaurantCity").equalTo(userCity)
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                ArrayList<Request> nearbyRequests = new ArrayList<Request>();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request newRequest = parseRequestJson(requestSnapshot);

                    Log.d("Nearby requests", "requestID: " + newRequest.getRequestID());
                    // get today's date and current time
                    Date today = new Date();

                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    String todayDate = sdfDate.format(today);

                    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm", Locale.US);
                    String todayTime = sdfTime.format(today);

                    // get new request's data
                    RequestData newRequestData = newRequest.getRequestData();

                    Log.d("current time", todayTime);
                    Log.d("request end time", newRequestData.getEndTime());
//                    Log.d("currenttime vs endtime",  Integer.toString(newRequestData.getEndTime()
//                            .compareTo(todayTime)));

                    // only get today's pending requests where end time is later than current time
                    if (newRequest.getRequestState().equals(RequestState.PENDING) &&
                            newRequestData.getCreationDate().equals(todayDate) &&
                            newRequestData.getEndTime().compareTo(todayTime) >= 0
                            ) {
                        nearbyRequests.add(newRequest);
                    }

                }
                // save nearby requests
                Collections.reverse(nearbyRequests);
                RequestTracker.getInstance().setNearbyRequests(nearbyRequests);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
            }
        });

        // attach listener for user requests (requests created by the user)
        requestDatabase.orderByChild("requesterID").equalTo(userID)
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                ArrayList<Request> userRequests = new ArrayList<Request>();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request newRequest = parseRequestJson(requestSnapshot);
                    Log.d("User requests", "requestID: " + newRequest.getRequestID());
                    userRequests.add(newRequest);
                }
                // save nearby requests
                Collections.reverse(userRequests);
                RequestTracker.getInstance().setUserRequests(userRequests);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
            }
        });

        // attach listener for user reservations (requests claimed/completed by the user)
        requestDatabase.orderByChild("reserverID").equalTo(userID)
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                ArrayList<Request> userReservations = new ArrayList<Request>();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request newRequest = parseRequestJson(requestSnapshot);
                    Log.d("User reservations", "requestID: " + newRequest.getRequestID());
                    userReservations.add(newRequest);
                }
                // save nearby requests
                Collections.reverse(userReservations);
                RequestTracker.getInstance().setUserReservations(userReservations);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
            }
        });

        // attach listener for all users
        userDatabase.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                 ArrayList<User> allUsers = new ArrayList<User>();
                 for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                     User newUser = parseUserJson(userSnapshot);
                     Log.d("All Users", "userID: " + newUser.getUserID());
                     allUsers.add(newUser);
                 }
                 Log.d("FireBase AList", "Size: " + allUsers.size());
                 // save all users
                 UserTracker.getInstance().setAllUsers(allUsers);
             }

             @Override
             public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
             }
         });
    }

    /**
     * Attach listeners to Firebase to fetch request objects during initial data pull from
     * Firebase database after user logs into the app upon startup. Listeners are for all
     * requests, nearby requests in the same city as the user, requests created by the user, and
     * requests claimed/completed by the user.
     * @param userID   The current user's Facebook ID.
     * @param userCity The user's current city based on their Android location.
     */
    public void attachInitialFirebaseListeners(String userID, String userCity) {
        DatabaseReference requestDatabase = getRequestDatabase();
        DatabaseReference userDatabase = getUserDatabase();

        // listener to get all requests when app first starts
        requestDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                ArrayList<Request> allRequests = new ArrayList<Request>();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request newRequest = parseRequestJson(requestSnapshot);
//                    Log.d("Init all requests", "requestID: " + newRequest.getRequestID());
                    allRequests.add(newRequest);
                }
                // save all requests
                Collections.reverse(allRequests);
                RequestTracker.getInstance().setAllRequests(allRequests);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
            }
        });

        // listener to get nearby requests when app first starts
        requestDatabase.orderByChild("requestData/restaurant/restaurantCity").equalTo(userCity)
        .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                ArrayList<Request> nearbyRequests = new ArrayList<Request>();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request newRequest = parseRequestJson(requestSnapshot);

//                    Log.d("Init nearby requests", "requestID: " + newRequest.getRequestID());
                    // get today's date and current time
                    Date today = new Date();

                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                    String todayDate = sdfDate.format(today);

                    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm", Locale.US);
                    String todayTime = sdfTime.format(today);

                    // get new request's data
                    RequestData newRequestData = newRequest.getRequestData();

//                    Log.d("current time", todayTime);
//                    Log.d("request end time", newRequestData.getEndTime());
//                    Log.d("currenttime vs endtime",  Integer.toString(newRequestData.getEndTime()
//                            .compareTo(todayTime)));

                    // only get today's pending requests where end time is later than current time
                    if (newRequest.getRequestState().equals(RequestState.PENDING) &&
                            newRequestData.getCreationDate().equals(todayDate) &&
                            newRequestData.getEndTime().compareTo(todayTime) >= 0
                            ) {
                        nearbyRequests.add(newRequest);
                    }
                }
                // save nearby requests
                Collections.reverse(nearbyRequests);
                RequestTracker.getInstance().setNearbyRequests(nearbyRequests);
                mListener.onNearbyRequestsReady();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
            }
        });

        // listener to get user's created requests when app first starts
        requestDatabase.orderByChild("requesterID").equalTo(userID)
        .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                ArrayList<Request> userRequests = new ArrayList<Request>();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request newRequest = parseRequestJson(requestSnapshot);
                    Log.d("Init user requests", "requestID: " + newRequest.getRequestID());
                    userRequests.add(newRequest);
                }
                // save nearby requests
                Collections.reverse(userRequests);
                RequestTracker.getInstance().setUserRequests(userRequests);
                mListener.onRequesterRequestsReady();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
            }
        });

        // listener to get user's claimed/reserved when app first starts
        requestDatabase.orderByChild("reserverID").equalTo(userID)
        .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                ArrayList<Request> userReservations = new ArrayList<Request>();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    Request newRequest = parseRequestJson(requestSnapshot);
                    Log.d("Init user reservations", "requestID: " + newRequest.getRequestID());
                    userReservations.add(newRequest);
                }
                // save nearby requests
                Collections.reverse(userReservations);
                RequestTracker.getInstance().setUserReservations(userReservations);
                mListener.onReserverRequestsReady();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
            }
        });

        // listener to get all users when app first starts
        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                 ArrayList<User> allUsers = new ArrayList<User>();
                 for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                     User newUser = FirebaseManager.parseUserJson(requestSnapshot);
                     Log.d("Init all Users", "userID: " + newUser.getUserID());
                     allUsers.add(newUser);
                 }
                 // save all requests
                 UserTracker.getInstance().setAllUsers(allUsers);
                 mListener.onUsersReady();
             }

                     @Override
             public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.
                     }
         });
    }

    /**
     * Parse JSON object from database into a Request object.
     * @param requestSnapshot JSON object describing request read from Firebase.
     * @return Returns Request object with request data contained in the JSON object.
     */
    public Request parseRequestJson(DataSnapshot requestSnapshot) {
        String requestID = (String) requestSnapshot.child("requestID").getValue();
        String requesterID = (String) requestSnapshot.child("requesterID").getValue();
        String reserverID = (String) requestSnapshot.child("reserverID").getValue();
        String state = (String) requestSnapshot.child("requestState").getValue();

        // request info
        DataSnapshot requestData = requestSnapshot.child("requestData");

        String creationDate = (String) requestData.child("creationDate").getValue();
        String partyName = (String) requestData.child("partyName").getValue();
        int numParty = ((Long) requestData.child("numParty").getValue()).intValue();
        String startTime = (String) requestData.child("startTime").getValue();
        String endTime = (String) requestData.child("endTime").getValue();
        double payment = ((Long) requestData.child("payment").getValue()).doubleValue();

        // restaurant info
        DataSnapshot restaurant_info = requestData.child("restaurant");
        String restaurantID = (String) restaurant_info.child("restaurantID").getValue();
        String restaurantName = (String) restaurant_info.child("restaurantName").getValue();
        String restaurantPhoneNumber = (String) restaurant_info.child("restaurantPhoneNumber").getValue();
        String restaurantAddress = (String) restaurant_info.child("restaurantAddress").getValue();
        String restaurantCity = (String) restaurant_info.child("restaurantCity").getValue();
        Restaurant restaurant = new Restaurant(restaurantID, restaurantName, restaurantPhoneNumber, restaurantAddress, restaurantCity);

        // Create RequestData object
        RequestData newRequestData = new RequestData(startTime, endTime, partyName, numParty,
                restaurant, payment, creationDate);
        Request newRequest = new Request(requesterID, newRequestData, requestID);
        newRequest.setReserverID(reserverID);
        newRequest.setRequestState(state);

        return newRequest;
    }

    /**
     * Parse JSON object from database into a User object.
     * @param userSnapshot JSON object describing user read from Firebase.
     * @return Returns User object with user ID and rating contained in the JSON object.
     */
    public static User parseUserJson(DataSnapshot userSnapshot) {
        String userID = (String) userSnapshot.child("userID").getValue();
        Long userPoints = ((Long) userSnapshot.child("points").getValue());

        User newUser = new User();
        newUser.setUserID(userID);
        newUser.setPoints(userPoints);

        return newUser;
    }

    /**
     * Gets reference to the request node in Firebase.
     * @return Returns the request node reference.
     */
    public DatabaseReference getRequestDatabase() {
        return mRequestDatabase;
    }

    /**
     * Gets reference to the user node in Firebase.
     * @return Returns the user node reference.
     */
    public DatabaseReference getUserDatabase() { return mUserDatabase; }

    /**
     * Gets a new unique ID for a new request.
     * @return Returns the unique ID.
     */
    public String getNewRequestID() {
        return mRequestDatabase.push().getKey();
    }

    /**
     * Writes a Request object to the database.
     * @param  request The Request object to be written to the database.
     * @return Returns true if write succeeds.
     */
    public boolean writeRequest(Request request) {
        mRequestDatabase.child(request.getRequestID()).setValue(request);
        // wrote successfully to database
        return true;
        // TODO: error handling
    }

    /**
     * Deletes a Request object from the database.
     * @param  request The Request object to be deleted from the database.
     * @return Returns true if deletion succeeds.
     */
    public boolean deleteRequest(Request request) {
        mRequestDatabase.child(request.getRequestID()).removeValue();
        return true;
    }

    /**
     * Writes a User object to the database.
     * @param  user The User object to be written to the database.
     * @return Returns true if write succeeds.
     */
    public boolean writeUser(User user) {
        Log.d("write user ID", "Hi" + user.getUserID());
        Log.d("user database ref null", Boolean.toString(mUserDatabase == null));
        mUserDatabase.child(user.getUserID()).setValue(user);
        // wrote successfully to database
        return true;
        // TODO: error handling
    }

    public interface OnDataReadyListener {
        void onNearbyRequestsReady();
        void onRequesterRequestsReady();
        void onReserverRequestsReady();
        void onUsersReady();
    }

}