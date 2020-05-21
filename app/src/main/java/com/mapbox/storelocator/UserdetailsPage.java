package com.mapbox.storelocator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class UserdetailsPage extends AppCompatActivity {

    TextView userdet,ph;
    Button change;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String value1=preferences.getString("User", null);
        String p=preferences.getString("Phone", null);
        userdet=findViewById(R.id.userdet_uer);
        ph=findViewById(R.id.userdet_ph);
        //change=findViewById(R.id.update);
        userdet.setText(value1);
        ph.setText(p);

    }
}
