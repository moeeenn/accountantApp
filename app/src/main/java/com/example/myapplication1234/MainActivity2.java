package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity2 extends AppCompatActivity {

    IRetrofit iRetrofit;
    String category;
    EditText categoryText;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        categoryText=findViewById(R.id.cat_text);
        btn_add=findViewById(R.id.btn_cat);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category=categoryText.getText().toString();

                iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL).create(IRetrofit.class);

                iRetrofit.add_category(category).enqueue(new Callback<ResponseBody>() {
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
                        Toast.makeText(MainActivity2.this, "خطا در برقراری ارتباط", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}