package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.Buttons.InlineKeyboards;
import pro.sky.telegrambot.Buttons.ReplyKeyboards;
import pro.sky.telegrambot.constants.Constants;


import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final ReplyKeyboards replyKeyboards = new ReplyKeyboards();
    private final InlineKeyboards inlineKeyboards = new InlineKeyboards();

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
            messageProcessing(update);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    // ПОДУМАТЬ КАК КОРЕКТНО ВЕРНУТЬ НОМЕР ЧАТА

    private Long getChatId(Update update) {
        Long chatId = null;
        try {
            if (update.message() == null) {
                chatId = update.callbackQuery().message().chat().id();
            } else {
                chatId = update.message().chat().id();
            }
        } catch (NullPointerException notInitializedChatId){
            System.out.println("Сработал блок try catch в методе getChatId() " + chatId);
        }
        return chatId;
    }



    private void messageProcessing(Update update){
        Long chatId = getChatId(update);

        if (!(update.message() == null)){
            if ("/start".equals(update.message().text())) {
                SendMessage messageMain = new SendMessage(chatId, Constants.WELCOME_MESSAGE_MAIN);
                messageMain.replyMarkup(inlineKeyboards.generateMainKeyboard());
                telegramBot.execute(messageMain);
            }
        }

        if (!(update.callbackQuery() == null)) {
            switch (update.callbackQuery().data()) {
                case Constants.KEYBOARD_MAIM_SHELTER_INFORMATION:
                    SendMessage messageOne = new SendMessage(chatId, Constants.WELCOME_MESSAGE_ONE);
                    messageOne.replyMarkup(inlineKeyboards.generateOneKeyboard());
                    telegramBot.execute(messageOne);
                    break;
                case Constants.KEYBOARD_MAIM_ADOPT_DOG:
                    System.out.println(update.callbackQuery().data());
                    SendMessage messageTwo = new SendMessage(chatId, Constants.WELCOME_MESSAGE_TWO);
                    messageTwo.replyMarkup(inlineKeyboards.generateTwoKeyboard());
                    telegramBot.execute(messageTwo);
                    break;
                case Constants.KEYBOARD_MAIM_SUBMIT_REPORT:
                    SendMessage messageThree = new SendMessage(chatId, Constants.WELCOME_MESSAGE_THREE);
                    telegramBot.execute(messageThree);
                    break;
                case Constants.KEYBOARD_CALL_VOLUNTEER:
                    System.out.println("chatId " + chatId);
                    System.out.println("update.callbackQuery().message().chat().id() " + update.callbackQuery().message().chat().id());
                    System.out.println("update.callbackQuery().data() " + update.callbackQuery().data());
                    SendMessage messagePerson = new SendMessage(chatId, Constants.WELCOME_MESSAGE_FOUR);
                    telegramBot.execute(messagePerson);
                    SendMessage messageVolunteer = new SendMessage(891499042, Constants.WELCOME_MESSAGE_THREE);
                    telegramBot.execute(messageVolunteer);
                    break;

                // СЮДА НАПИСАТЬ ДЕЙСТВИЯ НА ОСТАВШИЕСЯ КНОПКИ
            }

        }




    }

}
