package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.Buttons.ReplyKeyboards;
import pro.sky.telegrambot.constants.MenuItemsNames;
import pro.sky.telegrambot.entitydatabase.Person;
import pro.sky.telegrambot.entitydatabase.Report;
import pro.sky.telegrambot.model.Responses;
import pro.sky.telegrambot.repositoty.PersonRepository;
import pro.sky.telegrambot.repositoty.ReportRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static pro.sky.telegrambot.constants.Strings.*;

@Service
public class TelegramBotUpdatesListenerVlad implements UpdatesListener {
    private final ShelterService shelterService;
    private final ReplyKeyboards keyboards;

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListenerVlad.class);
    @Autowired
    private TelegramBot telegramBot;


    private Map<Long, Responses> waitedResponses;
    private final static Long volunteerChatId = 202671625L;

    public TelegramBotUpdatesListenerVlad(ReplyKeyboards keyboards, ShelterService shelterService) {
        this.keyboards = keyboards;
        this.shelterService = shelterService;
        waitedResponses = new HashMap<Long, Responses>();
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);

            long chatId = update.message().chat().id();
            String messageText = update.message().text();

            try {
                if (waitedResponses.containsKey(chatId)) {
                    processRequest(update.message());
                } else {
                    sendMenuAndReplies(chatId, messageText);
                }

            } catch (Exception e) {

            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void processRequest(Message message) {
        Long chatid = message.chat().id();
        switch (waitedResponses.get(chatid)) {
            case REPORT:
                shelterService.getReport(message);
                break;
            case REQUEST:
                shelterService.getRequest(message);
                break;
            case CONTACT:
                shelterService.getContact(message);
                break;
        }
        waitedResponses.remove(chatid);

    }

    private void sendMenuAndReplies(long chatId, String messageText) {
        String replyTextMessage;
        SendMessage message;

        switch (messageText) {
            // главное меню и общее
            case MenuItemsNames.START:
            case MenuItemsNames.TO_MAIN_MENU:
                message = new SendMessage(chatId, WELCOME_MESSAGE_MENU_MAIN);
                message.replyMarkup(keyboards.mainMenuKeyboards);
                break;
            case MenuItemsNames.TO_INFO_ABOUT_SHELTER:
                message = new SendMessage(chatId, WELCOME_MESSAGE_MENU_ABOUT_SHELTER);
                message.replyMarkup(keyboards.aboutShelterMenuKeyboards);
                break;
            case MenuItemsNames.TO_ADOPT_DOG:
                message = new SendMessage(chatId, WELCOME_MESSAGE_MENU_ADOPT_DOG);
                message.replyMarkup(keyboards.adoptDogMenuKeyboards);
                break;
            case MenuItemsNames.SEND_REPORT:
                message = new SendMessage(chatId, SEND_REPORT_OFFER);
                waitedResponses.put(chatId, Responses.REPORT);
                break;

            case MenuItemsNames.CALL_VOLUNTEER:

                //callVolunteer(inputMessage);
                message = new SendMessage(chatId, WELCOME_MESSAGE_FOUR);
                message.replyMarkup(keyboards.emptyKeyboard);
                waitedResponses.put(chatId, Responses.REQUEST);
                break;
            case MenuItemsNames.SEND_CONTACTS:
                waitedResponses.put(chatId, Responses.CONTACT);
                message = new SendMessage(chatId, RECORD_CONTACT);
                message.replyMarkup(keyboards.emptyKeyboard);
                //message = new SendMessage(chatId, RECORD_CONTACT);
                //recordStatus = true;
                break;
            // меню один
            case MenuItemsNames.ABOUT_SHELTER_INFO:
                replyTextMessage = shelterService.getAbout();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.ABOUT_SHELTER_ADDRESS_SCHEDULE:
                replyTextMessage = shelterService.getScheduleAndAdress();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.ABOUT_SHELTER_SAFETY_PRECUATUINS:
                replyTextMessage = shelterService.getSafetyPrecautions();
                message = new SendMessage(chatId, replyTextMessage);
                break;

            // меню два
            case MenuItemsNames.ADOPT_DOG_MEETING_RULES:
                replyTextMessage = shelterService.getmeetingRules();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.ADOPT_DOG_DOCUMENTS:
                replyTextMessage = shelterService.getDocumentsForAdpotion();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.ADOPT_DOG_RECOMENDATIONS:

                message = new SendMessage(chatId, RECOMMENDATIONS_MENU_GREETINGS);
                message.replyMarkup(keyboards.recommendationMenuKeyboard);
                break;
            case MenuItemsNames.RECOMMENDATIONS_TRANSPORTATION:
                replyTextMessage = shelterService.getTransportationRecommendations();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_ADUALTS:
                replyTextMessage = shelterService.getHomeImprovementsForAdultsRecommendations();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_PUPPIES:
                replyTextMessage = shelterService.getHomeImprovementsForPuppiesRecommendations();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_DISABLED:
                replyTextMessage = shelterService.getHomeImprovementsForDisabledRecommendations();
                message = new SendMessage(chatId, replyTextMessage);
                break;


            case MenuItemsNames.ADOPT_DOG_APPROVED_CYNOLOGYSTS:
                replyTextMessage = shelterService.getApprovedCynologysts();
                message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.RECOMMENDATIONS_CYNOLOGYSTS_ADVICES:
                replyTextMessage = shelterService.getCynologystsAdvices();
                message = new SendMessage(chatId, replyTextMessage);
                break;

            case MenuItemsNames.ADOPT_DOG_DECLINE_REASONS:
                replyTextMessage = shelterService.getDeclineReasons();
                message = new SendMessage(chatId, replyTextMessage);
                break;

            default:
                message = new SendMessage(chatId, SORRY_MESSAGE);
        }
        telegramBot.execute(message);
    }






}
