package com.a0xffffffff.dinesum;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.facebook.Profile;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewRequestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewRequestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TEXT = "arg_text";
    private String mText;
    private OnFragmentInteractionListener mListener;
    private TextView mTextView;
    private Request mRequest;


    public NewRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    public static NewRequestFragment newInstance() {
        NewRequestFragment fragment = new NewRequestFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_TEXT, text);
//        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_newrequest, container, false);
        Button submitButton = (Button) view.findViewById(R.id.submitButton);
        final EditText editRestaurantId = (EditText) view.findViewById(R.id.editRestaurantId);
        EditText editStartTime = (EditText) view.findViewById(R.id.editStartTime);
        EditText editEndTime = (EditText) view.findViewById(R.id.editEndTime);
        final EditText editPartyName = (EditText) view.findViewById(R.id.editPartyName);
        final EditText editNumberInParty = (EditText) view.findViewById(R.id.editNumberInParty);
        final EditText editPrice = (EditText) view.findViewById(R.id.editPrice);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String restaurantId = editRestaurantId.getText().toString();
                String startTime = "22:18";
                String endTime = "23:00";
                String partyName = editPartyName.getText().toString();
                int numberInParty = Integer.parseInt(editNumberInParty.getText().toString());
                double price = Double.parseDouble(editPrice.getText().toString());
                mRequest = createNewRequest(restaurantId, startTime, endTime, partyName, numberInParty, price);
            }
        });

        return view;
    }

    public Request createNewRequest(
            String restaurantID,
            String startTime,
            String endTime,
            String partyName,
            int numParty,
            double price) {
        RequestData requestData = new RequestData(startTime, endTime, partyName, numParty,
                restaurantID, price);
        // requesterID is the user's FBID
        Request newRequest = new Request(Profile.getCurrentProfile().getId(), requestData);
        FirebaseManager.getInstance().writeRequest(newRequest);
        return newRequest;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        outState.putString(ARG_TEXT, mText);
        super.onSaveInstanceState(outState);
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
    }
}
