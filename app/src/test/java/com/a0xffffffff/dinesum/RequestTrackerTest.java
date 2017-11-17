package com.a0xffffffff.dinesum;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

public class RequestTrackerTest {


    @Test
    public void RequestTrackerWrittenSuccessfully(){
        RequestTracker requestTracker = RequestTracker.getInstance();

        String requesterID1 = "requesterID1";
        Restaurant restaurant1 = new Restaurant("restaurantID1", "restaurantName1",
                "555-5555", "1200 Pennsylvania Ave NW", "Washington D.C.");
        RequestData requestData1 = new RequestData("11:00", "12:00", "party1",
                2, restaurant1, 2.50);
        String requestID1 = "request1";
        Request request1 = new Request(requesterID1, requestData1, requestID1);

        String requesterID2 = "requesterID2";
        Restaurant restaurant2 = new Restaurant("restaurantID2", "restaurantName2",
                "777-7777", "330 De Neve Dr", "Los Angeles");
        RequestData requestData2 = new RequestData("11:00", "12:00", "party2",
                2, restaurant2, 2.50);
        String requestID2 = "request2";
        Request request2 = new Request(requesterID2, requestData2, requestID2);


        ArrayList<Request> twoRequests = new ArrayList<Request>();
        twoRequests.add(request1);
        twoRequests.add(request2);

        ArrayList<Request> emptyRequests = new ArrayList<Request>();

        System.out.println("Checking Nearby Requests List");
        requestTracker.setNearbyRequests(twoRequests);
        assertTrue(requestTracker.getNearbyRequests() == twoRequests);
        assertTrue(requestTracker.getNearbyRequests().size() == 2);
        requestTracker.setNearbyRequests(emptyRequests);
        assertTrue(requestTracker.getNearbyRequests() == emptyRequests);
        assertTrue(requestTracker.getNearbyRequests().size() == 0);

        System.out.println("Checking All Requests List");
        requestTracker.setAllRequests(twoRequests);
        assertTrue(requestTracker.getAllRequests() == twoRequests);
        assertTrue(requestTracker.getAllRequests().size() == 2);
        requestTracker.setAllRequests(emptyRequests);
        assertTrue(requestTracker.getAllRequests() == emptyRequests);
        assertTrue(requestTracker.getAllRequests().size() == 0);

        System.out.println("Checking User Requests List");
        requestTracker.setUserRequests(twoRequests);
        assertTrue(requestTracker.getUserRequests() == twoRequests);
        assertTrue(requestTracker.getUserRequests().size() == 2);
        requestTracker.setUserRequests(emptyRequests);
        assertTrue(requestTracker.getUserRequests() == emptyRequests);
        assertTrue(requestTracker.getUserRequests().size() == 0);

        System.out.println("Checking User Reservations List");
        requestTracker.setUserReservations(twoRequests);
        assertTrue(requestTracker.getUserReservations() == twoRequests);
        assertTrue(requestTracker.getUserReservations().size() == 2);
        requestTracker.setUserReservations(emptyRequests);
        assertTrue(requestTracker.getUserReservations() == emptyRequests);
        assertTrue(requestTracker.getUserReservations().size() == 0);

        System.out.println("Checking Filter All Requests");
        requestTracker.setAllRequests(twoRequests);
        ArrayList<Request> filteredRequests = requestTracker.filterAllRequestsByCity("Los Angeles");
        assertTrue(filteredRequests.size() == 1);
        assertTrue(filteredRequests.get(0) == request2);
    }

}