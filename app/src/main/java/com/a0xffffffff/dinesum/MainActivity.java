package com.a0xffffffff.dinesum;

import android.app.Fragment;
import android.content.Intent;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.facebook.Profile;

/**
 * Central Activity that controls all fragments and UI.
 */
public class MainActivity extends AppCompatActivity
        implements MainFragment.OnFragmentInteractionListener,
            NewRequestFragment.OnFragmentInteractionListener,
            FirebaseManager.OnDataReadyListener,
            RequestFeedFragment.OnFragmentInteractionListener,
            RequesterFragment.OnFragmentInteractionListener,
            ReserverFragment.OnFragmentInteractionListener {
    private static final String SELECTED_ITEM = "arg_selected_item";

    private BottomNavigationViewEx mBottomNav;
    private ViewPager mViewPager;
    private Toolbar mToolbar;

    private SparseIntArray items;
    private List<Fragment> fragments;

    protected GeoDataClient mGeoDataClient;
    protected PlaceDetectionClient mPlaceDetectionClient;

    private FirebaseManager mFirebaseManager;
    private NewRequestFragment mAddFragment;
    private RequestFeedFragment mHomeFragment;
    private RequesterFragment mRequesterFragment;
    private ReserverFragment mReserverFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.vp);

        initView();
        initData();
        initGooglePlacesAPI();
        initEvent();
        initFirebaseData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.app_bar_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                // TODO: logout gracefully
                break;
            case R.id.action_refresh:
                if (mHomeFragment != null)
                    mHomeFragment.updateListView();
                if (mRequesterFragment != null)
                    mRequesterFragment.updateListView();
                if (mReserverFragment != null)
                    mReserverFragment.updateListView();
                break;
        }
        return true;
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        mBottomNav = (BottomNavigationViewEx) findViewById(R.id.bnve);
        mBottomNav.enableAnimation(false);
        mBottomNav.enableShiftingMode(false);
        mBottomNav.enableItemShiftingMode(false);
        mBottomNav.setTextVisibility(false);
        mBottomNav.setItemHeight(200); //px
        mBottomNav.setIconSize(40, 40); //dp
        mBottomNav.setCurrentItem(0);
        updateToolbarText(0);
    }

    private void initData() {
        fragments = new ArrayList<>(4);
        items = new SparseIntArray(4);

        mRequesterFragment = RequesterFragment.newInstance();
        mReserverFragment = ReserverFragment.newInstance();
        mHomeFragment = RequestFeedFragment.newInstance();
        mAddFragment = NewRequestFragment.newInstance();

        fragments.add(mRequesterFragment);
        fragments.add(mReserverFragment);
        fragments.add(mHomeFragment);
        fragments.add(mAddFragment);

        items.put(R.id.menu_requester, 0);
        items.put(R.id.menu_acceptor, 1);
        items.put(R.id.menu_home, 2);
        items.put(R.id.menu_add, 3);

        mViewPager.setAdapter(new VpAdapter(getFragmentManager(), fragments));
    }

    public void onNearbyRequestsReady() {
        mHomeFragment.initListView();
    }

    public void onRequesterRequestsReady() {
        mRequesterFragment.initListView();
    }

    public void onReserverRequestsReady() {
        mReserverFragment.initListView();
    }

    public void onUsersReady() { initCreateNewUserIfFirstTime(); }

    private void initEvent() {
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationViewEx.OnNavigationItemSelectedListener() {
            private int previousPosition = -1;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int position = items.get(item.getItemId());
                if (previousPosition != position) {
                    previousPosition = position;
                    mViewPager.setCurrentItem(position);
                    updateToolbarText(position);
                }

                return true;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNav.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initFirebaseData() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userFbId");
        if (userID == null) {
            userID = Profile.getCurrentProfile().getId();
        }
        // TODO: get the userCity using their Android location
        String userCity = "Los Angeles";
        mFirebaseManager = new FirebaseManager(this);
        mFirebaseManager.attachInitialFirebaseListeners(userID, userCity);
        mFirebaseManager.attachFirebaseListeners(userID, userCity);
    }

    private void initCreateNewUserIfFirstTime() {
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userFbId");
        if (userID == null) {
            userID = Profile.getCurrentProfile().getId();
        }
        // return if already inside of user
        ArrayList<User> allUsers = UserTracker.getInstance().getAllUsers();
        Log.d("UserInit", "List Size: " + allUsers.size());

        for (User user: allUsers) {
            Log.d("UserInit", "From List: " + user.getUserID());
            if (user.getUserID().equals(userID))
                return;
        }
        Log.d("UserInit", "Hi" + userID);
        User newUser = new User();
        newUser.setUserID(userID);
        mFirebaseManager.writeUser(newUser);
    }

    private void updateToolbarText(int index) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            switch (index) {
                case 0:
                    actionBar.setTitle("Sent Requests");
                    break;
                case 1:
                    actionBar.setTitle("Claimed Requests");
                    break;
                case 2:
                    actionBar.setTitle("Nearby Requests");
                    break;
                case 3:
                    actionBar.setTitle("New Requests");
                    break;
            }
        }
    }

    private void initGooglePlacesAPI() {
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
    }

    public void onFragmentInteraction(String TAG) {
        // TODO
    }

    public void onSubmitButtonPressed(String TAG, Request request) {
        if (TAG.equals(NewRequestFragment.TAG)) {
            request.setRequestID(mFirebaseManager.getNewRequestID());
            mFirebaseManager.writeRequest(request);
            mViewPager.setCurrentItem(2);
        }
    }

    @Override
    public void onUpdateRequestState(String TAG, Request request) {
        mFirebaseManager.writeRequest(request);
    }

    @Override
    public void onDeleteRequest(String TAG, Request request) {
        mFirebaseManager.deleteRequest(request);
    }

    @Override
    public void onUpdateUserPoints(String TAG, User user) {
        mFirebaseManager.writeUser(user);
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
        }
    }

    public NewRequestFragment getAddFragment() { return mAddFragment; }

    public RequestFeedFragment getHomeFragement() { return mHomeFragment; }

    public RequesterFragment getRequesterFragment() { return mRequesterFragment; }

    public ReserverFragment getReserverFragement() { return mReserverFragment; }

}

