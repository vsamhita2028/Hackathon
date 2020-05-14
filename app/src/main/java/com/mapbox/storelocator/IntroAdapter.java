package com.mapbox.storelocator;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new Fragment1();
            case 1:

                return new Fragment2();
            default:
                return null;
        }

}


    @Override
    public int getCount() {
        return 2;
    }
}
