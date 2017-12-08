package com.a0xffffffff.dinesum;

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

public class FirebaseManagerTest {
    JSONParser parser = new JSONParser();
    JSONArray jsonFile;

    public JSONArray file_to_json() throws IOException, ParseException
    {
        jsonFile = (JSONArray) parser.parse(new FileReader("dinesum-requests-export.json"));
        return jsonFile;
    }
    public Request[] create_test_requests()
    {
        //Request1
        String requesterID = "10215291050977832";
        Restaurant restaurant = new Restaurant("ChIJ__Jsmgq7woARhmKIwcjfc0o", "Popcorn Chicken Taste of Taiwan",
                "+1 424-832-3076", "2224 Sawtelle Blvd, Los Angeles, CA 90064, USA", "Los Angeles");
        RequestData requestData = new RequestData("4:31", "5:1", "Stan",
                3, restaurant, 6);
        String requestID = "-L-kxRDFoFoSovybwWbd";

        Request request1 = new Request(requesterID, requestData, requestID);

        //Request2
        requesterID = "10215291050977832";
        restaurant = new Restaurant("ChIJ4zrX7Ii8woARDS6Jslp0j3Y", "Yoshinoya UCLA",
                "+1 310-443-3681", "617 Charles E Young Dr E, Los Angeles, CA 90095, USA", "Los Angeles");
        requestData = new RequestData("8:31", "9:07", "Dude",
                2, restaurant, 5);
        requestID = "-L-kxZPJaG9yq5JPbdNo";

        Request request2 = new Request(requesterID, requestData, requestID);

        //Request3
        requesterID = "1758223867534643";
        restaurant = new Restaurant("ChIJqc1-Q5u4woARAzOO1E824cY", "Hae Jang Chon Korean BBQ Restaurant",
                "+1 213-389-8777", "3821 W 6th St, Los Angeles, CA 90020, USA", "Los Angeles");
        requestData = new RequestData("20:35", "23:42", "Daisy",
                4, restaurant, 10);
        requestID = "-L-mOdIwjUINbLReWCDy";

        Request request3 = new Request(requesterID, requestData, requestID);

        //Request4
        requesterID = "1499052203496028";
        restaurant = new Restaurant("ChIJ9TQdLHW7woARYBaUqZ2X6N8", "Tatsu Ramen",
                "+1 310-684-2889", "2123 Sawtelle Blvd, Los Angeles, CA 90025, USA", "Los Angeles");
        requestData = new RequestData("19:6", "20:36", "KAREN",
                6, restaurant, 10000000);
        requestID = "-L-nDzF3z1canFyiBzBl";

        Request request4 = new Request(requesterID, requestData, requestID);

        Request[] test_requests = new Request[]{request1, request2, request3, request4};

        return test_requests;
    }
    @Test
    public Request test_parseJson() throws IOException, ParseException
    {
        JSONArray jsonFile = file_to_json();
        for (Object o : jsonFile)
        {

        }
        System.out.println("Checking that request1 is not null");
        assertTrue(request1 != null);
    }
}