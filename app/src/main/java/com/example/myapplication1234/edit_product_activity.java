package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplication1234.Config.Config;
import com.example.myapplication1234.Model.get_category;
import com.example.myapplication1234.Retrofit.IRetrofit;
import com.example.myapplication1234.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_product_activity extends AppCompatActivity {

    IRetrofit iRetrofit;
    ArrayList<get_category> cats=new ArrayList<>();
    String[] mylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();
        int p_Id=bundle.getInt("p_id");
        int c_Id=bundle.getInt("c_id");
        String p_name=bundle.getString("p_name");
        int p_price=bundle.getInt("p_price");
        int p_num=bundle.getInt("p_num");


        Spinner s = findViewById(R.id.spinner_cat3);
        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL).create(IRetrofit.class);

        iRetrofit.set_category().enqueue(new Callback<List<get_category>>() {
            @Override
            public void onResponse(Call<List<get_category>> call, Response<List<get_category>> response) {
                cats.addAll(response.body());
                mylist=new String[cats.size()];
                for (int i=0;i<cats.size();i++){
                    mylist[i]=cats.get(i).getC_name();


                }
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(edit_product_activity.this, android.R.layout.simple_spinner_dropdown_item,mylist);
//                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s.setAdapter(adapter);

                for (int i=0;i<cats.size();i++){
                    if (cats.get(i).getC_id()==(c_Id)){
                        s.setSelection(i);
                    }

                }
            }
            @Override
            public void onFailure(Call<List<get_category>> call, Throwable t) {

            }
        });



    }
}