package com.a0xffffffff.dinesum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

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

    @Before
    public void setUp() {

        user1.setUserID(userID1);
        user1.setPoints(userPoints1);

        user2.setUserID(userID2);
        user2.setPoints(userPoints2);

        usersList.add(user1);
        usersList.add(user2);
    }

    @Test
    public void testAllUsersList() {
        System.out.println("Checking All Users List");
        userTracker.setAllUsers(usersList);
        assertTrue(userTracker.getAllUsers().size() == 2);
        userTracker.setAllUsers(emptyUsers);
        assertTrue(userTracker.getAllUsers().size() == 0);
    }

    @Test
    public void testGetUserPoints() {
        System.out.println("Checking Get User Points");
        userTracker.setAllUsers(usersList);


    }


    @After
    public void teardown() {
        usersList.clear();
    }
}
