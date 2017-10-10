package com.sample.virtusademo;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by neerajt on 10/10/17.
 */

public class Utils {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String CITY = "city";

    public static  double kelvinToCelsius( double kelvin) {

        return  kelvin - 273.15;

    }

    public static void setCity(Context context,String city){

        SharedPreferences sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sharedpreferences.edit().putString(CITY,city).commit();

    }

    public static String getCity(Context context){

        SharedPreferences preferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return preferences.getString(CITY,"");

    }

    public static boolean isConnectingToInternet(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

}
