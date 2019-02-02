package com.example.soymilk.asynchttp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.myHolderMyViews> {

    ArrayList<String> listOfEarthquakes;
    Context mContext;

    public Adapter(ArrayList<String> listOfEarthquakes) {
        this.listOfEarthquakes = listOfEarthquakes;
    }

    @NonNull
    @Override
    public myHolderMyViews onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        myHolderMyViews holderMyViews = new myHolderMyViews(rootView);
        return holderMyViews;
    }

    @Override
    public void onBindViewHolder(@NonNull myHolderMyViews myHolderMyViews, int i) {
        String earthquakeData = listOfEarthquakes.get(i);
        myHolderMyViews.textView.setText(earthquakeData);
    }



    @Override
    public int getItemCount() {
        return listOfEarthquakes.size();
    }

    public static class myHolderMyViews extends RecyclerView.ViewHolder {
        public TextView textView;
        public myHolderMyViews(@NonNull View itemView) {
            super(itemView);
             textView = (TextView)itemView.findViewById(R.id.chicken);
        }
    }
}
