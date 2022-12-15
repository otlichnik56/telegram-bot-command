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
import pro.sky.telegrambot.Buttons.Keyboards;
import pro.sky.telegrambot.Buttons.ReplyKeyboards;

import javax.annotation.PostConstruct;
import java.util.List;

import static pro.sky.telegrambot.constants.Constants.*;

@Service
public class TelegramBotUpdatesListenerVlad implements UpdatesListener {
    private Keyboards keyboard;
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListenerVlad.class);
    private final static Keyboard replyMainKeyboards = new ReplyKeyboards().generateMainMenuKeyboard();
    private final static Keyboard replyOneKeyboards = new ReplyKeyboards().generateAboutShelterMenuKeyboard();
    private final static Keyboard replyTwoKeyboards = new ReplyKeyboards().generateAdoptDogMenuKeyboard();

    private Boolean recordStatus = false;
    private final static Long volunteerChatId = -1001816802535L;

    public TelegramBotUpdatesListenerVlad() {
        this.keyboard = new Keyboards();
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

            long chatId = update.message().chat().id();
            String inputText = update.message().text();

            SendMessage replyMessage = createMessage(chatId, inputText);
            telegramBot.execute(replyMessage);

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendReply(SendMessage replyMessage) {
        telegramBot.execute(replyMessage);
    }


    private void messageProcessing(Update update) {
        Long chatId = getChatId(update);
        if (recordStatus) {
            if (!(update.message() == null)) {
                String text = "Ребят, новый контакт, запишите  " + update.message().text() + " " + update.message().chat().firstName() + " " + update.message().chat().lastName();
                SendMessage message = new SendMessage(volunteerChatId, text);
                telegramBot.execute(message);
                recordStatus = false;
            }
        } else {
            if (!(update.message() == null)) {
                telegramBot.execute(createMessage(chatId, update.message().text()));
                if (!(update.message().text() == null) && update.message().text().equals(CALL_VOLUNTEER)) {
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
        SendMessage message;
        System.out.println(text);

        switch (text) {
            // главное меню и общее
            case START:
            case TO_MAIN_MENU:
                message = new SendMessage(chatId, WELCOME_MESSAGE_MAIN);
                message.replyMarkup(replyMainKeyboards);
                break;
            case TO_INFO_ABOUT_SHELTER:
                message = new SendMessage(chatId, WELCOME_MESSAGE_ONE);
                message.replyMarkup(replyOneKeyboards);
                break;
            case TO_ADOPT_DOG:
                message = new SendMessage(chatId, WELCOME_MESSAGE_TWO);
                message.replyMarkup(replyTwoKeyboards);
                break;
            case KEYBOARD_MAIM_SUBMIT_REPORT:
                message = new SendMessage(chatId, WELCOME_MESSAGE_THREE);
                recordStatus = true;
                break;

            case CALL_VOLUNTEER:
                message = new SendMessage(chatId, WELCOME_MESSAGE_FOUR);
                break;
            case SEND_CONTACTS:
                message = new SendMessage(chatId, RECORD_CONTACT);
                recordStatus = true;
                break;
            // меню один

            // меню два
            case ADOPT_DOG_RULES:
                message = new SendMessage(chatId, "заглушка");
                break;
            case ADOPT_DOG_DOCUMENTS:
                message = new SendMessage(chatId, "заглушка");
                break;
            case ADOPT_DOG_RECOMENDATIONS:
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

            case ADOPT_DOG_APPROVED_CYNOLOGYSTS:
                message = new SendMessage(chatId, "заглушка");
                break;
            case ADOPT_DOG_DECLINE_REASONS:
                message = new SendMessage(chatId, "заглушка");
                break;
            default:
                message = new SendMessage(chatId, SORRY_MESSAGE);
        }
        return message;
    }

    private void saveReport(Update update) {

    }


}
