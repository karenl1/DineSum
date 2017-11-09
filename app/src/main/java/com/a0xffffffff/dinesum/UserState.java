package com.a0xffffffff.dinesum;


public abstract class UserState {

    private User mUser;

    public UserState(User new_user)
    {
    	mUser = new_user;
    }

    public User getUser()
    {
        return mUser;
    }
}
