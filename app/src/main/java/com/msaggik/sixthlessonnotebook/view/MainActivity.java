package com.msaggik.sixthlessonnotebook.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.msaggik.sixthlessonnotebook.R;
import com.msaggik.sixthlessonnotebook.service.ChatGptService;

import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity  {

    private static final String API_KEY = "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";

    // создание полей
    private ImageButton imageButton;
    private Button gpt_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание id полям
        imageButton = findViewById(R.id.imageButton);
        gpt_btn = findViewById(R.id.gpt_btn);

        // обработка нажатия кнопки
        imageButton.setOnClickListener(listener);
        gpt_btn.setOnClickListener(listener);


    }

    // создание слушателя
    private View.OnClickListener listener = new View.OnClickListener()  {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.gpt_btn:
                    ChatGptService service = new ChatGptService(API_KEY);

                    System.out.println("Java CLI application that use Chat GPT API");
                    System.out.println("---");

                    String queryString = "";
                    Scanner scanner = new Scanner(System.in);

                    while (true) {
                        System.out.println();
                        System.out.println("Enter a string to search for (or /q to quit): ");
                        queryString = scanner.nextLine();

                        if (queryString.equals("/q")) {
                            break;
                        }

                        System.out.println("Searching...");
                        String answer = null;

                        try {
                            answer = service.search(queryString);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(answer);
                    }
                case R.id.imageButton:


                    // переключение на новую активность
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(intent);

            }
        }
    };
}