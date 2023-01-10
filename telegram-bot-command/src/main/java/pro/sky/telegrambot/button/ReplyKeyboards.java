package pro.sky.telegrambot.button;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.constant.AdminMenuItems;
import pro.sky.telegrambot.constant.MenuItemsNames;


// кнопки, которые отображаются из главного меню, этапа 1 и 2. генерирует три разные клавиатуры

/**
 * Клавиатуры всех меню бота
 */
@Component
public class ReplyKeyboards {
    /*
        public final static Keyboard replyMainKeyboards = new ReplyKeyboards().generateMainMenuKeyboard();
        public final static Keyboard replyAboutShelterKeyboards = new ReplyKeyboards().generateAboutShelterMenuKeyboard();
        public final static Keyboard replyAdoptDogKeyboards = new ReplyKeyboards().generateAdoptDogMenuKeyboard();
        public final static Keyboard replyEmptyKeyboard = new ReplyKeyboards().generateEmptyMenuKeyboard();
        public final static Keyboard recommendationKeyboard = new ReplyKeyboards().generateRecommendationMenuKeyboard();
    */
    /**
     * Главное меню пользователей
     */
    public final Keyboard mainMenuKeyboards;
    /**
     * Меню пользователя с информацией о приюте
     */
    public final Keyboard aboutShelterMenuKeyboards;
    /**
     * меню пользователя с информацией по усыновлению собаки
     */
    public final Keyboard adoptDogMenuKeyboards;
    /**
     * пустая клавиатура при выполнении запроса
     */
    public final Keyboard emptyKeyboard;
    /**
     * меню пользователя со списками рекоммендаций
     */
    public final Keyboard recommendationMenuKeyboard;
    /**
     * Главное меню администратора
     */
    public final Keyboard сontrolMainMenu;
    /**
     * Меню администратора по управлению контактами
     */
    public final Keyboard contactsControlMenu;
    /**
     * Меню администратора по управлению отчетами
     */
    public final Keyboard reportsControlMenu;
    public final Keyboard shelterMenu;


    public ReplyKeyboards() {
        сontrolMainMenu = generateControlMainKeyboard();
        contactsControlMenu = generateContactsMenuKeyboard();
        reportsControlMenu = generateControlTwoKeyboard();


        shelterMenu = generateShelterMenuKeyboard();
        mainMenuKeyboards = generateMainMenuKeyboard();
        aboutShelterMenuKeyboards = generateAboutShelterMenuKeyboard();
        adoptDogMenuKeyboards = generateAdoptDogMenuKeyboard();
        emptyKeyboard = generateEmptyMenuKeyboard();
        recommendationMenuKeyboard = generateRecommendationMenuKeyboard();
    }

    public Keyboard generateMainMenuKeyboard() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.TO_INFO_ABOUT_SHELTER),
                        new KeyboardButton(MenuItemsNames.TO_ADOPT_DOG)
                },
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.SEND_REPORT),
                        new KeyboardButton(MenuItemsNames.CALL_VOLUNTEER)
                },
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.TO_SHELTER_MENU)
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateShelterMenuKeyboard() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(MenuItemsNames.DOG_SHELTER_CHOOSE),
                new KeyboardButton(MenuItemsNames.CAT_SHELTER_CHOOSE));
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateAboutShelterMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.ABOUT_SHELTER_INFO),
                        new KeyboardButton(MenuItemsNames.ABOUT_SHELTER_ADDRESS_SCHEDULE)
                },
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.ABOUT_SHELTER_SAFETY_PRECUATUINS),
                        new KeyboardButton(MenuItemsNames.SEND_CONTACTS)
                },
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.CALL_VOLUNTEER),
                        new KeyboardButton(MenuItemsNames.TO_MAIN_MENU)
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateAdoptDogMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.ADOPT_DOG_MEETING_RULES),
                        new KeyboardButton(MenuItemsNames.ADOPT_DOG_DOCUMENTS)
                },

                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.ADOPT_DOG_RECOMENDATIONS),
                        new KeyboardButton(MenuItemsNames.ADOPT_DOG_APPROVED_CYNOLOGYSTS)
                },
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.ADOPT_DOG_DECLINE_REASONS),
                        new KeyboardButton(MenuItemsNames.SEND_CONTACTS)
                },
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.CALL_VOLUNTEER),
                        new KeyboardButton(MenuItemsNames.TO_MAIN_MENU)
                });


        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateEmptyMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(MenuItemsNames.TO_MAIN_MENU));


        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateRecommendationMenuKeyboard() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.RECOMMENDATIONS_TRANSPORTATION),
                        new KeyboardButton(MenuItemsNames.RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_PUPPIES)
                },
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_ADUALTS),
                        new KeyboardButton(MenuItemsNames.RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_DISABLED)
                },
                new KeyboardButton[]{
                        new KeyboardButton(MenuItemsNames.RECOMMENDATIONS_CYNOLOGYSTS_ADVICES),
                        new KeyboardButton(MenuItemsNames.TO_MAIN_MENU)
                });
        //replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateControlMainKeyboard() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(AdminMenuItems.TO_CONTACTS_MENU),
                new KeyboardButton(AdminMenuItems.TO_REPORTS_MENU),
                new KeyboardButton(AdminMenuItems.TO_FILES_MENU)
        );
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateContactsMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(AdminMenuItems.PRINT_CONTACTS_LIST),
                        new KeyboardButton(AdminMenuItems.DELETE_CONTACT)
                },
                new KeyboardButton[]{
                        new KeyboardButton(AdminMenuItems.ADD_CONTACT),
                        new KeyboardButton(AdminMenuItems.EXTEND_PROBATION)
                },
                new KeyboardButton[]{
                        new KeyboardButton(AdminMenuItems.APPOINT_GUARDIAN),
                },
                new KeyboardButton[]{

                        new KeyboardButton(AdminMenuItems.WATCH_DEBTORS),
                        new KeyboardButton(AdminMenuItems.TO_MAIN_MENU)
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateControlTwoKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton("Продлить срок"),
                        new KeyboardButton("Список должников")
                },
                new KeyboardButton[]{
                        new KeyboardButton("Вернуться назад")
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

}
