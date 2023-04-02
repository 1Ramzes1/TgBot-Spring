package com.example.demo.commands.bookcommand;

import com.example.demo.commands.WorkerCommand;
import com.example.demo.helpers.DoctorEnum;
import com.example.demo.helpers.DoctorHelper;
import com.example.demo.helpers.UserHelper;
import com.example.demo.models.UserModel;
import org.glassfish.grizzly.utils.DelayedExecutor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class HirurgBookCommand implements WorkerCommand {
    @Override
    public SendMessage start(Update update) {
        if(!update.getMessage().getText().equals("Хирург")){
            return null;
        }
        UserModel userModel = UserHelper.findUser(update.getMessage().getFrom().getId().toString());
        userModel.setDoctorEnum(DoctorEnum.HIRURG);
        UserHelper.saveUser(userModel);
        return sendDefaultMessage(update);

    }

    @Override
    public SendMessage sendDefaultMessage(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setText("Выберите подходящее время");

        List<String> list = DoctorHelper.getFreeTimes(DoctorEnum.HIRURG);
        KeyboardRow k1 = new KeyboardRow();
        k1.add(new KeyboardButton(list.get(0)));
        k1.add(new KeyboardButton(list.get(1)));

        List<KeyboardRow> list1 = new ArrayList<>();
        list1.add(k1);
        KeyboardRow k2 = new KeyboardRow();
        if(list.size()>2){
            for (int i = 0; i < list.size(); i++) {
                k2.add(new KeyboardButton(list.get(i)));
            }
            list1.add(k2);
        }
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(list1);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}
