package com.example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Size;

import com.facebook.react.ReactActivity;
import com.reactlibrary.MotionSplash;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "example";
  }

  @SuppressLint("ResourceType")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Size iconInitialSize = new Size(70, 70);
    String backgroundColor = getResources().getString(R.color.backgroundColor);
    MotionSplash.init(MainActivity.this, R.drawable.background_splash, R.id.logo_splash, iconInitialSize, backgroundColor);

    //    MotionSplash.init(MainActivity.this, R.drawable.background_splash, R.id.logo_splash, iconInitialSize, R.id.logo_background);
  }
}
