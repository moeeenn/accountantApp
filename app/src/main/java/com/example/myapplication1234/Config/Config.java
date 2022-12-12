package com.example.myapplication1234.Config;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class Config {

    public static final String TAG = "Eyrsa";
    public static final String BASE_URL = "https://hamraahmobile.ir/App/";



    public static Boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    public static String jsonObject(String key, String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return "false";
        }
    }

}
