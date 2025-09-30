package com.saral.examapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    ArrayList<Exam> examList;

    public ExamAdapter(ArrayList<Exam> examList) {
        this.examList = examList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subjectTextView);
            date = itemView.findViewById(R.id.dateTextView);
        }
    }

    @NonNull
    @Override
    public ExamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamAdapter.ViewHolder holder, int position) {
        Exam exam = examList.get(position);
        holder.subject.setText(exam.getSubject());
        holder.date.setText(exam.getDate());
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }
}

