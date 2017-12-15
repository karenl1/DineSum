package com.a0xffffffff.dinesum;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.AccessToken;

/**
 * Initial activity that determines whether to begin Login or go to MainActivity
 */
public class LaunchActivity extends AppCompatActivity {

    /**
     * Creation of this initial activity will open MainActivity or LoginActivity.
     * If the user has previously logged in, MainActivity will open. Otherwise, LoginActivity opens.
     * @param savedInstanceState This is saved state from previously opening the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        if (AccessToken.getCurrentAccessToken() != null) {
            intent = new Intent(this, MainActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }

}
