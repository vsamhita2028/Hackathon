package com.mapbox.storelocator.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.storelocator.R;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;
import static com.mapbox.storelocator.util.StringConstants.SELECTED_THEME;

/**
 * Activity for picking the specific map theme to view
 */
public class ThemePickerActivity extends AppCompatActivity {


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // This contains the MapView in XML and needs to be called after the access token is configured.

  }

}
