package com.saral.frags;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class InputFragment extends Fragment {

    EditText editText;
    Button sendButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        editText = view.findViewById(R.id.editText);
        sendButton = view.findViewById(R.id.sendButton);

        sendButton.setOnClickListener(v -> {
            String userInput = editText.getText().toString();
            Intent intent = new Intent(getActivity(), SecondActivity.class);
            intent.putExtra("user_input", userInput);
            startActivity(intent);
        });

        return view;
    }
}
