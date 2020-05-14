package com.mapbox.storelocator;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.os.Build;

import com.mapbox.storelocator.activity.MapActivity;

import static android.app.PendingIntent.getActivity;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getSharedPreferences("Openerval", Context.MODE_PRIVATE);
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
        if(sharedPref.getBoolean("introval",false)){
            Intent i = new Intent(this.getApplicationContext(), MapActivity.class);
            startActivity(i);
            finish();
        }

        //Intent i = new Intent(this.getApplicationContext(), IntroAdapter.class);
        //startActivity(i);
        viewPager = findViewById(R.id.viewPager);
        IntroAdapter adapter =new IntroAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("introval",true);
        editor.commit();

    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){

            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    closeNow();
                }
                return;
            }
        }
    }
     private void closeNow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            finishAffinity();
        }

        else
        {
            finish();
        }
    }
    private boolean restorePrefData(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        Boolean isopend = pref.getBoolean("IntroOpened",false);
        return isopend;
    }


}
