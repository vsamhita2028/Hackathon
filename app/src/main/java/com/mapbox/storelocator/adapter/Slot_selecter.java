package com.mapbox.storelocator.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
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
        value1 = extras.getString("store");
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
                Intent saraswati = new Intent(getApplicationContext(), Trialpage.class);
                startActivity(saraswati);

                 /*reference = FirebaseDatabase.getInstance().getReference().child("Store_names").child(value1);
                Toast toast = Toast.makeText(getApplicationContext(),value1, Toast.LENGTH_LONG);
                toast.show();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){

                            String count = dataSnapshot.child(slot).getValue().toString();
                            Toast toast = Toast.makeText(getApplicationContext(), count, Toast.LENGTH_LONG);
                            toast.show();
                            int n=Integer.parseInt(count);
                            if(n<=5){
                                n=n+1;
                                reference.child(slot).setValue(String.valueOf(n));
                                Intent i = new Intent(getApplicationContext(), Last_page.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                radioButton.setVisibility(View.INVISIBLE);
                                Toast lol = Toast.makeText(getApplicationContext(), "The selected slot is full", Toast.LENGTH_LONG);
                                lol.show();

                            }
                            //Log.d("Meow",count);
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/
                //update();


            }
        });


    }
    public  void update(){

    }

    }

