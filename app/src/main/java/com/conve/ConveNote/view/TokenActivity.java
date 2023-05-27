package com.conve.ConveNote.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.conve.ConveNote.R;

public class TokenActivity extends AppCompatActivity {
    public String chat_gpt_token;
    private String CHATGPT_TOKEN = "chatgtp_token";
    private SharedPreferences chatgpt_token_setting;

    private SharedPreferences.Editor chatgpt_token_setting_editor;
    private Button saveBtn, passBtn;
    private EditText editToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        saveBtn  = findViewById(R.id.saveBtn);
        passBtn = findViewById(R.id.passBtn);
        editToken = findViewById(R.id.editToken);
        chatgpt_token_setting = getSharedPreferences(CHATGPT_TOKEN, MODE_PRIVATE);

        saveBtn.setOnClickListener(listener);
        passBtn.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.saveBtn:
                    chat_gpt_token = editToken.getText().toString();
                    chatgpt_token_setting_editor = chatgpt_token_setting.edit();
                    chatgpt_token_setting_editor.putString(CHATGPT_TOKEN, chat_gpt_token);


            }
            chatgpt_token_setting_editor.apply();
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            startActivity(intent);
        }
    };
}