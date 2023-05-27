package com.conve.ConveNote.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.conve.ConveNote.R;

public class MainActivity extends AppCompatActivity {

    // создание полей
    private Button yesButton, noButton;

    private String CHATGPT_TOKEN = "chatgtp_token";
    private SharedPreferences chatgpt_token_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание id полям
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);
        chatgpt_token_setting = getSharedPreferences(CHATGPT_TOKEN, MODE_PRIVATE);


        // обработка нажатия кнопки
        yesButton.setOnClickListener(listener);
        noButton.setOnClickListener(listener);


    }

    // создание слушателя
    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.yesButton) {
                    // переключение на новую активность
                    Intent intentSecond = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(intentSecond);}
            else{
                    Intent intentToken = new Intent(getApplicationContext(), TokenActivity.class);
                    startActivity(intentToken);


            }
        }
    };
}