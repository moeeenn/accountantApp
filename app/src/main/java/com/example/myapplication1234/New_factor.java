package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1234.Adapters.FactorListAdapter;
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
    int num,price,pId;
    EditText q;
    ArrayList<CustomFactorItem> list_item;
    FactorListAdapter listAdapter;


    List <Integer> products=new ArrayList<>();
    List <String> productsName=new ArrayList<String>();
    Button btnAdd,btnFinish;
    ListView factorView;
    List <Integer> factorList=new ArrayList<>();
    TextView sum;
    int finalSum = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_factor);

        s = findViewById(R.id.spinner_pr);
        s1 = findViewById(R.id.spinner_cat);
        q=findViewById(R.id.q_edit);
        factorView=findViewById(R.id.factor);
        sum=findViewById(R.id.sum_price);

        list_item = new ArrayList<CustomFactorItem>();
        listAdapter = new FactorListAdapter(New_factor.this,list_item);
        factorView.setAdapter((ListAdapter) listAdapter);

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
                products.clear();
                productsName.clear();
                categoryId=arrayGetCategory.get(i).getC_id();

                for (int j=0;j<arrayGetProducts.size();j++){
                    if (arrayGetProducts.get(j).getC_id()==categoryId){
                        productsName.add(arrayGetProducts.get(j).getP_name());
                        products.add(arrayGetProducts.get(j).getP_id());

                    }
                }
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(New_factor.this, android.R.layout.simple_spinner_dropdown_item,productsName);
                s.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                for (int b=0;b<arrayGetProducts.size();b++)
                {
                    for (int j=0;j<products.size();j++){
                        if (products.get(j)==arrayGetProducts.get(b).getP_id()){
                            num=arrayGetProducts.get(b).getP_num();
                            price=arrayGetProducts.get(b).getP_price();
                            pId=products.get(j);



                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd=findViewById(R.id.add_new_p);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=productsName.get(s.getSelectedItemPosition());
                int Finalnum=Integer.parseInt(q.getText().toString());

                int FinalPrice=(price/num)*Finalnum;

                factorList.add(pId);
                factorList.add(FinalPrice);
                factorList.add(num);
                finalSum=finalSum+FinalPrice;
                sum.setText(String.valueOf(finalSum));

                String details="قیمت واحد:" + price + " - " + "مقدار:" + Finalnum + " - " + "قیمت کل:" + FinalPrice;
                list_item.add(new CustomFactorItem(name,details,pId));
                listAdapter.notifyDataSetChanged();




            }
        });

        factorView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                int c=factorList.indexOf(list_item.get(i).getId());
                list_item.remove(i);
                listAdapter.notifyDataSetChanged();
                finalSum -= factorList.get(c + 1);
                factorList.remove(c+2);
                factorList.remove(c+1);
                factorList.remove(c);
                sum.setText(String.valueOf(finalSum));
                return false;
            }
        });



        btnFinish=findViewById(R.id.finish_factor);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




            }
        });



    }


}