package com.msaggik.sixthlessonnotebook.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.msaggik.sixthlessonnotebook.R;

public class MainActivity extends AppCompatActivity  {

    private static final String API_KEY = "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";

    // создание полей
    private Button yesButton, noButton;
    private Button gpt_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание id полям
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);


        // обработка нажатия кнопки
        yesButton.setOnClickListener(listener);
        gpt_btn.setOnClickListener(listener);


    }

    // создание слушателя
    private View.OnClickListener listener = new View.OnClickListener()  {
        @Override
        public void onClick(View view) {
                    // переключение на новую активность
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(intent);

            }

    };
}