package com.a0xffffffff.dinesum;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.service.voice.VoiceInteractionSession;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.Profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RequestFeedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RequestFeedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequesterFragment extends Fragment {

    public static final String TAG = "RequesterFragment";
    private ArrayList<Request> mRequests;
    private ListView mListView;
    private ProgressBar mProgressBar;
    private RequestAdapter mAdapter;
    private OnFragmentInteractionListener mListener;
    private Request clickedRequest;
    private boolean mDataReady = false;

    public RequesterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RequestFeedFragment.
     */
    public static RequesterFragment newInstance() {
        RequesterFragment fragment = new RequesterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_requester, container, false);

        mListView = (ListView) view.findViewById(R.id.requester_request_list);
        mProgressBar = (ProgressBar) view.findViewById(R.id.requester_progressBar);

        if(mDataReady) {
            initListView();
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
        }
        return view;
    }

    public void updateListView() {
        if (mRequests == null) {
            mRequests = new ArrayList<>();
        }
        if (mRequests.equals(RequestTracker.getInstance().getUserRequests())) {
            return;
        }
        mRequests.clear();
        mRequests.addAll(RequestTracker.getInstance().getUserRequests());
        mAdapter.notifyDataSetChanged();
    }

    public void initListView() {
        mDataReady = true;
        if (mProgressBar == null) {
            return;
        }

        mProgressBar.setVisibility(View.GONE);

        mRequests = RequestTracker.getInstance().getUserRequests();

        mAdapter = new RequestAdapter(getActivity(), mRequests);
        mListView.setAdapter(mAdapter);
        mListView.setVisibility(View.VISIBLE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Request request = (Request) adapterView.getItemAtPosition(i);
                final RequestData requestData = request.getRequestData();
                clickedRequest = request;
                final String requesterId = request.getRequesterID();
                final String reserverId = request.getReserverID();
                final String requestPrice = "$" + (int) request.getRequestData().getPayment();
                final String requestStatus = request.getRequestState();
                final String restaurantId = requestData.getRestaurant().getRestaurantID();
                final String restaurantName = requestData.getRestaurant().getRestaurantName();
                final String restaurantAddress = requestData.getRestaurant().getRestaurantAddress();
                final String restaurantNumber = requestData.getRestaurant().getRestaurantPhoneNumber();
                final String requestName = requestData.getPartyName();
                final String requestPartySize = "Party of " + Integer.toString(requestData.getNumParty());
                final String requestTime = requestData.getStartTime() + " - " + requestData.getEndTime();

                final Intent intent = new Intent(getActivity(), RequestInfoActivity.class);
                intent.putExtra("requesterId", requesterId);
                intent.putExtra("reserverId", reserverId);
                intent.putExtra("requestPrice", requestPrice);
                intent.putExtra("requestStatus", requestStatus);
                intent.putExtra("restaurantId", restaurantId);
                intent.putExtra("restaurantName", restaurantName);
                intent.putExtra("restaurantAddress", restaurantAddress);
                intent.putExtra("restaurantNumber", restaurantNumber);
                intent.putExtra("requestName", requestName);
                intent.putExtra("partySize", requestPartySize);
                intent.putExtra("requestTime", requestTime);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1: // remove request
                mListener.onDeleteRequest(TAG, clickedRequest);
                break;
            case 2: // mark as pending
                clickedRequest.setRequestState(RequestState.PENDING);
                clickedRequest.setReserverID("");
                mListener.onUpdateRequestState(TAG, clickedRequest);
                break;
            case 6: // complete
                clickedRequest.setRequestState(RequestState.COMPLETED);
                mListener.onUpdateRequestState(TAG, clickedRequest);
                break;
            default:
                Log.e(TAG, "wrong resultCode");
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String TAG);
        void onUpdateRequestState(String TAG, Request request);
        void onDeleteRequest(String TAG, Request request);
    }

    public ArrayList<Request> getRequests() { return mRequests; }
}
