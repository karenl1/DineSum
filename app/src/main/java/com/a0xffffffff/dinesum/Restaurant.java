package com.a0xffffffff.dinesum;

import com.google.android.gms.location.places.Place;

/**
 * Represents a Restaurant.
 */
public class Restaurant {
    private String mRestaurantID;
    private String mRestaurantName;
    private String mRestaurantPhoneNumber;
    private String mRestaurantAddress;
    private String mRestaurantCity;

    /**
     * Creates a Restaurant instance.
     */
    public Restaurant() {

    }

    /**
     * Creates a Restaurant instance.
     * @param restaurantID The restaurant's unique ID obtained from the Google Places API.
     * @param restaurantName The restaurant's name.
     * @param restaurantPhoneNumber The restaurant's phone number.
     * @param restaurantAddress The restaurant's address.
     * @param restaurantCity The restaurant's city.
     */
    public Restaurant(
            String restaurantID,
            String restaurantName,
            String restaurantPhoneNumber,
            String restaurantAddress,
            String restaurantCity) {
        mRestaurantID = restaurantID;
        mRestaurantName = restaurantName;
        mRestaurantPhoneNumber = restaurantPhoneNumber;
        mRestaurantAddress = restaurantAddress;
        mRestaurantCity = restaurantCity;
    }

    public String getRestaurantID() {
        return mRestaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        mRestaurantID = restaurantID;
    }

    public String getRestaurantName(){
        return mRestaurantName;
    }

    public void setRestaurantName(String restaurantName){
        mRestaurantName = restaurantName;
    }

    public String getRestaurantPhoneNumber() {
        return mRestaurantPhoneNumber;
    }

    public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
        mRestaurantPhoneNumber = restaurantPhoneNumber;
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