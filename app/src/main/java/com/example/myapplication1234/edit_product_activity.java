package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication1234.Config.Config;
import com.example.myapplication1234.Model.get_category;
import com.example.myapplication1234.Retrofit.IRetrofit;
import com.example.myapplication1234.Retrofit.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_product_activity extends AppCompatActivity {

    IRetrofit iRetrofit;
    ArrayList<get_category> cats=new ArrayList<>();
    String[] mylist;
    EditText pna,pnu,ppr;
    Button editbtn,deletebtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        pnu=findViewById(R.id.p_num2);
        pna=findViewById(R.id.p_name2);
        ppr=findViewById(R.id.p_price2);


        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();

        int p_Id=bundle.getInt("p_id");
        int c_Id=bundle.getInt("c_id");
        String p_name=bundle.getString("p_name");
        int p_price=bundle.getInt("p_price");
        int p_num=bundle.getInt("p_num");

        /*
        int p_Id=1;
        int c_Id=1;
        int p_name=1;
        int p_price=1;
        int p_num=1;
        */


        pna.setText(p_name);
        pnu.setText(String.valueOf(p_num));
        ppr.setText(String.valueOf(p_price));



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

                Toast.makeText(edit_product_activity.this, "?????? ???? ?????????????? ????????????", Toast.LENGTH_SHORT).show();
            }
        });

        editbtn=findViewById(R.id.btn_edit_product);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                iRetrofit.edit_delete_product(pna.getText().toString(),0,p_Id,Integer.parseInt(ppr.getText().toString())
                        ,Integer.parseInt(pnu.getText().toString()),cats.get(s.getSelectedItemPosition()).getC_id()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String message=Config.jsonObject("message",response.body().string());
                            if (message.equals("1"))
                            {
                                Toast.makeText(edit_product_activity.this, "???????????? ???????? ??????", Toast.LENGTH_SHORT).show();
                                edit_product_activity.this.finish();
                            }
                            else
                                Toast.makeText(edit_product_activity.this, "???????????? ???????? ????????", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(edit_product_activity.this, "?????? ???? ?????????????? ????????????", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

        deletebtn=findViewById(R.id.btn_delete_product);

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRetrofit.edit_delete_product(pna.getText().toString(),1,p_Id,Integer.parseInt(ppr.getText().toString())
                        ,Integer.parseInt(pnu.getText().toString()),cats.get(s.getSelectedItemPosition()).getC_id()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String message=Config.jsonObject("message",response.body().string());
                            if (message.equals("1"))
                            {
                                Toast.makeText(edit_product_activity.this, "???????????? ???????? ??????", Toast.LENGTH_SHORT).show();
                                edit_product_activity.this.finish();
                            }
                            else
                                Toast.makeText(edit_product_activity.this, "???????????? ???????? ????????", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(edit_product_activity.this, "?????? ???? ?????????????? ????????????", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}