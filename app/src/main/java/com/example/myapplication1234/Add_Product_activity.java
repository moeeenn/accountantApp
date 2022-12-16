package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

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
    Button btn_add;
    EditText product,price,num;
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
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(Add_Product_activity.this, android.R.layout.simple_spinner_dropdown_item,mylist);
//                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<get_category>> call, Throwable t) {

            }
        });

        btn_add=findViewById(R.id.btn_add_product);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=s.getSelectedItemPosition();
                id=cats.get(id).getC_id();
                product=findViewById(R.id.p_name);
                price=findViewById(R.id.p_price);
                num=findViewById(R.id.p_num);

                String product1=product.getText().toString();
                int price1=Integer.parseInt(price.getText().toString());
                int num1=Integer.parseInt((num.getText().toString()));

                iRetrofit.add_product(product1,price1,num1,id).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String message=Config.jsonObject("message",response.body().string());
                            if (message.equals("1"))
                            {
                                Toast.makeText(Add_Product_activity.this, "موفقیت آمیز بود", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(Add_Product_activity.this, "موفقیت آمیز نبود", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Add_Product_activity.this, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
                    }
                });


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