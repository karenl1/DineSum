package com.a0xffffffff.dinesum;

public class UserStateRequester extends UserState {



    public UserStateRequester(User new_user)
    {
        super(new_user);
    }


    //To Implement
    public boolean makeRequest(Request request)
    {
        return true;
    }

    public boolean cancelRequest(Request request)
    {
        return true;
    }

    public boolean acceptReservation(Request request)
    {
        return true;
    }

    public boolean rejectReservation(Request request)
    {
        return true;
    }
}
