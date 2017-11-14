package com.a0xffffffff.dinesum;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FirebaseManagerTest {
    @Test
    public void writesRequestSuccessfully(){
        String userID = "1743480282342335";
        RequestData testRequestData = new RequestData();
        FirebaseManager firebaseManager = FirebaseManager.getInstance();
        Request testRequest = new Request(userID, testRequestData);
        firebaseManager.writeRequest(testRequest);
    }
}