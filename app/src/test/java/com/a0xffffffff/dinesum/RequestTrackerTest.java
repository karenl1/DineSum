package com.a0xffffffff.dinesum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit Tests for the RequestTracker class
 */
public class RequestTrackerTest {

    private String requesterID1 = "requesterID1";
    private Restaurant restaurant1 = new Restaurant("restaurantID1", "restaurantName1",
            "555-5555", "1200 Pennsylvania Ave NW", "Washington D.C.");
    private RequestData requestData1 = new RequestData("11:00", "12:00", "party1",
            2, restaurant1, 2.50);
    private String requestID1 = "request1";
    private Request request1 = new Request(requesterID1, requestData1, requestID1);


    private String requesterID2 = "requesterID2";
    private Restaurant restaurant2 = new Restaurant("restaurantID2", "restaurantName2",
            "777-7777", "330 De Neve Dr", "Los Angeles");
    private RequestData requestData2 = new RequestData("11:00", "12:00", "party2",
            2, restaurant2, 2.50);
    private String requestID2 = "request2";
    private Request request2 = new Request(requesterID2, requestData2, requestID2);

    RequestTracker requestTracker = RequestTracker.getInstance();

    ArrayList<Request> requestsList = new ArrayList<Request>();
    ArrayList<Request> emptyRequests = new ArrayList<Request>();

    /**
     * Set up the test Requests Lists with new requests.
     */
    @Before
    public void setUp() {
        requestsList.add(request1);
        requestsList.add(request2);
    }

    /**
     * Check that the NearbyRequestsList is set and retrieved successfully.
     */
    @Test
    public void testNearbyRequestsList() {

        System.out.println("Checking Nearby Requests List");
        requestTracker.setNearbyRequests(requestsList);
        assertTrue(requestTracker.getNearbyRequests().size() == 2);
        requestTracker.setNearbyRequests(emptyRequests);
        assertTrue(requestTracker.getNearbyRequests().size() == 0);
    }

    /**
     * Check that the AllRequestsList is set and retrieved successfully.
     */
    @Test
    public void testAllRequestsList() {
        System.out.println("Checking All Requests List");
        requestTracker.setAllRequests(requestsList);
        assertTrue(requestTracker.getAllRequests().size() == 2);
        requestTracker.setAllRequests(emptyRequests);
        assertTrue(requestTracker.getAllRequests().size() == 0);
    }

    /**
     * Check that the UserRequestsList is set and retrieved successfully.
     */
    @Test
    public void testUserRequestsList() {
        System.out.println("Checking User Requests List");
        requestTracker.setUserRequests(requestsList);
        assertTrue(requestTracker.getUserRequests().size() == 2);
        requestTracker.setUserRequests(emptyRequests);
        assertTrue(requestTracker.getUserRequests().size() == 0);
    }

    /**
     * Check that the UserReservationsList is set and retrieved successfully.
     */
    @Test
    public void testUserReservationsList() {
        System.out.println("Checking User Reservations List");
        requestTracker.setUserReservations(requestsList);
        assertTrue(requestTracker.getUserReservations().size() == 2);
        requestTracker.setUserReservations(emptyRequests);
        assertTrue(requestTracker.getUserReservations().size() == 0);
    }

    /**
     * Reset the test requestsList to an empty list.
     */
    @After
    public void teardown() {
        requestsList.clear();
    }

}