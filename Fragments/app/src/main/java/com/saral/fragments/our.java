package com.saral.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class our extends Fragment {
    EditText name;
    Button pressme;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_our, container, false);
        name = v.findViewById(R.id.editPersonName);
        pressme = v.findViewById(R.id.button);
        pressme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity2.class);
                i.putExtra("com.saral.fragments.name", name.getText().toString());
                startActivity(i);
            }
        });
        return v;
    }
}