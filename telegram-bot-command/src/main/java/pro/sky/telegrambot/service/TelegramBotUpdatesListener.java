package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.Callback;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;


import com.pengrad.telegrambot.model.request.Keyboard;


import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.request.SendMessage;
import org.telegram.telegrambots.TelegramBotsApi;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.apache.http.annotation.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import javax.annotation.PostConstruct;
import javax.websocket.SendResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            List<List<InlineKeyboardButton>> buttonsMain = new ArrayList<>();

            List<InlineKeyboardButton> buttonShelterInformation = new ArrayList<>();
            buttonShelterInformation.add(new InlineKeyboardButton().setText("Информация о приюте").setCallbackData("CallFi4a"));
            buttonsMain.add(buttonShelterInformation);

            List<InlineKeyboardButton> buttonAdoptDog = new ArrayList<>();
            buttonAdoptDog.add(new InlineKeyboardButton().setText("приютить собаку").setCallbackData("CallFi4a"));
            buttonsMain.add(buttonAdoptDog);

            List<InlineKeyboardButton> buttonSubmitReport = new ArrayList<>();
            buttonSubmitReport.add(new InlineKeyboardButton().setText("Прислать отчет о питомце").setCallbackData("CallFi4a"));
            buttonsMain.add(buttonSubmitReport);

            List<InlineKeyboardButton> buttonCallVolunteer = new ArrayList<>();
            buttonCallVolunteer.add(new InlineKeyboardButton().setText("Позвать волонтера").setCallbackData("CallFi4a"));
            buttonsMain.add(buttonCallVolunteer);

            InlineKeyboardMarkup keyboardMarkup =new InlineKeyboardMarkup();
            keyboardMarkup.setKeyboard(buttonsMain);



            long chatId = update.message().chat().id();
            String messageText = String.valueOf(keyboardMarkup);
            SendMessage message = new SendMessage(chatId, messageText);

            try {
                if(update.message().text().equals("/start")) {

                    telegramBot.execute(message);

                }

            } catch (RuntimeException e){
                logger.error("error");
            }





           // Long chatId = update.message().chat().id();

            //if(update.message().text().equals("/start")) {
              //  telegramBot.execute(sendInlineKeyBoardMessage(update.message().chat().id()));
           // }

            //SendMessage message = new SendMessage().setChatId(update.message().chat().id()).setText("Пример").setReplyMarkup(inlineKeyboardMarkup);
            // SendMessage message = new SendMessage(update.message().chat().id(), inlineKeyboardMarkup);



        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }




}
