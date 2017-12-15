package com.a0xffffffff.dinesum;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Adapter that converts a list of Request objects to be displayed in a Fragment
 */
public class RequestAdapter extends ArrayAdapter<Request> {
    private ArrayList<Request> mRequests;
    private Context mContext;
    ViewHolder holder;

    public RequestAdapter(Context context, ArrayList<Request> requests) {
        super(context, 0, requests);
        mRequests = requests;
        mContext = context;
    }

    private class ViewHolder {
        ImageView restaurantImage;
        TextView restaurantName;
        TextView restaurantAddress;
        TextView requestPrice;
        TextView requestStatus;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the request item for this position
        final Request request = getItem(position);
        holder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_request, parent, false);
            holder = new ViewHolder();

            holder.restaurantImage = (ImageView) convertView.findViewById(R.id.item_request_restaurant_image);
            holder.restaurantName = (TextView) convertView.findViewById(R.id.item_request_restaurant_name);
            holder.restaurantAddress = (TextView) convertView.findViewById(R.id.item_request_restaurant_address);
            holder.requestPrice = (TextView) convertView.findViewById(R.id.item_request_price);
            holder.requestStatus = (TextView) convertView.findViewById(R.id.item_request_status);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.restaurantName.setText(request.getRequestData().getRestaurant().getRestaurantName());
        holder.restaurantAddress.setText(request.getRequestData().getRestaurant().getRestaurantAddress());
        holder.requestPrice.setText("$" + Integer.toString((int)request.getRequestData().getPayment()));
        holder.requestStatus.setText(request.getRequestState());

        return convertView;
    }
}
