package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.Buttons.ReplyKeyboards;
import pro.sky.telegrambot.constants.Constants;


import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final ReplyKeyboards replyKeyboards = new ReplyKeyboards();
    private final ShelterService shelterService;

    public TelegramBotUpdatesListener(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

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

    private void messageProcessing(Update update){
        long chatId = update.message().chat().id();
        switch (update.message().text()) {
            case "/start":
                SendMessage messageMain = new SendMessage(chatId, Constants.WELCOME_MESSAGE_MAIN);
                messageMain.replyMarkup(replyKeyboards.generateMainKeyboard());
                telegramBot.execute(messageMain);
                break;
            case Constants.KEYBOARD_MAIM_SHELTER_INFORMATION:
                SendMessage messageOne = new SendMessage(chatId, Constants.WELCOME_MESSAGE_ONE);
                messageOne.replyMarkup(replyKeyboards.generateOneKeyboard());
                telegramBot.execute(messageOne);
                break;
            case Constants.KEYBOARD_MAIM_ADOPT_DOG:
                SendMessage messageTwo = new SendMessage(chatId, Constants.WELCOME_MESSAGE_TWO);
                messageTwo.replyMarkup(replyKeyboards.generateTwoKeyboard());
                telegramBot.execute(messageTwo);
                break;
            case Constants.KEYBOARD_MAIM_SUBMIT_REPORT:
                SendMessage messageThree = new SendMessage(chatId, Constants.WELCOME_MESSAGE_THREE);
                telegramBot.execute(messageThree);
                break;
            case Constants.KEYBOARD_CALL_VOLUNTEER:
                SendMessage messageFour = new SendMessage(chatId, Constants.WELCOME_MESSAGE_FOUR);
                telegramBot.execute(messageFour);
                break;
            case "/hello":
                SendMessage messageN = new SendMessage(chatId, shelterService.greetings());
                telegramBot.execute(messageN);
                break;

                // СЮДА НАПИСАТЬ ДЕЙСТВИЯ НА ОСТАВШИЕСЯ КНОПКИ

            default:
                SendMessage message = new SendMessage(chatId, Constants.SORRY_MESSAGE);
                telegramBot.execute(message);
        }
    }

}
