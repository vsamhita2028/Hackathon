package com.mapbox.storelocator.adapter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mapbox.storelocator.R;

public class Last_page extends AppCompatActivity {
private Button b;
private TextView t;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String u=preferences.getString("User", null);
        String p=preferences.getString("Phone", null);
        t.setText(u);


    }
}
