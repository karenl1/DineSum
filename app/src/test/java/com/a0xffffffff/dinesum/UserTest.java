package com.a0xffffffff.dinesum;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    @Test
    public void userCreatedSuccessfullyy(){
        User testUser = new User();
        RequestData testRequestData = new RequestData();
        Request testRequest = new Request(testUser, testRequestData);
    }
}
