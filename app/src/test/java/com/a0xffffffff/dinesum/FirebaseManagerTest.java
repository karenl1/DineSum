package com.a0xffffffff.dinesum;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

import java.util.ArrayList;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FirebaseManagerTest {

    /**
     * This test checks if a request created by the user is successfully written to Firebase and
     * if the event listener for the requests for the current user correctly returns the list of
     * this user's created requests.
     */
    @Test
    public void testUserWriteRequestSuccessfully(){
        // create dummy request object needed for test
        String userID = "111";
        Restaurant testRestaurant = new Restaurant("id", "name", "address", "phoneNum",
                "city");
        RequestData testRequestData = new RequestData("startTime", "endTime", "partyName",
                4, testRestaurant, 1.0);
        Request testRequest = new Request(userID, testRequestData);
        // add event listener to Firebase to detect new requests created by the current user
        DatabaseReference requestDatabase = FirebaseManager.getInstance().getRequestDatabase();
        requestDatabase.orderByChild("requesterID").equalTo(userID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                        for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                            Request newRequest = FirebaseManager.parseJson(requestSnapshot);
                            Log.d("Test FirebaseManager user request", "requesterID" + newRequest
                                    .getRequesterID
                                            ());
                            // check if user requests have correct userID
                            assertTrue(String.equals(newRequest.getRequesterID(),userID));
                        }
                    }
                });
        // write requests to Firebase
        FirebaseManager.getInstance().writeRequest(testRequest);
    }
