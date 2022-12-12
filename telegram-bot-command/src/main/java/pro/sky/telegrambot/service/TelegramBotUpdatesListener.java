package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendContact;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.Buttons.InlineKeyboards;
import pro.sky.telegrambot.Buttons.ReplyKeyboards;

import javax.annotation.PostConstruct;
import java.util.List;

import static pro.sky.telegrambot.constants.Constants.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final static Keyboard replyMainKeyboards = new ReplyKeyboards().generateMainKeyboard();
    private final static Keyboard replyOneKeyboards = new ReplyKeyboards().generateOneKeyboard();
    private final static Keyboard replyTwoKeyboards = new ReplyKeyboards().generateTwoKeyboard();
    private final static Keyboard inlineMainKeyboards = new InlineKeyboards().generateMainKeyboard();
    private final static Keyboard inlineOneKeyboards = new InlineKeyboards().generateOneKeyboard();
    private final static Keyboard inlineTwoKeyboards = new InlineKeyboards().generateTwoKeyboard();
    private final static Long volunteerChatId = -1001816802535L;

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

    private void messageProcessing(Update update) {
        Long chatId = getChatId(update);
        if (chatId.equals(volunteerChatId) || chatId.equals(-891499042L)) {
            System.out.println("Помехи в чате волонтёров. ID чата: " + chatId + " " + chatId.equals(volunteerChatId));
        } else {
            if (!(update.message() == null)) {
                telegramBot.execute(createMessage(chatId, update.message().text()));
                if (update.message().text().equals(KEYBOARD_CALL_VOLUNTEER)){
                    SendMessage messageVolunteer = new SendMessage(volunteerChatId, MESSAGE_FOR_VOLUNTEER + " " + "@" + update.message().from().username());
                    telegramBot.execute(messageVolunteer);
                }
            }
        }
    }

    private Long getChatId(Update update) {
        Long chatId = null;
        try {
            if (update.message() == null) {
                if (update.callbackQuery() == null) {
                    chatId = update.myChatMember().chat().id();
                } else {
                    chatId = update.callbackQuery().message().chat().id();
                }
            } else {
                chatId = update.message().chat().id();
            }
        } catch (NullPointerException notInitializedChatId) {
            System.out.println("Сработал блок try catch в методе getChatId() " + chatId);
        }
        return chatId;
    }

    private SendMessage createMessage(Long chatId, String text) {
        SendMessage message = null;
        switch (text) {
            // главное меню и общее
            case "/start":
            case "Главное меню":
                message = new SendMessage(chatId, WELCOME_MESSAGE_MAIN);
                message.replyMarkup(replyMainKeyboards);
                break;
            case KEYBOARD_MAIM_SHELTER_INFORMATION:
                message = new SendMessage(chatId, WELCOME_MESSAGE_ONE);
                message.replyMarkup(replyOneKeyboards);
                break;
            case KEYBOARD_MAIM_ADOPT_DOG:
                message = new SendMessage(chatId, WELCOME_MESSAGE_TWO);
                message.replyMarkup(replyTwoKeyboards);
                break;
            case KEYBOARD_MAIM_SUBMIT_REPORT:
                message = new SendMessage(chatId, WELCOME_MESSAGE_THREE);
                break;
            case KEYBOARD_CALL_VOLUNTEER:
                message = new SendMessage(chatId, WELCOME_MESSAGE_FOUR);
                break;
            case KEYBOARD_CONTACT:
                message = new SendMessage(chatId, RECORD_CONTACT);
                break;
            // меню один
            case KEYBOARD_ONE_SHELTER_INFORMATION:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_ONE_WORK_SCHEDULE:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_ONE_ACCIDENT_PREVENTION:
                message = new SendMessage(chatId, "заглушка");
                break;
            // меню два
            case KEYBOARD_TWO_DATING_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_DOCUMENTS_ADOPT_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_TRANSPORTATION_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_SMALL_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_BIG_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_INVALID_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_CYNOLOGIST_ADVICE:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_GOOD_CYNOLOGIST:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_NOT_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
        }
        return message;
    }



}
