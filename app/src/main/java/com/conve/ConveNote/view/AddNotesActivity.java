package com.conve.ConveNote.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.conve.ConveNote.R;
import com.conve.ConveNote.model.CommandProcessor;
import com.conve.ConveNote.viewmodel.DatabaseHelper;

public class AddNotesActivity extends AppCompatActivity {

    // создание полей
    private EditText title, description;
    private ImageView backBtnAdd;
    private Handler handler;
    private CommandProcessor commandProcessor;
    private Button addNote, useBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        commandProcessor = new CommandProcessor();
        handler = new Handler(Looper.getMainLooper());

        // присваивание id полям
        title = findViewById(R.id.title_edit);
        description = findViewById(R.id.description_edit);
        addNote = findViewById(R.id.add_note);
        backBtnAdd = findViewById(R.id.backBtnAdd);
        useBtnAdd = findViewById(R.id.useBtnAdd);

        // обработка нажатия кнопки
        addNote.setOnClickListener(listener);
        backBtnAdd.setOnClickListener(listener);
        useBtnAdd.setOnClickListener(listener);
    }

    private void updateTextView(final String text) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                // Обновляем элемент интерфейса
                description.setText(text);
            }
        });
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.add_note:
                    if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())){

                        DatabaseHelper database = new DatabaseHelper(AddNotesActivity.this); // создание объекта БД в текущей активности
                        database.addNotes(title.getText().toString(), description.getText().toString()); // создание записи в БД

                        // создание намерения переключения активности
                        Intent intent = new Intent(AddNotesActivity.this, SecondActivity.class); // переключение обратно в активность демонстрации всех записей
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // установления флага экономии ресурсов
                        startActivity(intent);

                        finish(); // при нажатии на кнопку назад действие уничтожается и проиходит переход в активность SecondActivity

                    } else { // иначе просто тост об отсутствии изменений
                        Toast.makeText(AddNotesActivity.this, "Необходимо заполнить оба поля", Toast.LENGTH_SHORT).show();
                    }
                case R.id.backBtnAdd:
                    Intent intent = new Intent(AddNotesActivity.this, SecondActivity.class); // переключение обратно в активность демонстрации всех записей
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); // установления флага экономии ресурсов
                    startActivity(intent);
                case R.id.useBtnAdd:
                    String result = null;
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if (result == null) {
                                // Ваш код, который нужно выполнить в отдельном потоке
                                String result = commandProcessor.Process(description.getText().toString());

                                updateTextView(result);}
                        }
                    });
                    thread.start();
                    // место для обработки текстовым процессором editText
            }
        }
    };
}