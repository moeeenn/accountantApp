package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication1234.Adapters.Product_list_adapter;
import com.example.myapplication1234.Config.Config;
import com.example.myapplication1234.Model.get_products;
import com.example.myapplication1234.Retrofit.IRetrofit;
import com.example.myapplication1234.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class products_list_activity extends AppCompatActivity {

    ArrayList<get_products> arrayGetProducts=new ArrayList<>();
    Product_list_adapter adapter;
    RecyclerView recyclerView;
    IRetrofit iRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);


        recyclerView=findViewById(R.id.recyclerview);

        // retrofit

        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL).create(IRetrofit.class);
        iRetrofit.set_product_list().enqueue(new Callback<List<get_products>>() {
            @Override
            public void onResponse(Call<List<get_products>> call, Response<List<get_products>> response) {
                arrayGetProducts.addAll(response.body());
                adapter=new Product_list_adapter(products_list_activity.this,arrayGetProducts);
                recyclerView.setLayoutManager(new LinearLayoutManager(products_list_activity.this,RecyclerView.VERTICAL,false));
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onFailure(Call<List<get_products>> call, Throwable t) {
                Toast.makeText(products_list_activity.this, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
            }
        });





    }

    public void onRestart(){

        super.onRestart();

        finish();
        overridePendingTransition(0,0);
        startActivity(getIntent());
        overridePendingTransition(0,0);

    }
}