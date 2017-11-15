package com.a0xffffffff.dinesum;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RequestTest {

    private String requesterID = "requesterID";
    private Restaurant restaurant = new Restaurant("restaurantID", "restaurantName",
            "555-5555", "1200 Pennsylvania Ave NW", "Los Angeles");
    private RequestData requestData = new RequestData("11:00", "12:00", "party",
            2, restaurant, 2.50);
    private String requestID = "request";



    @Test
    public void requestCreatedSuccessfully() {
        Request request1 = new Request(requesterID, requestData, requestID);

        System.out.println("Checking that request1 is not null");
        assertTrue(request1 != null);
    }

    @Test
    public void testRequestID() {
        Request request1 = new Request(requesterID, requestData, requestID);

        System.out.println("Checking initial requestID value");
        assertTrue(request1.getRequestID() == "request");
    }

    @Test
    public void testRequesterID() {
        Request request1 = new Request(requesterID, requestData, requestID);

        System.out.println("Checking initial requesterID value");
        assertTrue(request1.getRequesterID() == "requesterID");

        String newRequesterID = "newRequesterID";
        System.out.println("Setting requesterID to new value");
        request1.setRequesterID(newRequesterID);
        assertTrue(request1.getRequesterID() == "newRequesterID");
    }

    @Test
    public void testReserverID() {
        Request request1 = new Request(requesterID, requestData, requestID);

        System.out.println("Checking initial reserverID value");
        assertTrue(request1.getReserverID() == null);

        String newReserverID = "newReserverID";
        System.out.println("Setting reserverID to new value");
        request1.setReserverID(newReserverID);
        assertTrue(request1.getReserverID() == "newReserverID");
    }

    @Test
    public void testRequestState() {
        Request request1 = new Request(requesterID, requestData, requestID);

        System.out.println("Checking initial requestState value (PENDING)");
        assertTrue(request1.getRequestState() == RequestState.PENDING);

        System.out.println("Setting requestState to next state (CLAIMED)");
        request1.setRequestState(RequestState.CLAIMED);
        assertTrue(request1.getRequestState() == RequestState.CLAIMED);

        System.out.println("Setting requestState to next state (COMPLETED)");
        request1.setRequestState(RequestState.COMPLETED);
        assertTrue(request1.getRequestState() == RequestState.COMPLETED);

        System.out.println("Setting requestState to next state (PAID)");
        request1.setRequestState(RequestState.PAID);
        assertTrue(request1.getRequestState() == RequestState.PAID);
    }

    @Test
    public void testRequestData() {
        Request request1 = new Request(requesterID, requestData, requestID);


        Restaurant newRestaurant = new Restaurant("newRestaurantID", "newRestaurantName",
                "777-7777", "330 De Neve Dr.", "Los Angeles");
        RequestData newRequestData = new RequestData("1:00", "2:00", "newParty",
                3, newRestaurant, 3.00);

        System.out.println("Checking initial requestData value");
        assertTrue(request1.getRequestData() == requestData);

        System.out.println("Setting requestData to new data");
        request1.setRequestData(newRequestData);
        assertTrue(request1.getRequestData() == newRequestData);
    }

}
