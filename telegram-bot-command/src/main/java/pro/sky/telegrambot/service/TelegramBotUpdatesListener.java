package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.Buttons.ReplyKeyboards;
import pro.sky.telegrambot.constants.AdminMenuItems;
import pro.sky.telegrambot.constants.MenuItemsNames;

import pro.sky.telegrambot.constants.Strings;
import pro.sky.telegrambot.model.AdminResponses;
import pro.sky.telegrambot.model.Responses;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pro.sky.telegrambot.constants.Strings.*;

/**
 * сервис будет делиться на 3 других, временно существует в таком виде
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final ShelterService shelterService;
    private final ReplyKeyboards keyboards;
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    @Autowired
    private TelegramBot telegramBot;

    private Map<Long, Responses> pendingResponses;
    private Map<Long, AdminResponses> adminPendingResponses;
    private final static Long volunteerChatId = 202671625L;

    public TelegramBotUpdatesListener(ReplyKeyboards keyboards, ShelterService shelterService) {
        this.keyboards = keyboards;
        this.shelterService = shelterService;
        this.adminPendingResponses = new HashMap<Long, AdminResponses>();
        pendingResponses = new HashMap<Long, Responses>();
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
            if (chatId == volunteerChatId) {
                if (adminPendingResponses.containsKey(volunteerChatId)) {
                    adminRequestProccessing(update.message());
                } else {
                    adminMenu(chatId, messageText);
                }
            } else {
                if (pendingResponses.containsKey(chatId)) {
                    requestProccessing(update.message());
                } else {
                    sendMenuAndReplies(chatId, messageText);
                }

            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void requestProccessing(Message message) {
        Long chatid = message.chat().id();
        switch (pendingResponses.get(chatid)) {
            case REPORT:
                shelterService.getReport(message);
                break;
            case REQUEST:
                shelterService.getRequest(message);
                break;
            case CONTACT:
                shelterService.getContactFromChat(message);
                break;
        }
        pendingResponses.remove(chatid);
    }

    private void sendMenuAndReplies(long chatId, String command) {
        String replyTextMessage;
        SendMessage message;
        switch (command) {
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
                pendingResponses.put(chatId, Responses.REPORT);
                break;
            case MenuItemsNames.CALL_VOLUNTEER:
                message = new SendMessage(chatId, WELCOME_MESSAGE_FOUR);
                message.replyMarkup(keyboards.emptyKeyboard);
                pendingResponses.put(chatId, Responses.REQUEST);
                break;
            case MenuItemsNames.SEND_CONTACTS:
                pendingResponses.put(chatId, Responses.CONTACT);
                message = new SendMessage(chatId, RECORD_CONTACT);
                message.replyMarkup(keyboards.emptyKeyboard);
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

    private void adminRequestProccessing(Message message) {
        Long chatid = message.chat().id();
        String inputText = message.text();
        switch (adminPendingResponses.get(volunteerChatId)) {
            case DELETE:
                shelterService.deleteContact(inputText);
                break;
            case APPOINT_GUARDIAN:
                shelterService.appointGuardian(inputText);
                break;
            case CREATE:
                shelterService.addContact(chatid, inputText);
                break;
            case EXTEND_PROBATION:
                shelterService.extendProbation(inputText);
                break;
        }
        adminPendingResponses.remove(volunteerChatId);
    }


    private void adminMenu(long chatId, String command) {
        SendMessage message;
        switch (command) {
            case "/start":
            case AdminMenuItems.TO_MAIN_MENU:
                message = new SendMessage(chatId, Strings.ADMIN_MAIN_MENU);
                message.replyMarkup(keyboards.сontrolMainMenu);
                break;
            case AdminMenuItems.TO_CONTACTS_MENU:
                message = new SendMessage(chatId, Strings.CONTACTS_MENU);
                message.replyMarkup(keyboards.contactsControlMenu);
                break;
            case AdminMenuItems.DELETE_CONTACT:
                adminPendingResponses.put(chatId, AdminResponses.DELETE);
                message = new SendMessage(chatId, Strings.DELETE_CONTACT);
                break;
            case AdminMenuItems.APPOINT_GUARDIAN:
                adminPendingResponses.put(chatId, AdminResponses.APPOINT_GUARDIAN);
                message = new SendMessage(chatId, Strings.APPOINT_GUARDIAN);
                break;
            case AdminMenuItems.EXTEND_PROBATION:
                adminPendingResponses.put(chatId, AdminResponses.EXTEND_PROBATION);
                message = new SendMessage(chatId, Strings.EXTEND_PROBATION);
                break;
            case AdminMenuItems.ADD_CONTACT:
                adminPendingResponses.put(chatId, AdminResponses.CREATE);
                message = new SendMessage(chatId, Strings.ADD_CONTACT);
                break;
            case AdminMenuItems.TO_REPORTS_MENU:
                message = new SendMessage(chatId, Strings.REPORTS_MENU);
                break;
            case AdminMenuItems.PRINT_CONTACTS_LIST:
                String contactsAsString = shelterService.printContactsList();
                message = new SendMessage(chatId, contactsAsString);
                break;
            default:
                message = new SendMessage(volunteerChatId, "Ошибка ввода данных. Попробуйте снова и внимательно!");
                break;
        }
        telegramBot.execute(message);
    }

}
