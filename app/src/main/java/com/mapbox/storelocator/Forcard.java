package com.mapbox.storelocator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Forcard extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int i=0;

    private Button btn;
    private ArrayList<ExampleItem> exampleList;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookedslot);
        createExampleList();
        buildRecyclerView();
        InsertItems();

        /*btn = findViewById(R.id.dashback);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ine = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(ine);
            }
        });*/



    }
    public void InsertItems(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String u=preferences.getString("Slot", null);
        String p=preferences.getString("Store", null);
        exampleList.add(new ExampleItem(p,u));
    }

    public void createExampleList(){

        exampleList = new ArrayList<>();
        i=exampleList.size();



    }
    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerViewslots);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mAdapter = new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
