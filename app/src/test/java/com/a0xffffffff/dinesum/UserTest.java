package com.a0xffffffff.dinesum;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private String requesterID1 = "requester1";
    private RequestData requestData1 = new RequestData("11:00", "12:00", "party1",
            2, "restaurant1", 2.50);
    Request request1 = new Request(requesterID1, requestData1);

    private String requesterID2 = "requester2";
    private RequestData requestData2 = new RequestData("2:00", "3:00", "party2",
            4, "restaurant2", 3.00);
    Request request2 = new Request(requesterID2, requestData2);


    @Test
    public void userCreatedSuccessfully(){
        User testUser = new User();

        assertTrue(testUser.getRequests() != null && testUser.getRequests().isEmpty());
        assertTrue(testUser.getReservations() != null && testUser.getReservations().isEmpty());
    }

    @Test
    public void requestAddedRemovedSuccessfully() {

    }

    @Test
    public void reservationAddedRemovedSuccessfully() {

    }

    @Test
    public void facebookIDSuccessful() {
        
    }

}
