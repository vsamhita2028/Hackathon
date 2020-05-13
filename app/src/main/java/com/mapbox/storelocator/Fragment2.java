package com.mapbox.storelocator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mapbox.storelocator.activity.MapActivity;
import com.mapbox.storelocator.R;

import android.content.Intent;
import static com.mapbox.storelocator.util.StringConstants.SELECTED_THEME;


public class Fragment2 extends Fragment {

    TextView next;
    TextView back;
    ViewPager viewPager;
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
        viewPager =getActivity().findViewById(R.id.viewPager);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                Intent i = new Intent(getActivity().getApplicationContext(), MapActivity.class);
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
