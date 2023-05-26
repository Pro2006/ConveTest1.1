package com.msaggik.sixthlessonnotebook.view;

import com.msaggik.sixthlessonnotebook.service.ChatGptService;

import java.io.IOException;

public class App {
    private static final String API_KEY = "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";

    public static String chatGPT(String question) throws IOException, InterruptedException {
        // Создаем исполнительский сервис с одним потоком
        ChatGptService service = new ChatGptService(API_KEY);
        return service.search(question);

    }
}

