package com.a0xffffffff.dinesum;

import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private long initialUserPoints = 50;
    private long updatePoints = 25;
    private String testUserID = "testUserID";

    User testUser;

    @Before
    public void setup(){
        testUser = new User();
    }


    @Test
    public void testUserCreatedSuccessfully() {
        assertTrue(testUser.getPoints() == 50);
    }

    @Test
    public void testUserIDSetSuccessfully() {
        testUser.setUserID(testUserID);
        assertTrue(testUser.getUserID().equals(testUserID));
    }

    @Test
    public void testUserPointsSetSuccessfully() {
        testUser.setPoints(updatePoints);
        assertTrue(testUser.getPoints() == updatePoints);
    }

}