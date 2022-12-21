package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.Buttons.Keyboards;
import pro.sky.telegrambot.Buttons.ReplyKeyboards;
import pro.sky.telegrambot.entitydatabase.Person;
import pro.sky.telegrambot.repositoty.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pro.sky.telegrambot.constants.Constants.*;

@Service
public class TelegramBotUpdatesListenerVlad implements UpdatesListener {
    private final PersonRepository contactRepository;
    private Keyboards keyboard;
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListenerVlad.class);
    private final static Keyboard replyMainKeyboards = new ReplyKeyboards().generateMainMenuKeyboard();
    private final static Keyboard replyAboutShelterKeyboards = new ReplyKeyboards().generateAboutShelterMenuKeyboard();
    private final static Keyboard replyAdoptDogKeyboards = new ReplyKeyboards().generateAdoptDogMenuKeyboard();

    private final static Keyboard replyEmptyKeyboard = new ReplyKeyboards().generateEmptyMenuKeyboard();


    private Boolean recordStatus = false;
    private boolean isLeavingRequest = false;
    private boolean isLeavingContact = false;
    private final static Long volunteerChatId = 202671625L;

    public TelegramBotUpdatesListenerVlad(PersonRepository contactRepository, ShelterService shelterService) {
        this.contactRepository = contactRepository;
        this.shelterService = shelterService;
        this.keyboard = new Keyboards();
    }

    private final ShelterService shelterService;


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

            Message inputMessage = update.message();
            if (isLeavingRequest) {
                callVolunteer(inputMessage);
            } else if (isLeavingContact) {
                getContact(inputMessage);
            } else {
                SendMessage replyMessage = createMessage(inputMessage);
                telegramBot.execute(replyMessage);
            }

        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void getContact(Message inputMessage) {
        String parsedPhoneString = "";
        String contactName = "";
        String inputText = inputMessage.text();

        Pattern phonePattern = Pattern.compile("^((8|\\+7)[\\-\\s]?)?\\(?\\d{3}\\)?[\\d\\-\\s]{7,10}");
        Pattern letterPattern = Pattern.compile("[^0-9\\+\\(\\)\\s\\-\\_]");

        Matcher letterMatcher = letterPattern.matcher(inputText);
        Matcher phoneMatcher = phonePattern.matcher(inputText);
        if(phoneMatcher.find()){
            parsedPhoneString = phoneMatcher.group();
        }
        if(letterMatcher.find()){
            contactName = inputText.substring(letterMatcher.start(0));
        }
        String formattedPhoneString = parsedPhoneString.replaceAll("[\\s\\-\\(\\)]","");
if(formattedPhoneString.charAt(0)=='+' && formattedPhoneString.charAt(1)=='7') {
    formattedPhoneString = "8" + formattedPhoneString.substring(2);
} else if (formattedPhoneString.charAt(0)=='9') {
formattedPhoneString = "8" + formattedPhoneString;
}
        Person newContact = new Person(inputMessage.chat().id(),
                formattedPhoneString, contactName, true);
saveContact(newContact);
                /*
                public Person(Long chatId, String numberPhone, String fullName, Boolean status) {
        this.chatId = chatId;
        this.numberPhone = numberPhone;
        this.fullName = fullName;
        this.status = status;
    }
                 */


        System.out.println("phone  = " + formattedPhoneString);
        System.out.println("name = " + contactName);

        isLeavingContact=false;

    }

    private void callVolunteer(Message inputMessage) {
        String nickName = inputMessage.from().username();
        String requestText = inputMessage.text();

        SendMessage messageVolunteer = new SendMessage(inputMessage.chat().id(), MESSAGE_FOR_VOLUNTEER + "\n " + "@" + nickName + "\n" + requestText );
        telegramBot.execute(messageVolunteer);
        SendMessage replyMessage = new SendMessage(inputMessage.chat().id(), THANKS_FOR_REQUEST );

        telegramBot.execute(replyMessage);
        isLeavingRequest=false;

    }

    private void sendReply(SendMessage replyMessage) {
        telegramBot.execute(replyMessage);
    }

/*
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
    */


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

    private SendMessage createMessage(Message inputMessage) {
        long chatId = inputMessage.chat().id();
        String inputTextMessage = inputMessage.text();
        String replyTextMessage;
        SendMessage message;


        switch (inputTextMessage) {
            // главное меню и общее
            case START:
            case TO_MAIN_MENU:
                message = new SendMessage(chatId, WELCOME_MESSAGE_MENU_MAIN);
                message.replyMarkup(replyMainKeyboards);
                break;
            case TO_INFO_ABOUT_SHELTER:
                message = new SendMessage(chatId, WELCOME_MESSAGE_MENU_ABOUT_SHELTER);
                message.replyMarkup(replyAboutShelterKeyboards);
                break;
            case TO_ADOPT_DOG:
                message = new SendMessage(chatId, WELCOME_MESSAGE_MENU_ADOPT_DOG);
                message.replyMarkup(replyAdoptDogKeyboards);
                break;
            case KEYBOARD_MAIM_SUBMIT_REPORT:
                message = new SendMessage(chatId, WELCOME_MESSAGE_THREE);
                recordStatus = true;
                break;

            case CALL_VOLUNTEER:
                isLeavingRequest=true;
                //callVolunteer(inputMessage);
                message = new SendMessage(chatId, WELCOME_MESSAGE_FOUR);
                message.replyMarkup(replyEmptyKeyboard);

                break;
            case SEND_CONTACTS:
                isLeavingContact = true;
                message = new SendMessage(chatId, RECORD_CONTACT);
                message.replyMarkup(replyEmptyKeyboard);
                //message = new SendMessage(chatId, RECORD_CONTACT);
                //recordStatus = true;
                break;
            // меню один
            case ABOUT_SHELTER_INFO:
                replyTextMessage = shelterService.getAbout();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case ABOUT_SHELTER_ADDRESS_SCHEDULE:
                replyTextMessage = shelterService.getScheduleAndAdress();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case ABOUT_SHELTER_SAFETY_PRECUATUINS:
                replyTextMessage = shelterService.getSafetyPrecautions();
                message = new SendMessage(chatId, replyTextMessage);
                break;

            // меню два
            case ADOPT_DOG_MEETING_RULES:
                replyTextMessage= shelterService.getmeetingRules();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case ADOPT_DOG_DOCUMENTS:
                replyTextMessage = shelterService.getDocumentsForAdpotion();
                message = new SendMessage(chatId, replyTextMessage);
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
                replyTextMessage = shelterService.getDeclineReasons();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            default:
                message = new SendMessage(chatId, SORRY_MESSAGE);
        }
        return message;
    }

    private void saveContact(Person person) {
        contactRepository.save(person);

    }
    /*
    private void callVolunteer(Message message) {

        SendMessage messageVolunteer = new SendMessage(volunteerChatId, MESSAGE_FOR_VOLUNTEER + " " + "@" + message.from().username());
        telegramBot.execute(messageVolunteer);

    }
*/
    private void saveReport(Update update) {

    }


}
