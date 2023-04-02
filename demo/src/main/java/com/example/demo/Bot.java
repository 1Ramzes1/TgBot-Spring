package com.example.demo;

import com.example.demo.commands.BookCommand;
import com.example.demo.commands.ChooseTime;
import com.example.demo.commands.LoginCommand;
import com.example.demo.commands.WorkerCommand;
import com.example.demo.commands.bookcommand.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Configuration
public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {

        return "@RamzesikBot";
    }
    @Override
    public String getBotToken(){

        return "6056203531:AAHwV0Y9fxlggDVXOkc6BwPj6a7aSD_Y4tk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Выберите действие");

        KeyboardRow k = new KeyboardRow();
        k.add(new KeyboardButton("Log In"));
        k.add(new KeyboardButton("Записаться к врачу"));
        k.add(new KeyboardButton("Показать время"));

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(k));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);


        List<WorkerCommand> list = new ArrayList<>();
        list.add(new LoginCommand());
        list.add(new BookCommand());
        list.add(new TerapevtBookCommand());
        list.add(new AllergologBookCommand());
        list.add(new GinekologBookCommand());
        list.add(new HirurgBookCommand());
        list.add(new LorBookCommand());
        list.add(new OkulistBookCommand());
        list.add(new ChooseTime());
        list.add(new Time());

        SendMessage s;
        for (WorkerCommand w : list) {
            if ((s = w.start(update)) != null) {
                sendMessage = s;
                break;
            }
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
