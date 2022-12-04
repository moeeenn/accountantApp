package com.example.myapplication1234;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class New_factor extends AppCompatActivity {

    Spinner category,product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_factor);
        String[] arraySpinner = new String[]{
                "PlaceHolder_1","PlaceHolder_2","PlaceHolder_3",
        };
        Spinner s = (Spinner) findViewById(R.id.spinner_cat);
        ArrayAdapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arraySpinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter1);

        String[] arraySpinner2 = new String[]{
                "PlaceHolder_1","PlaceHolder_2","PlaceHolder_3",
        };
        Spinner s1 = (Spinner) findViewById(R.id.spinner_pr);
        ArrayAdapter adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,arraySpinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter2);
    }


}