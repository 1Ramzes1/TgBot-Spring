package com.example.demo;

import com.example.demo.commands.WorkerCommand;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Date;

@Component
public class Time implements WorkerCommand {
    @Override
    public SendMessage start(Update update){
        if (!update.getMessage().getText().equals("Показать время")) {
            return null;
        }
        Date date = new Date();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        String str = String.format("Текущая дата и время: %tc", date);
        if (update.getMessage().getText().equals("Показать время")){
            sendMessage.setText("Время срать, а мы не ели"+"\n"+ str);
        }
        return sendMessage;
    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        return null;
    }

}
