package com.a0xffffffff.dinesum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.Profile;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Activity that displays information and actions of a Request
 */
public class RequestInfoActivity extends Activity {
    @BindView(R.id.restaurant_info_restaurant_picture) ImageView restaurantImage;
    @BindView(R.id.request_info_restaurant_name) TextView restaurantName;
    @BindView(R.id.request_info_restaurant_address) TextView restaurantAddress;
    @BindView(R.id.request_info_restaurant_number) TextView restaurantNumber;
    @BindView(R.id.request_info_price) TextView requestPrice;
    @BindView(R.id.request_info_status) TextView requestStatus;
    @BindView(R.id.request_info_no_reserver) TextView noReserver;
    @BindView(R.id.request_info_rating_layout) LinearLayout ratingLayout;
    @BindView(R.id.request_info_rating) TextView rating;
    @BindView(R.id.request_info_name) TextView partyName;
    @BindView(R.id.request_info_party_size) TextView partySize;
    @BindView(R.id.request_info_time) TextView requestTime;
    @BindView(R.id.request_info_requester_profile_picture) ImageView requesterProfilePicture;
    @BindView(R.id.request_info_button2) Button button2;
    @BindView(R.id.request_info_button3) Button button3;

    String requestViewingState;
    String requesterId;
    String reserverId;
    boolean isRequester;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_info);
        ButterKnife.bind(this);
        populateFields(getIntent());
    }

    /**
     * Sets up the fields of the Activity based on Request information passed through the intent
     * @param intent the request information passed through from the MainActivity
     */
    private void populateFields(Intent intent) {
        final String requesterIdIntent = intent.getStringExtra("requesterId");
        final String reserverIdIntent = intent.getStringExtra("reserverId");
        final String requestPriceIntent = intent.getStringExtra("requestPrice");
        final String requestStatusIntent = intent.getStringExtra("requestStatus");
        final String restaurantIdIntent = intent.getStringExtra("restaurantId");
        final String restaurantNameIntent = intent.getStringExtra("restaurantName");
        final String restaurantAddressIntent = intent.getStringExtra("restaurantAddress");
        final String restaurantNumberIntent = intent.getStringExtra("restaurantNumber");
        final String requestNameIntent = intent.getStringExtra("requestName");
        final String partySizeIntent = intent.getStringExtra("partySize");
        final String requestTimeIntent = intent.getStringExtra("requestTime");

        requesterId = requesterIdIntent;
        reserverId = reserverIdIntent;
        requestStatus.setText(requestStatusIntent);

        final Handler handler = new Handler();
        final StringBuilder stringBuilder = new StringBuilder();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Picasso.with(getApplicationContext()).load(stringBuilder.toString()).into(restaurantImage);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                final HttpClient httpClient = new DefaultHttpClient();
                final String placesUrl = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + restaurantIdIntent + "&key=" + getResources().getString(R.string.google_places_api_key);;
                final HttpGet httpGet = new HttpGet(placesUrl);
                try {
                    HttpResponse response = httpClient.execute(httpGet);
                    String serverResponse = EntityUtils.toString(response.getEntity());
                    JSONObject jsonObject = new JSONObject(serverResponse);
                    JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("photos");
                    String photoReference = jsonArray.getJSONObject(0).getString("photo_reference");

                    String photoReferenceUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoReference + "&key=" + getResources().getString(R.string.google_places_api_key);;
                    stringBuilder.append(photoReferenceUrl);
                    handler.postDelayed(runnable, 0);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        restaurantName.setText(restaurantNameIntent);
        restaurantAddress.setText(restaurantAddressIntent);
        restaurantNumber.setText(restaurantNumberIntent);
        requestPrice.setText(requestPriceIntent);
        partyName.setText(requestNameIntent);
        partySize.setText(partySizeIntent);
        requestTime.setText(requestTimeIntent);

        isRequester = requesterId.equals(Profile.getCurrentProfile().getId());

        if (isRequester) {
            if (requestStatusIntent.equals(RequestState.PENDING)) { // show the no reserver message
                noReserver.setVisibility(View.VISIBLE);
//                partyName.setVisibility(View.GONE);
//                partySize.setVisibility(View.GONE);
//                requestTime.setVisibility(View.GONE);
                requesterProfilePicture.setVisibility(View.GONE);
                ratingLayout.setVisibility(View.GONE);
            }

            else { // show the reserver info
                noReserver.setVisibility(View.GONE);
                final String profilePicUrl = "https://graph.facebook.com/" + reserverId + "/picture?type=square";
                Picasso.with(getApplicationContext()).load(profilePicUrl).into(requesterProfilePicture);
                ratingLayout.setVisibility(View.VISIBLE);
                long points = UserTracker.getInstance().getUserPointsFromDatabase(reserverId);
                rating.setText(Integer.toString((int) points));
//                partyName.setVisibility(View.GONE);
//                partySize.setVisibility(View.GONE);
//                requestTime.setVisibility(View.GONE);
            }
        } else { // show the requester info
            noReserver.setVisibility(View.GONE);
            final String profilePicUrl = "https://graph.facebook.com/" + requesterId + "/picture?type=square";
            Picasso.with(getApplicationContext()).load(profilePicUrl).into(requesterProfilePicture);
            ratingLayout.setVisibility(View.VISIBLE);
            long points = UserTracker.getInstance().getUserPointsFromDatabase(requesterId);
            rating.setText(Integer.toString((int) points));
//            partyName.setText(requestNameIntent);
//            partySize.setText(partySizeIntent);
//            requestTime.setText(requestTimeIntent);
        }

        switch (requestStatusIntent) {
            case RequestState.PENDING:
                requestViewingState = isRequester ?
                        RequestViewingState.REQUESTER_PENDING :
                        RequestViewingState.RESERVER_PENDING;
                break;
            case RequestState.CLAIMED:
                requestViewingState = isRequester ?
                        RequestViewingState.REQUESTER_CLAIMED :
                        RequestViewingState.RESERVER_CLAIMED;
                break;
            case RequestState.COMPLETED:
                requestViewingState = isRequester ?
                        RequestViewingState.REQUESTER_COMPLETED :
                        RequestViewingState.RESERVER_COMPLETED;
                break;
            case RequestState.PAID:
                requestViewingState = isRequester ?
                        RequestViewingState.REQUESTER_PAID :
                        RequestViewingState.RESERVER_PAID;
                break;
        }

        initializeButtons();
    }

    private void initializeButtons() {
        switch (requestViewingState) {
            case RequestViewingState.REQUESTER_PENDING:
                button2.setText(getResources().getString(R.string.remove_request));
                button3.setVisibility(View.GONE);
                break;
            case RequestViewingState.REQUESTER_CLAIMED:
                button2.setText(getResources().getString(R.string.mark_as_pending));
                button3.setText(getResources().getString(R.string.complete));
                break;
            case RequestViewingState.REQUESTER_COMPLETED:
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                break;
            case RequestViewingState.REQUESTER_PAID:
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                break;
            case RequestViewingState.RESERVER_PENDING:
                button2.setText(getResources().getString(R.string.claim));
                button3.setVisibility(View.GONE);
                break;
            case RequestViewingState.RESERVER_CLAIMED:
                button2.setText(getResources().getString(R.string.unclaim_request));
                button3.setVisibility(View.GONE);
                break;
            case RequestViewingState.RESERVER_COMPLETED:
                button2.setText(getResources().getString(R.string.mark_as_paid));
                button3.setVisibility(View.GONE);
                break;
            case RequestViewingState.RESERVER_PAID:
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                break;
        }
    }

    // Always cancel button
    @OnClick(R.id.request_info_button1)
    public void button1Clicked() {
        finish();
    }

    @OnClick(R.id.request_info_button2)
    public void button2Clicked() {
        switch (requestViewingState) {
            case RequestViewingState.REQUESTER_PENDING:
                setResult(1); // remove request
                break;
            case RequestViewingState.REQUESTER_CLAIMED:
                setResult(2); // mark as pending
                break;
            case RequestViewingState.RESERVER_PENDING:
                setResult(3); // claim request
                break;
            case RequestViewingState.RESERVER_CLAIMED:
                setResult(4); // un-claim request
                break;
            case RequestViewingState.RESERVER_COMPLETED:
                setResult(5); // mark as paid
                break;
        }
        finish();
    }

    // Always complete button
    @OnClick(R.id.request_info_button3)
    public void button3Clicked() {
        setResult(6); // complete request
        finish();
    }

    @OnClick(R.id.request_info_requester_profile_picture)
    public void requesterProfilePictureClicked() {
        String url;
        if (isRequester) {
            url = "http://www.facebook.com/" + reserverId;
        } else {
            url = "http://www.facebook.com/" + requesterId;
        }
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
