package com.example.myapplication1234.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1234.Model.factors;
import com.example.myapplication1234.R;

import java.util.ArrayList;

public class FactorsAdapter extends RecyclerView.Adapter<FactorsAdapter.Holder> {

    Context context;
    ArrayList<factors> array;

    public FactorsAdapter(Context context, ArrayList<factors> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.recycler_factors,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.textView_name.setText(array.get(position).getP_name());
        holder.textView_count.setText(array.get(position).getNum()+"");
        holder.textView_price.setText(array.get(position).getPrice()+"");
        holder.textView_date.setText(array.get(position).getDate()+"");
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView textView_name,textView_count,textView_price,textView_date;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView_name=itemView.findViewById(R.id.textView_name);
            textView_count=itemView.findViewById(R.id.textView_count);
            textView_price=itemView.findViewById(R.id.textView_price);
            textView_date=itemView.findViewById(R.id.textView_date);

        }
    }
}
