package com.a0xffffffff.dinesum;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FirebaseManagerTest {

    @Test
    public void writesRequestSuccessfully(){
        User testUser = new User();
        RequestData testRequestData = new RequestData();
        FirebaseManager firebaseManager = FirebaseManager.getInstance();
        Request testRequest = new Request(testUser, testRequestData);
        firebaseManager.writeRequest(testRequest);
    }
}