package com.czq.club;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Myclub_taskAdapter extends RecyclerView.Adapter<Myclub_taskAdapter.ViewHolder> {
    private List<BeanMyclub_task> myclub_tasks;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView myclub_logo;
        TextView myclub_name;
        TextView time;
        TextView content;

        public ViewHolder(View view) {
            super(view);
            myclub_logo = (ImageView) view.findViewById(R.id.club_logo);
            myclub_name = (TextView) view.findViewById(R.id.club_name);
            time = (TextView) view.findViewById(R.id.time);
            content=(TextView)view.findViewById(R.id.content);
        }
    }

    public Myclub_taskAdapter(List<BeanMyclub_task> Myclub_tasks){
        myclub_tasks=Myclub_tasks;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myclub_task, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        BeanMyclub_task myclub_task = myclub_tasks.get(position);
        holder.myclub_name.setText(myclub_task.getMyclub_name());
        holder.myclub_logo.setImageResource(myclub_task.getMyclub_logo());
        holder.time.setText(myclub_task.getTime());
        holder.content.setText(myclub_task.getTask_content());
    }

    public int getItemCount() {
        return myclub_tasks.size();
    }
}
