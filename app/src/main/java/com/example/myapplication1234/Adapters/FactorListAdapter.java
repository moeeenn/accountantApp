package com.example.myapplication1234.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication1234.CustomFactorItem;
import com.example.myapplication1234.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FactorListAdapter extends BaseAdapter {

    Context context;
    ArrayList<CustomFactorItem> customFactorItem2;

    public FactorListAdapter(Context context2,ArrayList<CustomFactorItem> list2){
        this.context=context2;
        this.customFactorItem2=list2;
    }

    @Override
    public int getCount() {
        return customFactorItem2.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.factor_list_item,viewGroup,false);
        TextView list_name=view.findViewById(R.id.itemName);
        TextView list_detail=view.findViewById(R.id.itemDetail);
        list_name.setText(customFactorItem2.get(i).getName());
        list_detail.setText(customFactorItem2.get(i).getDetails());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customFactorItem2.remove(i);
            }
        });

        return view;
    }
}