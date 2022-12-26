package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication1234.Adapters.FactorsAdapter;
import com.example.myapplication1234.Config.Config;
import com.example.myapplication1234.Model.factors;
import com.example.myapplication1234.Retrofit.IRetrofit;
import com.example.myapplication1234.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class factors_activity extends AppCompatActivity {

    RecyclerView recycler;
    ArrayList<factors> array=new ArrayList<>();
    FactorsAdapter factorsAdapter;
    IRetrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factors);

        retrofit = RetrofitClient.getRetrofit(Config.BASE_URL).create(IRetrofit.class);

        recycler=findViewById(R.id.recycler);
        retrofit.get_factors().enqueue(new Callback<List<factors>>() {
            @Override
            public void onResponse(Call<List<factors>> call, Response<List<factors>> response) {
                array.addAll(response.body());
                factorsAdapter=new FactorsAdapter(factors_activity.this,array);
                recycler.setAdapter(factorsAdapter);
                recycler.setLayoutManager(new LinearLayoutManager(factors_activity.this,
                        RecyclerView.VERTICAL,false));
                recycler.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<factors>> call, Throwable t) {

            }
        });

    }
}