package com.mapbox.storelocator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Fragment2 extends Fragment {

    TextView next;
    TextView back;
    ViewPager viewPager;
    Button done;
    EditText user;
    EditText phone;


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        next=view.findViewById(R.id.slideTwoNext);
        back=view.findViewById(R.id.slideTwoBack);
        done=view.findViewById(R.id.done);
        user=view.findViewById(R.id.user);
        phone = view.findViewById(R.id.ph);


       // SharedPreferences sharedPref = context.getSharedPreferences(
                //"Mysp", Context.MODE_PRIVATE);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("User",user.getText().toString());
                editor.putString("Phone",phone.getText().toString());
                editor.commit();
                /*SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("User",user.getText().toString());
                editor.putString("Phone",phone.getText().toString());
                editor.commit();*/
            }
        });
        viewPager =getActivity().findViewById(R.id.viewPager);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                Intent i = new Intent(getActivity().getApplicationContext(), Dashboard.class);
                startActivity(i);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        return view;


    }


}
