package com.msaggik.sixthlessonnotebook.model;

import com.msaggik.sixthlessonnotebook.view.App;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CommandProcessor {
    private final String API_KEY = "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";
    public String answer;

    public String Process(String preProcessedText) {
        // текст из editText

        Map<String, CommandHandler> commandHandlers = new HashMap<>();
        // здесб добавлять новые команды после создания нового handler для них
        commandHandlers.put("chatgpt", new ChatGPTCommandHandler());
        commandHandlers.put("translate", new TranslateHandler());
        commandHandlers.put("calcul", new CalculHandler());

        String processedText = processCommands(preProcessedText, commandHandlers);
        return processedText;
    }

    public static String processCommands(String text, Map<String, CommandHandler> commandHandlers) {
        Pattern pattern = Pattern.compile("(\\w+)\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(text);

        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            String commandName = matcher.group(1);
            String commandText = matcher.group(2);
            CommandHandler commandHandler = commandHandlers.get(commandName);
            if (commandHandler != null) {
                String processedCommand = commandHandler.processCommand(commandText);
                matcher.appendReplacement(buffer, processedCommand);
            }
        }
        matcher.appendTail(buffer);

        return buffer.toString();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // супер интерфейс
    public interface CommandHandler {
        String processCommand(String commandText);
    }

    //здесь начинаются handlerы для команд
    public static class ChatGPTCommandHandler implements CommandHandler {
        @Override
        public String processCommand(String commandText) {
            // Обработка команды chatgpt

            try {
                return App.chatGPT(commandText);
            } catch (IOException | InterruptedException e) {
                return "Произошла ошибка, попробуйте снова chatgpt[" + commandText + "]";
            }
        }
    }

    public static class TranslateHandler implements CommandHandler {

        @Override
        public String processCommand(String commandText) {
            return "translate";
        }
    }

    public static class CalculHandler implements CommandHandler {

        @Override
        public String processCommand(String commandText) {
            return MathExpressionEvaluator.evaluateMathExpression(commandText);
        }
    }





    }
