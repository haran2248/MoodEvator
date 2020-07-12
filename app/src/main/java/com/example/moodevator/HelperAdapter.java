package com.example.moodevator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter<HelperAdapter.HelperVH> {

    @NonNull
    ArrayList<Info> helpArrayList;
    Context context;

    public HelperAdapter(@NonNull ArrayList<Info> helpArrayList, Context context) {
        this.helpArrayList = helpArrayList;
        this.context = context;
    }

    public HelperAdapter.HelperVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HelperVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_help,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HelperAdapter.HelperVH holder, int position) {
        holder.populate(helpArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return helpArrayList.size();
    }

    public class HelperVH extends RecyclerView.ViewHolder {
        TextView name,location,phNo,help;
        public HelperVH(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_name);
            location=itemView.findViewById(R.id.item_location);
            phNo=itemView.findViewById(R.id.item_phone);
            help=itemView.findViewById(R.id.item_help);
        }

        public void populate(Info info) {
            name.setText(info.getName());
            location.setText(info.getLocation());
            phNo.setText(info.getPhoneNo());
            help.setText(info.getHelp());
        }
    }
}
