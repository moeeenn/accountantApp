package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.myapplication1234.Config.Config;
import com.example.myapplication1234.Model.get_category;
import com.example.myapplication1234.Model.test;
import com.example.myapplication1234.Retrofit.IRetrofit;
import com.example.myapplication1234.Retrofit.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class Add_Product_activity extends AppCompatActivity {


    IRetrofit iRetrofit;
    ArrayList<get_category> cats=new ArrayList<>();
    String[] mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Spinner s = findViewById(R.id.spinner_cat2);
        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL).create(IRetrofit.class);

        iRetrofit.set_category().enqueue(new Callback<List<get_category>>() {
            @Override
            public void onResponse(Call<List<get_category>> call, Response<List<get_category>> response) {
                cats.addAll(response.body());
                mylist=new String[cats.size()];
                for (int i=0;i<cats.size();i++){
                    mylist[i]=cats.get(i).getC_name();

                }
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(Add_Product_activity.this, android.R.layout.simple_spinner_item,mylist);
//                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s.setAdapter(adapter);


            }
            @Override
            public void onFailure(Call<List<get_category>> call, Throwable t) {

            }
        });


//        iRetrofit.SelectReshte().enqueue(new Callback<List<test>>() {
//            @Override
//            public void onResponse(Call<List<test>> call, Response<List<test>> response) {
//                a.addAll(response.body());
//                int id=a.get(0).getId();
//            }
//
//            @Override
//            public void onFailure(Call<List<test>> call, Throwable t) {
//
//            }
//        });

    }
}