package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.button.ReplyKeyboards;
import pro.sky.telegrambot.constant.AdminMenuItems;
import pro.sky.telegrambot.constant.ChatSettings;
import pro.sky.telegrambot.constant.MenuItemsNames;
import pro.sky.telegrambot.constant.Strings;
import pro.sky.telegrambot.constant.AdminResponses;
import pro.sky.telegrambot.constant.Responses;
import pro.sky.telegrambot.constant.ShelterType;

import java.util.HashMap;
import java.util.Map;

import static pro.sky.telegrambot.constant.Strings.*;


/**
 * Класс управления через меню в телеграмм боте
 */
@Service
public class MenuService {
    private final ReplyKeyboards keyboards;
    private final TelegramBot telegramBot;
    private final ShelterService dogShelterService;
    private final ShelterService catShelterService;
    private final Map<Long, Responses> pendingResponses;
    private final Map<Long, AdminResponses> adminPendingResponses;
    private final Map<Long, ShelterType> choosedSheltersForUsers;

    public MenuService(ReplyKeyboards keyboards, TelegramBot telegramBot, ShelterService dogShelterService, ShelterService catShelterService) {
        this.keyboards = keyboards;
        this.telegramBot = telegramBot;
        this.dogShelterService = dogShelterService;
        this.catShelterService = catShelterService;
        this.pendingResponses = new HashMap<Long, Responses>();
        this.adminPendingResponses = new HashMap<Long, AdminResponses>();
        this.choosedSheltersForUsers = new HashMap<Long, ShelterType>();
    }


    /**
     *
     * @param message
     */
    public void shelterServiceChooser(Message message) {
        long chatId = message.chat().id();
        if (userChosenShelter(chatId)) {
            switch (choosedSheltersForUsers.get(chatId)) {
                case CAT_SHELTER:
                    messageProcessing(message, catShelterService);
                    break;
                case DOG_SHELTER:
                    messageProcessing(message, dogShelterService);
                    break;
            }
        } else {
            chooseShelter(chatId, message.text());
        }
    }

    /**
     *
     * @param message
     * @param shelterService
     */
    private void messageProcessing(Message message, ShelterService shelterService) {
        long chatId = message.chat().id();
        String messageText = message.text();
        if (isAdministratorUser(chatId)) {
            if (adminPendingResponses.containsKey(chatId)) {
                adminRequestProcessing(chatId, messageText, shelterService);
            } else {
                sendAdminMenuAndReply(chatId, messageText, shelterService);
            }
        } else {
            if (pendingResponses.containsKey(chatId)) {
                requestProcessing(message, shelterService);
            } else {
                sendMenuAndReply(chatId, messageText, shelterService);
            }
        }
    }

    /**
     *
     * @param message
     * @param shelterService
     */
    private void requestProcessing(Message message, ShelterService shelterService) {
        Long chatId = message.chat().id();
        switch (pendingResponses.get(chatId)) {
            case REPORT:
                shelterService.setReport(message);
                break;
            case REQUEST:
                shelterService.getRequest(message);
                break;
            case CONTACT:
                shelterService.setContactFromChat(message);
                break;
        }
        pendingResponses.remove(chatId);
    }

    /**
     *
     * @param chatId
     * @param messageText
     */
    private void chooseShelter(long chatId, String messageText) {
        if (messageText.equals(MenuItemsNames.DOG_SHELTER_CHOOSE)) {
            choosedSheltersForUsers.put(chatId, ShelterType.DOG_SHELTER);
            sendReply(chatId, Strings.DOG_SHELTER_GREETINGS, keyboards.mainMenuKeyboards);
        } else if (messageText.equals(MenuItemsNames.CAT_SHELTER_CHOOSE)) {
            choosedSheltersForUsers.put(chatId, ShelterType.CAT_SHELTER);
            sendReply(chatId, Strings.CAT_SHELTER_GREETINGS, keyboards.mainMenuKeyboards);
        } else {
            sendReply(chatId, SHELTER_MENU_GREETINGS, keyboards.shelterMenu);
        }
    }

    /**
     *
     * @param chatId
     * @return
     */
    private boolean userChosenShelter(long chatId) {
        return choosedSheltersForUsers.containsKey(chatId);
    }

    /**
     *
     * @param chatId
     * @return
     */
    private boolean isAdministratorUser(long chatId) {
        return chatId == ChatSettings.volunteerChatId;
    }

    /**
     *
     * @param chatId
     * @param replyMessageText
     * @param keyboard
     */
    private void sendReply(long chatId, String replyMessageText, Keyboard keyboard) {
        SendMessage message = new SendMessage(chatId, replyMessageText);
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    /**
     *
     * @param chatId
     * @param command
     * @param shelterService
     */
    private void sendMenuAndReply(long chatId, String command, ShelterService shelterService) {
        String replyTextMessage;
        SendMessage message;
        switch (command) {
            case MenuItemsNames.TO_MAIN_MENU:
                sendReply(chatId, WELCOME_MESSAGE_MENU_MAIN, keyboards.mainMenuKeyboards);
                break;
            case MenuItemsNames.TO_INFO_ABOUT_SHELTER:
                sendReply(chatId, WELCOME_MESSAGE_MENU_ABOUT_SHELTER, keyboards.aboutShelterMenuKeyboards);
                break;
            case MenuItemsNames.TO_ADOPT_DOG:
                sendReply(chatId, WELCOME_MESSAGE_MENU_ADOPT_DOG, keyboards.adoptDogMenuKeyboards);
                break;
            case MenuItemsNames.SEND_REPORT:
                pendingResponses.put(chatId, Responses.REPORT);
                sendReply(chatId, SEND_REPORT_OFFER, keyboards.mainMenuKeyboards);
                break;
            case MenuItemsNames.CALL_VOLUNTEER:
                pendingResponses.put(chatId, Responses.REQUEST);
                sendReply(chatId, DESCRIBE_ISSUE, keyboards.mainMenuKeyboards);
                break;
            case MenuItemsNames.SEND_CONTACTS:
                pendingResponses.put(chatId, Responses.CONTACT);
                sendReply(chatId, RECORD_CONTACT, keyboards.mainMenuKeyboards);
                break;
            // меню один
            case MenuItemsNames.ABOUT_SHELTER_INFO:
                replyTextMessage = shelterService.getAbout();
                sendReply(chatId, replyTextMessage, keyboards.aboutShelterMenuKeyboards);
                break;
            case MenuItemsNames.ABOUT_SHELTER_ADDRESS_SCHEDULE:
                replyTextMessage = shelterService.getScheduleAndAddress();
                sendReply(chatId, replyTextMessage, keyboards.aboutShelterMenuKeyboards);
                break;
            case MenuItemsNames.ABOUT_SHELTER_SAFETY_PRECUATUINS:
                replyTextMessage = shelterService.getSafetyPrecautions();
                sendReply(chatId, replyTextMessage, keyboards.aboutShelterMenuKeyboards);
                break;
            // меню два
            case MenuItemsNames.ADOPT_DOG_MEETING_RULES:
                replyTextMessage = shelterService.getMeetingRules();
                sendReply(chatId, replyTextMessage, keyboards.adoptDogMenuKeyboards);
                break;
            case MenuItemsNames.ADOPT_DOG_DOCUMENTS:
                replyTextMessage = shelterService.getDocumentsForAdoption();
                sendReply(chatId, replyTextMessage, keyboards.adoptDogMenuKeyboards);
                break;
            case MenuItemsNames.ADOPT_DOG_RECOMENDATIONS:
                sendReply(chatId, RECOMMENDATIONS_MENU_GREETINGS, keyboards.recommendationMenuKeyboard);
                break;
            case MenuItemsNames.RECOMMENDATIONS_TRANSPORTATION:
                replyTextMessage = shelterService.getTransportationRecommendations();
                sendReply(chatId, replyTextMessage, keyboards.recommendationMenuKeyboard);
                break;
            case MenuItemsNames.RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_ADUALTS:
                replyTextMessage = shelterService.getHomeImprovementsForAdultsRecommendations();
                sendReply(chatId, replyTextMessage, keyboards.recommendationMenuKeyboard);
                break;
            case MenuItemsNames.RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_PUPPIES:
                replyTextMessage = shelterService.getHomeImprovementsForPuppiesRecommendations();
                sendReply(chatId, replyTextMessage, keyboards.recommendationMenuKeyboard);
                break;
            case MenuItemsNames.RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_DISABLED:
                replyTextMessage = shelterService.getHomeImprovementsForDisabledRecommendations();
                sendReply(chatId, replyTextMessage, keyboards.recommendationMenuKeyboard);
                break;
            case MenuItemsNames.ADOPT_DOG_APPROVED_CYNOLOGYSTS:
                //replyTextMessage = shelterService.getApprovedCynologysts();
                //sendReply(chatId, replyTextMessage, keyboards.adoptDogMenuKeyboards);
                //message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.RECOMMENDATIONS_CYNOLOGYSTS_ADVICES:
                //replyTextMessage = shelterService.getCynologystsAdvices();
                //sendReply(chatId, replyTextMessage, keyboards.recommendationMenuKeyboard);
                //message = new SendMessage(chatId, replyTextMessage);
                break;
            case MenuItemsNames.ADOPT_DOG_DECLINE_REASONS:
                replyTextMessage = shelterService.getDeclineReasons();
                sendReply(chatId, replyTextMessage, keyboards.adoptDogMenuKeyboards);
                break;
            default:
                message = new SendMessage(chatId, SORRY_MESSAGE);
        }
    }

    /**
     *
     * @param chatid
     * @param inputText
     * @param shelterService
     */
    private void adminRequestProcessing(long chatid, String inputText, ShelterService shelterService) {
        switch (adminPendingResponses.get(chatid)) {
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
        adminPendingResponses.remove(chatid);
    }

    /**
     *
     * @param chatId
     * @param command
     * @param shelterService
     */
    private void sendAdminMenuAndReply(long chatId, String command, ShelterService shelterService) {
        switch (command) {
            case AdminMenuItems.TO_MAIN_MENU:
                sendReply(chatId, DEFAULT_MESSAGE, keyboards.сontrolMainMenu);
                break;
            case AdminMenuItems.TO_CONTACTS_MENU:
                sendReply(chatId, CONTACTS_MENU, keyboards.contactsControlMenu);
                break;
            case AdminMenuItems.DELETE_CONTACT:
                adminPendingResponses.put(chatId, AdminResponses.DELETE);
                sendReply(chatId, DELETE_CONTACT, keyboards.contactsControlMenu);
                break;
            case AdminMenuItems.APPOINT_GUARDIAN:
                adminPendingResponses.put(chatId, AdminResponses.APPOINT_GUARDIAN);
                sendReply(chatId, APPOINT_GUARDIAN, keyboards.contactsControlMenu);
                break;
            case AdminMenuItems.EXTEND_PROBATION:
                adminPendingResponses.put(chatId, AdminResponses.EXTEND_PROBATION);
                sendReply(chatId, EXTEND_PROBATION, keyboards.contactsControlMenu);
                break;
            case AdminMenuItems.ADD_CONTACT:
                adminPendingResponses.put(chatId, AdminResponses.CREATE);
                sendReply(chatId, ADD_CONTACT, keyboards.contactsControlMenu);
                break;
            case AdminMenuItems.TO_REPORTS_MENU:
                sendReply(chatId, REPORTS_MENU, keyboards.reportsControlMenu);
                break;
            case AdminMenuItems.PRINT_CONTACTS_LIST:
                String contactsAsString = shelterService.printContactsList();
                sendReply(chatId, contactsAsString, keyboards.contactsControlMenu);
                break;
            default:
                sendReply(chatId, DEFAULT_MESSAGE, keyboards.mainMenuKeyboards);
                break;
        }
    }

}