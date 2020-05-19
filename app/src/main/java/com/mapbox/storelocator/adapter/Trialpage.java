package com.mapbox.storelocator.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mapbox.storelocator.R;
import com.mapbox.storelocator.MapActivity;

public class Trialpage extends AppCompatActivity {
    private Button b;
    private TextView t;
    private TextView t1;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide1);
        t=findViewById(R.id.user_name);
        t1=findViewById(R.id.phone_number);
        b=findViewById(R.id.btn);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String u=preferences.getString("User", null);
        String p=preferences.getString("Phone", null);
        t.setText(u);
        t1.setText(p);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (view.getContext(), MapActivity.class);
                startActivity(intent);
            }
        });

    }
}
