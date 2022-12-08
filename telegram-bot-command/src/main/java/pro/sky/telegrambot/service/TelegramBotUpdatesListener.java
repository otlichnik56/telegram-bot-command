package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.Callback;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;


import com.pengrad.telegrambot.model.request.Keyboard;


import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.telegram.telegrambots.TelegramBotsApi;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.apache.http.annotation.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.Buttons.ReplyKeyboards;


import javax.annotation.PostConstruct;
import javax.websocket.SendResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private ReplyKeyboards replyKeyboards = new ReplyKeyboards();

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



            long chatId = update.message().chat().id();
            String messageText = "dfghdf";
            //Message message = new Message();



            SendMessage message = new SendMessage(chatId, messageText);
            message.replyMarkup(replyKeyboards.generateMainKeyboard());


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
            //SendMessage message = new SendMessage(update.message().chat().id(), inlineKeyboardMarkup);



        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
