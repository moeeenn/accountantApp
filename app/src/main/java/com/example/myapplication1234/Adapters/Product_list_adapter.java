package com.example.myapplication1234.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1234.MainActivity;
import com.example.myapplication1234.Model.get_products;
import com.example.myapplication1234.R;
import com.example.myapplication1234.edit_product_activity;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class Product_list_adapter extends RecyclerView.Adapter<Product_list_adapter.MyViewHolder> {

    Context context;
    ArrayList<get_products> arrayGetProducts;

    public Product_list_adapter(Context context, ArrayList<get_products> arrayGetProducts) {
        this.context = context;
        this.arrayGetProducts = arrayGetProducts;
    }

    @NonNull
    @Override
    public Product_list_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Product_list_adapter.MyViewHolder holder, int position) {
        holder.product_name.setText(arrayGetProducts.get(position).getP_name() + "  ");
        holder.product_c_name.setText(arrayGetProducts.get(position).getC_name() + "");
        holder.product_price.setText(arrayGetProducts.get(position).getP_price() + "  ");
        holder.product_num.setText(arrayGetProducts.get(position).getP_num() + "");

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,edit_product_activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("p_id",arrayGetProducts.get(holder.getAdapterPosition()).getP_id());
                bundle.putInt("c_id",arrayGetProducts.get(holder.getAdapterPosition()).getC_id());
                bundle.putString("p_name",arrayGetProducts.get(holder.getAdapterPosition()).getP_name());
                bundle.putInt("p_price",arrayGetProducts.get(holder.getAdapterPosition()).getP_price());
                bundle.putInt("p_num",arrayGetProducts.get(holder.getAdapterPosition()).getP_num());

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayGetProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_c_name, product_price, product_num;
        LinearLayout linear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_c_name = itemView.findViewById(R.id.product_c_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_num = itemView.findViewById(R.id.product_num);
            linear = itemView.findViewById(R.id.linear);
        }
    }
}
