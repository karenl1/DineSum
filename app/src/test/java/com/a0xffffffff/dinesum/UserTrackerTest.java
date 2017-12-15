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
 * JUnit Tests for the UserTracker class
 */
public class UserTrackerTest {

    private String userID1 = "userID1";
    private long userPoints1 = 40;
    private User user1 = new User();

    private String userID2 = "userID2";
    private long userPoints2 = 70;
    private User user2 = new User();


    UserTracker userTracker = UserTracker.getInstance();

    ArrayList<User> usersList = new ArrayList<User>();
    ArrayList<User> emptyUsers = new ArrayList<User>();

    /**
     * Set up the test Users Lists with new users.
     */
    @Before
    public void setUp() {

        user1.setUserID(userID1);
        user1.setPoints(userPoints1);

        user2.setUserID(userID2);
        user2.setPoints(userPoints2);

        usersList.add(user1);
        usersList.add(user2);
    }

    /**
     * Check that the AllUsersList is set and retrieved successfully.
     */
    @Test
    public void testAllUsersList() {
        System.out.println("Checking All Users List");
        userTracker.setAllUsers(usersList);
        assertTrue(userTracker.getAllUsers().size() == 2);
        userTracker.setAllUsers(emptyUsers);
        assertTrue(userTracker.getAllUsers().size() == 0);
    }

    /**
     * Check that the points of a specific User ID can be retrieved successfully.
     */
    @Test
    public void testGetUserPoints() {
        System.out.println("Checking Get User Points");
        userTracker.setAllUsers(usersList);

        long points = userTracker.getUserPointsFromDatabase(userID2);
        assertTrue(points == userPoints2);
    }

    /**
     * Check that the points of a specific User ID is updated successfully.
     */
    @Test
    public void testUpdateUserPoints() {
        System.out.println("Checking Update User Points");
        userTracker.setAllUsers(usersList);

        long updatePoints = 10;
        User user = userTracker.updateUsersPoints(userID2, updatePoints);

        assertTrue(user.getUserID().equals(userID2));
        assertTrue(user.getPoints() == userPoints2 + updatePoints);
    }

    /**
     * Reset the test usersList to an empty list.
     */
    @After
    public void teardown() {
        usersList.clear();
    }
}
