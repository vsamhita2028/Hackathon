package com.mapbox.storelocator.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mapbox.storelocator.R;
import com.mapbox.storelocator.activity.MapActivity;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class Slot_selecter extends AppCompatActivity {
    private Button submit;
    private RadioGroup rg;
    RelativeLayout rl;
    private TextView str;
    private static RadioButton radioButton;
    private static String slot;
    private static String count;
    static DatabaseReference reference;
    static String value1;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_selector);
        Bundle extras = getIntent().getExtras();
        value1 = extras.getString("store");
        str = findViewById(R.id.str_name);
        str.setText(value1);
        submit = findViewById(R.id.submit);
        rg = new RadioGroup(this);
        int start, end;
        start = 9;
        end = 0;
        rl = (RelativeLayout) findViewById(R.id.rel);
        int c = 0;
        for (int i = 0; i < 22; i++) {
            RadioButton rb1 = new RadioButton(this);
            if (c == 0) {
                rb1.setText(start + ":00" + " to " + start + ":30");
                c = 1;
                rg.addView(rb1);
                rg.setOrientation(RadioGroup.VERTICAL);
            } else {
                end = start + 1;
                rb1.setText(start + ":30" + " to " + end + ":00");
                c = 0;
                start++;
                rg.addView(rb1);
                rg.setOrientation(RadioGroup.VERTICAL);
            }

        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) RelativeLayout.LayoutParams.WRAP_CONTENT, (int) RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 10;
        params.topMargin = 20;
        rg.setLayoutParams(params);
        rl.addView(rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) findViewById(checkedId);
                slot = radioButton.getText().toString();

            }
        });

        // Database validation
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 reference = FirebaseDatabase.getInstance().getReference("Store_names");
                Query checkstore = reference.orderByChild("Store_name").equalTo(value1);
                checkstore.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){

                            count = dataSnapshot.child(value1).child(slot).getValue(String.class);
                            Toast toast = Toast.makeText(getApplicationContext(), count, Toast.LENGTH_LONG);
                            toast.show();
                            //Log.d("Meow",count);
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                update();

            }
        });


    }
    public  void update(){
        int n=Integer.parseInt(count);
        if(n<5){
            int add_val=Integer.parseInt(count);
            add_val=add_val+1;
            reference.child(value1).child(slot).setValue(String.valueOf(add_val));
            Toast toast = Toast.makeText(getApplicationContext(), "The selected slot is updated", Toast.LENGTH_LONG);
            toast.show();
            //Intent i = new Intent(getApplicationContext(), Last_page.class);
            // startActivity(i);
        }
        else{
            radioButton.setVisibility(View.INVISIBLE);
            Toast toast = Toast.makeText(getApplicationContext(), "The selected slot is full", Toast.LENGTH_LONG);
            toast.show();

        }

    }

    }

