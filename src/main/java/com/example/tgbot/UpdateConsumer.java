package com.example.tgbot;

//import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateConsumer implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;

    public UpdateConsumer() {
        this.telegramClient = new OkHttpTelegramClient(
                "8062170151:AAHZwvu7m5I_sqQA-5ooWeVPeV9q8IYF68Y"
        );
    }

//    @SneakyThrows
    @Override
    public void consume(Update update) {

        if (update.hasMessage()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                sendReplyKeyboard(update.getMessage().getChatId());
            } else if (messageText.equals("Ключ у меня")) {

            } else if (messageText.equals("Ключ сдан")) {

            }
        }
//        System.out.printf(
//                "Пришло сообщение %s от %s%n",
//                update.getMessage().getText(),
//                update.getMessage().getChatId()
//        );
//
//        var chatId = update.getMessage().getChatId();
//        SendMessage message = SendMessage.builder()
//                .text("Привет")
//                .chatId(chatId)
//                .build();
//
//        try {
//            telegramClient.execute(message);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void sendReplyKeyboard(Long chatId) {
        SendMessage message = SendMessage.builder()
                .text("\uD83D\uDD11 Привет!\n" +
                        "\n" +
                        "Я помогу вам отслеживать местоположение ключа от 618/1 аудитории.\n" +
                        "\n" +
                        "\uD83D\uDCCB Что вы можете сделать:\n" +
                        "• Сообщить, что ключ у вас\n" +
                        "• Отметить, что ключ сдан\n")
                .chatId(chatId)
                .build();

        List<KeyboardRow> keyboardRows = List.of(
                new KeyboardRow("Ключ у меня", "Ключ сдан")
        );

        ReplyKeyboardMarkup replyKeyboardMarkup = ReplyKeyboardMarkup.builder()
                .keyboard(keyboardRows)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();

//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboardRows);
        message.setReplyMarkup(replyKeyboardMarkup);

        try {
            telegramClient.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
