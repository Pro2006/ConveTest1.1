package com.msaggik.sixthlessonnotebook.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.msaggik.sixthlessonnotebook.R;

public class TokenActivity extends AppCompatActivity {
    public String chat_gpt_token;
    private Button saveBtn, passBtn;
    private EditText editToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        saveBtn  = findViewById(R.id.saveBtn);
        passBtn = findViewById(R.id.passBtn);
        editToken = findViewById(R.id.editToken);

        saveBtn.setOnClickListener(listener);
        passBtn.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.saveBtn:
                    chat_gpt_token = editToken.getText().toString();

            }
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            intent.putExtra("chat_gpt_token", chat_gpt_token);
            startActivity(intent);
        }
    };
}