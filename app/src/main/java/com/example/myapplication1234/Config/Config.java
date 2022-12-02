package com.example.myapplication1234.Config;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Config {

    public static final String TAG = "Eyrsa";
    public static final String BASE_URL = "http://alifeyzabadi.ir/eyrsa/api/";
    public static final String ADMIN_URL = "Admin/";



    public static Boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
