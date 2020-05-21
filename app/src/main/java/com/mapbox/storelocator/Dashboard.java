package com.mapbox.storelocator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    ImageView user, map, detail, chatbot;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        user = findViewById(R.id.prevuserdet);
        map = findViewById(R.id.mapbutton);
        detail = findViewById(R.id.reservedslot);
        chatbot = findViewById(R.id.chatbot_button);
        map.setClickable(true);
        user.setClickable(true);
        detail.setClickable(true);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UserdetailsPage.class);
                startActivity(i);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(in);
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Forcard bla =new Forcard();
                //bla.setvalues();
                setContentView(R.layout.activity_bookedslot);
                ArrayList<ExampleItem> exampleList = new ArrayList<>();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String u=preferences.getString("Store", null);
                String p=preferences.getString("Slot", null);
                exampleList.add(new ExampleItem( u, p));

                mRecyclerView = findViewById(R.id.recyclerViewslots);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getApplicationContext());
                mAdapter = new ExampleAdapter(exampleList);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

            }
        });
        /*chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Chatbot.class);
                startActivity(intent);
            }
        });*/

    }
}
