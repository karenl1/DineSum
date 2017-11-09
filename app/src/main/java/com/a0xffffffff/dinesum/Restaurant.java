package com.a0xffffffff.dinesum;

public class Restaurant {
    private String mRestaurantName;
    private String mRestaurantAddress;
    private String mRestaurantCity;

    public Restaurant() {

    }

    public Restaurant(String restaurantName, String restaurantAddress, String restaurantCity) {
        mRestaurantName = restaurantName;
        mRestaurantAddress = restaurantAddress;
        mRestaurantCity = restaurantCity;
    }

    public String getRestaurantName(){
        return mRestaurantName;
    }

    public void setRestaurantName(String restaurantName){
        mRestaurantName = restaurantName;
    }

    public String getRestaurantAddress(){
        return mRestaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress){
        mRestaurantAddress = restaurantAddress;
    }

    public String getRestaurantCity(){
        return mRestaurantCity;
    }

    public void setRestaurantCity(String restaurantCity){
        mRestaurantCity = restaurantCity;
    }

}
