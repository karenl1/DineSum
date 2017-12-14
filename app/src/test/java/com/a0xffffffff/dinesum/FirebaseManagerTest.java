/*
package com.a0xffffffff.dinesum;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import android.support.test.InstrumentationRegistry;

@org.junit.runner.RunWith(AndroidJUnit4.class)
public class FirebaseManagerTest {
    private Context instrumentationCtx;
    JSONParser parser = new JSONParser();
    JSONArray jsonFile = new JSONArray();
    FirebaseManager test_FirebaseManager;
    Request[] test_requests;

    public JSONArray file_to_json() throws IOException, ParseException
    {
        Object obj = parser.parse(new FileReader("/Users/SusanKrkasharian/Desktop/DineSum/app/src/test/java/com/a0xffffffff/dinesum/dinesum-requests-export.json"));
        jsonFile.add(obj);
        return jsonFile;
    }

    public Request[] create_test_requests()
    {
        //Request1
        String requesterID = "10215291050977832";
        Restaurant restaurant = new Restaurant("ChIJ__Jsmgq7woARhmKIwcjfc0o", "Popcorn Chicken Taste of Taiwan",
                "+1 424-832-3076", "2224 Sawtelle Blvd, Los Angeles, CA 90064, USA", "Los Angeles");
        RequestData requestData = new RequestData("4:31", "5:1", "Stan",
                3, restaurant, 6, "2017-12-06");
        String requestID = "-L-kxRDFoFoSovybwWbd";

        Request request1 = new Request(requesterID, requestData, requestID);

        //Request2
        requesterID = "10215291050977832";
        restaurant = new Restaurant("ChIJ4zrX7Ii8woARDS6Jslp0j3Y", "Yoshinoya UCLA",
                "+1 310-443-3681", "617 Charles E Young Dr E, Los Angeles, CA 90095, USA", "Los Angeles");
        requestData = new RequestData("8:31", "9:07", "Dude",
                2, restaurant, 5, "2017-12-07");
        requestID = "-L-kxZPJaG9yq5JPbdNo";

        Request request2 = new Request(requesterID, requestData, requestID);
        request2.setRequestState("Completed");

        //Request3
        requesterID = "1758223867534643";
        restaurant = new Restaurant("ChIJqc1-Q5u4woARAzOO1E824cY", "Hae Jang Chon Korean BBQ Restaurant",
                "+1 213-389-8777", "3821 W 6th St, Los Angeles, CA 90020, USA", "Los Angeles");
        requestData = new RequestData("20:35", "23:42", "Daisy",
                4, restaurant, 10, "2017-12-08");
        requestID = "-L-mOdIwjUINbLReWCDy";

        Request request3 = new Request(requesterID, requestData, requestID);
        request3.setRequestState("Paid");

        //Request4
        requesterID = "1499052203496028";
        restaurant = new Restaurant("ChIJ9TQdLHW7woARYBaUqZ2X6N8", "Tatsu Ramen",
                "+1 310-684-2889", "2123 Sawtelle Blvd, Los Angeles, CA 90025, USA", "Los Angeles");
        requestData = new RequestData("19:6", "20:36", "KAREN",
                6, restaurant, 10000000, "2017-12-09");
        requestID = "-L-nDzF3z1canFyiBzBl";

        Request request4 = new Request(requesterID, requestData, requestID);
        request4.setRequestState("Claimed");

        Request[] test_requests = new Request[]{request1, request2, request3, request4};

        return test_requests;
    }

    @org.junit.Before
    public void setup() throws IOException, ParseException
    {
        instrumentationCtx = InstrumentationRegistry.getContext();
        test_FirebaseManager = new FirebaseManager(instrumentationCtx);
        jsonFile = file_to_json();
        test_requests = create_test_requests();
    }

    @Test
    public void test_parseJson() throws IOException, ParseException
    {
        int i = 1;
        for (Object o : jsonFile)
        {
            Request temp_request = test_FirebaseManager.parseRequestJson((DataSnapshot) o);
            System.out.println("Checking that request " + i + " matches first request in JsonArray.");
            assertTrue(temp_request.getRequestID().equals(test_requests[i-1].getRequestID()));
            assertTrue(temp_request.getRequesterID().equals(test_requests[i-1].getRequesterID()));
            assertTrue(temp_request.getReserverID().equals(test_requests[i-1].getReserverID()));
            assertTrue(temp_request.getRequestState().equals(test_requests[i-1].getRequestState()));
            assertTrue(temp_request.getRequestData().getCreationDate().equals(test_requests[i-1].getRequestData().getCreationDate()));
            assertTrue(temp_request.getRequestData().getStartTime().equals(test_requests[i-1].getRequestData().getStartTime()));
            assertTrue(temp_request.getRequestData().getEndTime().equals(test_requests[i-1].getRequestData().getEndTime()));
            assertTrue(temp_request.getRequestData().getPartyName().equals(test_requests[i-1].getRequestData().getPartyName()));
            assertTrue(temp_request.getRequestData().getNumParty() == (test_requests[i-1].getRequestData().getNumParty()));
            assertTrue(temp_request.getRequestData().getPayment() == (test_requests[i-1].getRequestData().getPayment()));
            assertTrue(temp_request.getRequestData().getRestaurant().getRestaurantID().equals(test_requests[i-1].getRequestData().getRestaurant().getRestaurantID()));
            assertTrue(temp_request.getRequestData().getRestaurant().getRestaurantName().equals(test_requests[i-1].getRequestData().getRestaurant().getRestaurantName()));
            assertTrue(temp_request.getRequestData().getRestaurant().getRestaurantPhoneNumber().equals(test_requests[i-1].getRequestData().getRestaurant().getRestaurantPhoneNumber()));
            assertTrue(temp_request.getRequestData().getRestaurant().getRestaurantAddress().equals(test_requests[i-1].getRequestData().getRestaurant().getRestaurantAddress()));
            assertTrue(temp_request.getRequestData().getRestaurant().getRestaurantCity().equals(test_requests[i-1].getRequestData().getRestaurant().getRestaurantCity()));
            i++;
        }
    }
}
*/