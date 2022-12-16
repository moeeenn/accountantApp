package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication1234.Adapters.Product_list_adapter;
import com.example.myapplication1234.Model.get_products;

import java.util.ArrayList;

public class products_list_activity extends AppCompatActivity {

    ArrayList<get_products> arrayGetProducts=new ArrayList<>();
    Product_list_adapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);


        recyclerView=findViewById(R.id.recyclerView);

        // retrofit


        adapter=new Product_list_adapter(products_list_activity.this,arrayGetProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(products_list_activity.this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }
}