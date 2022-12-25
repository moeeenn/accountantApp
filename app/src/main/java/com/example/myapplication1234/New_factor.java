package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication1234.Adapters.Product_list_adapter;
import com.example.myapplication1234.Config.Config;
import com.example.myapplication1234.Model.get_category;
import com.example.myapplication1234.Model.get_products;
import com.example.myapplication1234.Retrofit.IRetrofit;
import com.example.myapplication1234.Retrofit.RetrofitClient;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_factor extends AppCompatActivity {

    int categoryId;
    Spinner s,s1;
    IRetrofit iRetrofit;
    ArrayList<get_products> arrayGetProducts=new ArrayList<>();
    ArrayList<get_category> arrayGetCategory=new ArrayList<>();
    String[] mylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_factor);

        s = findViewById(R.id.spinner_pr);
        s1 = findViewById(R.id.spinner_cat);

        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL).create(IRetrofit.class);

        iRetrofit.set_product_list().enqueue(new Callback<List<get_products>>() {
            @Override
            public void onResponse(Call<List<get_products>> call, Response<List<get_products>> response) {
                arrayGetProducts.addAll(response.body());
                List <String> products=new ArrayList<String>();
                for (int i = 0; i <arrayGetProducts.size(); i++) {
                    products.add(arrayGetProducts.get(i).getP_name()) ;

                    ArrayAdapter<String> adapter =new ArrayAdapter<String>(New_factor.this, android.R.layout.simple_spinner_dropdown_item,products);
                    s.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<get_products>> call, Throwable t) {
                Toast.makeText(New_factor.this, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
            }
        });

        iRetrofit.set_category().enqueue(new Callback<List<get_category>>() {
            @Override
            public void onResponse(Call<List<get_category>> call, Response<List<get_category>> response) {
                arrayGetCategory.addAll(response.body());
                mylist=new String[arrayGetCategory.size()];
                for (int i=0;i<arrayGetCategory.size();i++){
                    mylist[i]=arrayGetCategory.get(i).getC_name();

                }
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(New_factor.this, android.R.layout.simple_spinner_dropdown_item,mylist);
                s1.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<get_category>> call, Throwable t) {
                Toast.makeText(New_factor.this, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
            }
        });



        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                categoryId=arrayGetCategory.get(i).getC_id();
                int p=0;
                List <String> products=new ArrayList<String>();
                for (int j=0;j<arrayGetProducts.size();j++){
                    if (arrayGetProducts.get(j).getC_id()==categoryId){
                        products.add(arrayGetProducts.get(j).getP_name());
                    }
                }
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(New_factor.this, android.R.layout.simple_spinner_dropdown_item,products);
                s.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





    }


}