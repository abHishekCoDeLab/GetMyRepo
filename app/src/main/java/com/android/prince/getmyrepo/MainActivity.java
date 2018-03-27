package com.android.prince.getmyrepo;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.prince.getmyrepo.fragment.RepoList;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    TextView search;

    private static final String LIFECYCLE_CALLBACK_TEXT_KEY = "callbacks";

    String searchStr  = "Android";
    String sortBy = "Star";

    ImageView setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting = (ImageView)findViewById(R.id.SettingButton);
        search = (TextView)findViewById(R.id.TextSearch);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if(!(sharedPreferences.getString("interest","Android").isEmpty())){
            searchStr = sharedPreferences.getString("interest","Android");
        }

        if(!(sharedPreferences.getString("list_pref","star").isEmpty())){
            sortBy = sharedPreferences.getString("list_pref","Android");
        }

        setupPreferenced();

        if (savedInstanceState != null){
            if(savedInstanceState.containsKey(LIFECYCLE_CALLBACK_TEXT_KEY)){
                String allPreviousLifecycleCallbacks = savedInstanceState.getString(LIFECYCLE_CALLBACK_TEXT_KEY);
                searchStr = allPreviousLifecycleCallbacks;
            }
        }

        RepoList repoList = new RepoList();
        repoList.getContextMain(MainActivity.this);
        repoList.getSearch(searchStr,sortBy);

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentHome,repoList).commit();

        search.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    searchStr = search.getText().toString();

                    RepoList repoList = new RepoList();
                    repoList.getContextMain(MainActivity.this);
                    repoList.getSearch(searchStr,sortBy);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome,repoList).commit();
                    search.setText("");
                    return true;
                }
                return false;
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(LIFECYCLE_CALLBACK_TEXT_KEY, searchStr);
    }

    public void setupPreferenced(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
       /* if(key.equalsIgnoreCase("show_bass")){
            Log.e("Value of checkbox : ",String.valueOf(sharedPreferences.getBoolean(key,true)));
        }

        else if(key.equalsIgnoreCase("list_pref")){
            Log.e("Value of list pref : ",String.valueOf(sharedPreferences.getString(key,"red")));
        }

        else if(key.equalsIgnoreCase("Interest")){
            Log.e("Value of edit pref : ",String.valueOf(sharedPreferences.getString(key,"Android")));
        }   */

       if(key.equalsIgnoreCase("interest")){

           String string = String.valueOf(sharedPreferences.getString(key,"Android"));

           if(string.equalsIgnoreCase("")){

           }else {
               searchStr = string;
           }

       }else if(key.equalsIgnoreCase("list_pref")){

           String string = String.valueOf(sharedPreferences.getString(key,"star"));

           if(string.equalsIgnoreCase("")){

           }else {
               sortBy = string;
           }
       }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
