package com.saral.recyclerviewfbacp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {

    ArrayList<Student> students;

    public StudentsAdapter(ArrayList<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public StudentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.ViewHolder holder, int position) {
        Student student = students.get(position);
        holder.phone.setText(student.getPhone());
        holder.address.setText(student.getAddress());
        holder.email.setText(student.getEmail());
        holder.name.setText(student.getName());

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, email, address, phone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            email = itemView.findViewById(R.id.Email);
            address = itemView.findViewById(R.id.School);
            phone = itemView.findViewById(R.id.Phone);
        }
    }
}
