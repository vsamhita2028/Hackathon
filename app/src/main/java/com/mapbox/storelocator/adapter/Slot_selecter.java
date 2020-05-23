package com.mapbox.storelocator.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mapbox.storelocator.R;

import static android.app.PendingIntent.getActivity;

public class Slot_selecter extends AppCompatActivity {
    private Button submit;
    private RadioGroup rg;
    RelativeLayout rl;
    private TextView str;
    private static RadioButton radioButton;
    private static String slot;
    private static String count;
    DatabaseReference reference;
    static String value1;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_selector);
        Bundle extras = getIntent().getExtras();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        value1=preferences.getString("Store", null);
        str = findViewById(R.id.str_name);
        str.setText(value1);
        submit = findViewById(R.id.sammy);
        rg = new RadioGroup(this);
        int start, end;
        start = 10;
        end = 0;
        rl = (RelativeLayout) findViewById(R.id.rel);
        int c = 0;
        for (int i = 0; i < 18; i++) {
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
                reference = FirebaseDatabase.getInstance().getReference().child("Store_names").child(value1);
                Toast toast = Toast.makeText(getApplicationContext(),value1, Toast.LENGTH_LONG);
                toast.show();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Slot",slot);
                editor.commit();
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (Integer.parseInt(dataSnapshot.child(slot).getValue().toString())>5)
                        {
                            radioButton.setVisibility(View.INVISIBLE);
                            Toast lol = Toast.makeText(getApplicationContext(), "Sorry, the maximum limit has been reached. It is dangerous for more people to go there", Toast.LENGTH_LONG);
                            lol.show();
                        }
                        else
                        {
                            reference.child(slot).setValue(String.valueOf(Integer.parseInt(dataSnapshot.child(slot).getValue().toString())+1));
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Intent i = new Intent(getApplicationContext(), Trialpage.class);
                startActivity(i);
                finish();
            }
        });
    }
}