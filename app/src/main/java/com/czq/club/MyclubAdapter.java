package com.czq.club;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

public class MyclubAdapter extends RecyclerView.Adapter<MyclubAdapter.ViewHolder> {
    private List<BeanMyclub> myclubs;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView myclub_logo;
        TextView myclub_name;

        public ViewHolder(View view) {
            super(view);
            myclub_logo = (ImageView) view.findViewById(R.id.club_logo);
            myclub_name = (TextView) view.findViewById(R.id.club_name);
        }
    }

    public MyclubAdapter(List<BeanMyclub> Myclubs){
        myclubs=Myclubs;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myclub, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.myclub_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                BeanMyclub myclub = myclubs.get(position);
                //Toast.makeText(v.getContext(), "社团头像点击：你点击了社团：" + myclub.getMyclub_name(), Toast.LENGTH_SHORT).show();
                //获得当前点击社团的信息
                Intent intent =new Intent();
                intent.setClass(v.getContext(),ClubActivity1.class);
                intent.putExtra("myclub", myclub);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        BeanMyclub myclub = myclubs.get(position);
        holder.myclub_name.setText(myclub.getMyclub_name());
        holder.myclub_logo.setImageResource(myclub.getMyclub_logo());
    }

    public int getItemCount() {
        return myclubs.size();
    }
}