package com.android.prince.getmyrepo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

           /*     SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
                if(!(settings.getBoolean("splash",false))){

                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("splash", true);

                    startActivity(new Intent(SplashScreen.this,EnterDeatilLayout.class));
                    finish();
                }else{

                }

                */

                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();

            }
        },5000);


    }
}
