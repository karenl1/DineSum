package com.a0xffffffff.dinesum;

public class UserStateReserver extends UserState {


    public UserStateReserver(User new_user)
    {
        super(new_user);
    }

    //To Implement
    public boolean acceptRequest(Request request)
    {
        return true;
    }

    public boolean undoAcceptRequest(Request request)
    {
        return true;
    }

    public Request makeReservation(Request request)         //Class diagram says this should return Reservation, but should be request?
    {
        return request;
    }
}
