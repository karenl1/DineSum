package com.a0xffffffff.dinesum;

import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * JUnit Tests for the User class
 */
public class UserTest {

    private long initialUserPoints = 50;
    private long updatePoints = 25;
    private String testUserID = "testUserID";

    User testUser;

    /**
     * Create a new default instance of the User class
     */
    @Before
    public void setup(){
        testUser = new User();
    }

    /**
     * Check that the new default instance was successfully created.
     * Check the point value fo the default instance is correct.
     */
    @Test
    public void testUserCreatedSuccessfully() {
        assertTrue(testUser.getPoints() == initialUserPoints);
    }

    /**
     * Check that the User ID of the User is set and retrieved successfully.
     */
    @Test
    public void testUserIDSetSuccessfully() {
        testUser.setUserID(testUserID);
        assertTrue(testUser.getUserID().equals(testUserID));
    }

    /**
     * Check that the User Points of the User is set and retrieved successfully.
     */
    @Test
    public void testUserPointsSetSuccessfully() {
        testUser.setPoints(updatePoints);
        assertTrue(testUser.getPoints() == updatePoints);
    }

}