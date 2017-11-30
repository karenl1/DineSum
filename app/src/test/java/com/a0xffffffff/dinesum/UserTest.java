package com.a0xffffffff.dinesum;

import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private String requesterID1 = "requester1";
    private Restaurant restaurant1 = new Restaurant("restaurantID1", "restaurantName1",
            "555-5555", "1200 Pennsylvania Ave NW", "Los Angeles");
    private RequestData requestData1 = new RequestData("11:00", "12:00", "party1",
            2, restaurant1, 2.50);
    private String requestID1 = "request1";
    Request request1 = new Request(requesterID1, requestData1, requestID1);

    private String requesterID2 = "requester2";
    private Restaurant restaurant2 = new Restaurant("restaurantID2", "restaurantName2",
            "777-7777", "330 De Neve Dr", "Los Angeles");
    private RequestData requestData2 = new RequestData("2:00", "3:00", "party2",
            4, restaurant2, 3.00);
    private String requestID2 = "request2";
    Request request2 = new Request(requesterID2, requestData2, requestID2);

    User testUser;

    @Before
    public void setup(){
        testUser = new User();
    }


}