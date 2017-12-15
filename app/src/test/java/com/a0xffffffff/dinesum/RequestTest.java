package com.a0xffffffff.dinesum;

import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for the Request Class.
 */
public class RequestTest {

    private String requesterID = "requesterID";
    private Restaurant restaurant = new Restaurant("restaurantID", "restaurantName",
            "555-5555", "1200 Pennsylvania Ave NW", "Los Angeles");
    private RequestData requestData = new RequestData("11:00", "12:00", "party",
            2, restaurant, 2.50);
    private String requestID = "request";

    private String requesterID2 = "requesterID2";
    private Restaurant restaurant2 = new Restaurant("restaurantID2", "restaurantName2",
            "888-5555", "2400 Wilshire Ave", "Los Angeles");
    private RequestData requestData2 = new RequestData("11:00", "12:00", "party",
            2, restaurant2, 2.50, "2017-12-13");

    private Request request1;
    private Request request2;

    /**
     * Initialize request objects.
     */
    @Before
    public void setup()
    {
        request1 = new Request(requesterID, requestData, requestID);
        request2 = new Request(requesterID2, requestData2);
    }

    /**
     * Ensure that requests are not null.
     */
    @Test
    public void testRequestCreatedSuccessfully() {
        System.out.println("Checking that request1 is not null");
        assertTrue(request1 != null);
        System.out.println("Checking that request2 is not null");
        assertTrue(request2 != null);
    }

    /**
     * Check that request1 has been initialized with correct requestID.
     * Test getRequestID() and setRequestID() functions.
     */
    @Test
    public void testRequestID() {
        System.out.println("Checking initial requestID value for request1");
        assertTrue(request1.getRequestID().equals("request"));

        System.out.println("Setting requestID value to new value for request1");
        request1.setRequestID("newrequest");
        assertTrue(request1.getRequestID().equals("newrequest"));
    }

    /**
     * Check that requests have been initialized with correct requesterIDs.
     * Test getRequesterID() and setRequesterID() functions.
     */
    @Test
    public void testRequesterID() {
        System.out.println("Checking initial requesterID value for request1");
        assertTrue(request1.getRequesterID().equals("requesterID"));

        System.out.println("Checking initial requesterID value for request2");
        assertTrue(request2.getRequesterID().equals("requesterID2"));

        String newRequesterID = "newRequesterID";
        String newRequesterID2 = "newRequesterID2";
        System.out.println("Setting requesterID to new value");
        request1.setRequesterID(newRequesterID);
        request2.setRequesterID(newRequesterID2);
        assertTrue(request1.getRequesterID().equals("newRequesterID"));
        assertTrue(request2.getRequesterID().equals("newRequesterID2"));
    }

    /**
     * Check that requests' reserverIDs are initialized to "".
     * Test getReserverID() and setReserverID() functions.
     */
    @Test
    public void testReserverID() {
        System.out.println("Checking initial reserverID value for request1");
        assertTrue(request1.getReserverID().equals(""));

        System.out.println("Checking initial reserverID value for request2");
        assertTrue(request2.getReserverID().equals(""));

        String newReserverID = "newReserverID";
        String newReserverID2 = "newReserverID2";
        System.out.println("Setting reserverID to new value");
        request1.setReserverID(newReserverID);
        request2.setReserverID(newReserverID2);
        assertTrue(request1.getReserverID().equals("newReserverID"));
        assertTrue(request2.getReserverID().equals("newReserverID2"));
    }

    /**
     * Check that requests' requestState values are initialized to PENDING.
     * Test getRequestState() and setRequestState() functions.
     */
    @Test
    public void testRequestState() {
        System.out.println("Checking initial requestState value (PENDING) for request1");
        assertTrue(request1.getRequestState().equals(RequestState.PENDING));

        System.out.println("Checking initial requestState value (PENDING) for request2");
        assertTrue(request2.getRequestState().equals(RequestState.PENDING));

        System.out.println("Setting requestState to next state (CLAIMED) for request1");
        request1.setRequestState(RequestState.CLAIMED);
        assertTrue(request1.getRequestState().equals(RequestState.CLAIMED));

        System.out.println("Setting requestState to next state (CLAIMED) for request2");
        request2.setRequestState(RequestState.CLAIMED);
        assertTrue(request2.getRequestState().equals(RequestState.CLAIMED));

        System.out.println("Setting requestState to next state (COMPLETED) for request1");
        request1.setRequestState(RequestState.COMPLETED);
        assertTrue(request1.getRequestState().equals(RequestState.COMPLETED));

        System.out.println("Setting requestState to next state (COMPLETED) for request2");
        request2.setRequestState(RequestState.COMPLETED);
        assertTrue(request2.getRequestState().equals(RequestState.COMPLETED));

        System.out.println("Setting requestState to next state (PAID) for request1");
        request1.setRequestState(RequestState.PAID);
        assertTrue(request1.getRequestState().equals(RequestState.PAID));

        System.out.println("Setting requestState to next state (PAID) for request2");
        request2.setRequestState(RequestState.PAID);
        assertTrue(request2.getRequestState().equals(RequestState.PAID));
    }

    /**
     * Test getRequestData() and RequestData's corresponding getStartTime(),
     * getEndTime(), getPartyName(), getNumParty(), and getPayment() functions.
     * Test getRestaurant() and Restaurant's corresponding getRestaurantID(),
     * getRestaurantName(), getRestaurantPhoneNumber(), getRestaurantAddress(),
     * and getRestaurantCity() functions.
     * Test setRequestData() function.
     */
    @Test
    public void testRequestData() {
        Restaurant newRestaurant = new Restaurant("newRestaurantID", "newRestaurantName",
                "777-7777", "330 De Neve Dr.", "Los Angeles");
        RequestData newRequestData = new RequestData("1:00", "2:00", "newParty",
                3, newRestaurant, 3.00);

        Restaurant newRestaurant2 = new Restaurant("newRestaurantID2", "newRestaurantName2",
                "999-7777", "456 Sunset Rd", "Los Angeles");
        RequestData newRequestData2 = new RequestData("1:00", "2:00", "newParty",
                5, newRestaurant2, 7.00, "2017-12-15");

        System.out.println("Checking initial requestData value for request1");
        assertTrue(request1.getRequestData().getStartTime().equals(requestData.getStartTime()));
        assertTrue(request1.getRequestData().getEndTime().equals(requestData.getEndTime()));
        assertTrue(request1.getRequestData().getPartyName().equals(requestData.getPartyName()));
        assertTrue(request1.getRequestData().getNumParty() == requestData.getNumParty());
        assertTrue(request1.getRequestData().getPayment() == requestData.getPayment());
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantID().equals(requestData.getRestaurant().getRestaurantID()));
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantName().equals(requestData.getRestaurant().getRestaurantName()));
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantPhoneNumber().equals(requestData.getRestaurant().getRestaurantPhoneNumber()));
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantAddress().equals(requestData.getRestaurant().getRestaurantAddress()));
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantCity().equals(requestData.getRestaurant().getRestaurantCity()));


        System.out.println("Setting requestData to new data for request1");
        request1.setRequestData(newRequestData);
        assertTrue(request1.getRequestData().getStartTime().equals(newRequestData.getStartTime()));
        assertTrue(request1.getRequestData().getEndTime().equals(newRequestData.getEndTime()));
        assertTrue(request1.getRequestData().getPartyName().equals(newRequestData.getPartyName()));
        assertTrue(request1.getRequestData().getNumParty() == newRequestData.getNumParty());
        assertTrue(request1.getRequestData().getPayment() == newRequestData.getPayment());
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantID().equals(newRequestData.getRestaurant().getRestaurantID()));
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantName().equals(newRequestData.getRestaurant().getRestaurantName()));
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantPhoneNumber().equals(newRequestData.getRestaurant().getRestaurantPhoneNumber()));
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantAddress().equals(newRequestData.getRestaurant().getRestaurantAddress()));
        assertTrue(request1.getRequestData().getRestaurant().getRestaurantCity().equals(newRequestData.getRestaurant().getRestaurantCity()));

        System.out.println("Checking initial requestData value for request2");
        assertTrue(request2.getRequestData().getStartTime().equals(requestData2.getStartTime()));
        assertTrue(request2.getRequestData().getEndTime().equals(requestData2.getEndTime()));
        assertTrue(request2.getRequestData().getPartyName().equals(requestData2.getPartyName()));
        assertTrue(request2.getRequestData().getCreationDate().equals(requestData2.getCreationDate()));
        assertTrue(request2.getRequestData().getNumParty() == requestData2.getNumParty());
        assertTrue(request2.getRequestData().getPayment() == requestData2.getPayment());
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantID().equals(requestData2.getRestaurant().getRestaurantID()));
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantName().equals(requestData2.getRestaurant().getRestaurantName()));
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantPhoneNumber().equals(requestData2.getRestaurant().getRestaurantPhoneNumber()));
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantAddress().equals(requestData2.getRestaurant().getRestaurantAddress()));
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantCity().equals(requestData2.getRestaurant().getRestaurantCity()));


        System.out.println("Setting requestData to new data for request2");
        request2.setRequestData(newRequestData2);
        assertTrue(request2.getRequestData().getStartTime().equals(newRequestData2.getStartTime()));
        assertTrue(request2.getRequestData().getEndTime().equals(newRequestData2.getEndTime()));
        assertTrue(request2.getRequestData().getPartyName().equals(newRequestData2.getPartyName()));
        assertTrue(request2.getRequestData().getCreationDate().equals(newRequestData2.getCreationDate()));
        assertTrue(request2.getRequestData().getNumParty() == newRequestData2.getNumParty());
        assertTrue(request2.getRequestData().getPayment() == newRequestData2.getPayment());
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantID().equals(newRequestData2.getRestaurant().getRestaurantID()));
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantName().equals(newRequestData2.getRestaurant().getRestaurantName()));
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantPhoneNumber().equals(newRequestData2.getRestaurant().getRestaurantPhoneNumber()));
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantAddress().equals(newRequestData2.getRestaurant().getRestaurantAddress()));
        assertTrue(request2.getRequestData().getRestaurant().getRestaurantCity().equals(newRequestData2.getRestaurant().getRestaurantCity()));
    }
}
