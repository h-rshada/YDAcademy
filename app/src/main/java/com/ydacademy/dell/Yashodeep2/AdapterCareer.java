package com.ydacademy.dell.Yashodeep2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by akshay on 11/12/17.
 */

public class AdapterCareer extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    protected boolean amuser = true;
    List<DataCareer> data = Collections.emptyList();
    AdapterCareer.MyHolder myHolder;
    private Context context;
    private LayoutInflater inflater;


    public AdapterCareer(Context context, List<DataCareer> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        myHolder = (AdapterCareer.MyHolder) holder;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_career, parent, false);
        AdapterCareer.MyHolder holder = new AdapterCareer.MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        final AdapterCareer.MyHolder myHolder = (AdapterCareer.MyHolder) holder;
        DataCareer dataCareer = data.get(position);
        myHolder.txtTitle.setText(dataCareer.title);
        myHolder.txtDesc.setText(dataCareer.desc);
        myHolder.txtScope.setText(dataCareer.jobscope);
        myHolder.txtRelevantCourses.setText(dataCareer.course);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtDesc, txtScope, txtRelevantCourses;

        public MyHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtName);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtScope = itemView.findViewById(R.id.txtScope);
            txtRelevantCourses = itemView.findViewById(R.id.txtRelevantCourses);


        }
    }

}
