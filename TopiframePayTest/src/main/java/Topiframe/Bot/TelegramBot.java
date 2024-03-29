package Topiframe.Bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot {
    /**
     * Telegrambot for Javarush Community from Javarush community.
     */
    @Component
    public class JenkinsSerj_bot extends TelegramLongPollingBot {

        @Value("${bot.username}")
        private String username;

        @Value("${bot.token}")
        private String token;

        @Override
        public void onUpdateReceived(Update update) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String message = update.getMessage().getText().trim();
                String chatId = update.getMessage().getChatId().toString();

                SendMessage sm = new SendMessage();
                sm.setChatId(chatId);
                sm.setText(message);

                try {
                    execute(sm);
                } catch (TelegramApiException e) {
                    //todo add logging to the project.
                    e.printStackTrace();
                }
            }

        }

        @Override
        public String getBotUsername() {
            return null;
        }

        @Override
        public String getBotToken() {
            return null;
        }
    }}
