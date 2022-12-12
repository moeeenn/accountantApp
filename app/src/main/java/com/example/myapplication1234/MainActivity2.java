package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication1234.Config.Config;
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

public class MainActivity2 extends AppCompatActivity {

    IRetrofit iRetrofit;
    String ali;

    ArrayList<test> a=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL).create(IRetrofit.class);

        iRetrofit.add_category(ali).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String message=Config.jsonObject("message",response.body().string());
                    if (message.equals("1"))
                    {
                        Toast.makeText(MainActivity2.this, "موفقیت آمیز بود", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MainActivity2.this, "موفقیت آمیز نبود", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

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