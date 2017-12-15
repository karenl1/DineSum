package com.a0xffffffff.dinesum;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Utility class to obtain the city containing latitude and longitude coordinates.
 */
public final class LocationUtil {

    /**
     * Initial activity that determines whether to begin Login or go to MainActivity
     * @param context The application's context
     * @param latlng The Latitude and Longitude coordinates
     * @return the name of city containing the coordinates
     */
    public static String getCityFromLatLng(Context context, LatLng latlng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = geocoder.getFromLocation(latlng.latitude, latlng.longitude, 1);
        } catch (IOException e) {
            // swallows the exception for now
        }

        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0).getLocality();
        }
        return null;
    }
}
