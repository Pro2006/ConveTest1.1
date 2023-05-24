package com.msaggik.sixthlessonnotebook.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.msaggik.sixthlessonnotebook.R;

public class MainActivity extends AppCompatActivity {

    // создание полей
    private Button yesButton, noButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание id полям
        yesButton = findViewById(R.id.yesButton);
        noButton = findViewById(R.id.noButton);


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