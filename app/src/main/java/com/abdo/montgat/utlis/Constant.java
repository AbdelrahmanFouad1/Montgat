package com.abdo.montgat.utlis;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.fragment.app.Fragment;

import com.abdo.montgat.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Constant {

    public static final String MY_PREFERENCES = "nightModePref";
    public static final String KEY_NIGHT_MODE = "isNightMode";

    // shared preferences
    private static SharedPreferences sharedPreferences;

    private static DatabaseReference databaseReference;

    public static DatabaseReference initRef ()
    {
        if(databaseReference == null)
        {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return databaseReference;
    }


    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }


    public static void saveUid(Activity activity, String id){

        sharedPreferences = activity.getSharedPreferences("SOCIAL", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Uid", id);
        editor.apply();
    }

    public static String getUid(Activity activity) {

        sharedPreferences = activity.getSharedPreferences("SOCIAL", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Uid", "empty");
    }

    public static void replaceFragment(Fragment from, Fragment to) {
        from
                .requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, to)
                .addToBackStack(null)
                .commit();
    }


    public static void replaceSideMenu(Fragment from, Fragment to) {
        from
                .requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Container_fl_category, to)
                .addToBackStack(null)
                .commit();
    }


    public static void saveNightModeState(Activity activity, Boolean nightMode){

        sharedPreferences = activity.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_NIGHT_MODE, nightMode);
        editor.apply();


    }

    public static Boolean getNightModeState(Activity activity) {

        sharedPreferences = activity.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_NIGHT_MODE, false);
    }


}
