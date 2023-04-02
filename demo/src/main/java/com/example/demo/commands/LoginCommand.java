package com.example.demo.commands;

import com.example.demo.helpers.UserHelper;
import com.example.demo.models.UserModel;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

@Component
public class LoginCommand implements WorkerCommand {
    @Override
    public SendMessage start(Update update) {
        if (!update.getMessage().getText().equals("Log In")
                && !update.getMessage().getText().equals("Оставить своё имя")
                && !update.getMessage().getText().equals("Остаться анонимом")) {
            return null;
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Выберите действие");
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        if (update.getMessage().getText().equals("Log In")) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton("Оставить своё имя"));
            keyboardRow.add(new KeyboardButton("Остаться анонимом"));

            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
        }
        UserModel userModel = new UserModel();
        userModel.setUsername(update.getMessage().getFrom().getUserName());
        userModel.setTgId(update.getMessage().getFrom().getId().toString());
        if (update.getMessage().getText().equals("Остаться анонимом")){
            sendMessage.setText("Пользователь сохранён");
            UserHelper.saveUser(userModel);
        }
        if(update.getMessage().getText().equals("Оставить своё имя")){
            sendMessage.setText("Пользователь сохранён");
            userModel.setName(update.getMessage().getFrom().getFirstName());
            UserHelper.saveUser(userModel);
        }
            return sendMessage;
    }



    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }
}
