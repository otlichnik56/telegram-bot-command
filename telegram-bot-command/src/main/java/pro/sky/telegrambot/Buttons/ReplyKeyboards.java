package pro.sky.telegrambot.Buttons;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.constants.MenuItemsNames;


// кнопки, которые отображаются из главного меню, этапа 1 и 2. генерирует три разные клавиатуры
@Component
public class ReplyKeyboards {
    /*
        public final static Keyboard replyMainKeyboards = new ReplyKeyboards().generateMainMenuKeyboard();
        public final static Keyboard replyAboutShelterKeyboards = new ReplyKeyboards().generateAboutShelterMenuKeyboard();
        public final static Keyboard replyAdoptDogKeyboards = new ReplyKeyboards().generateAdoptDogMenuKeyboard();
        public final static Keyboard replyEmptyKeyboard = new ReplyKeyboards().generateEmptyMenuKeyboard();
        public final static Keyboard recommendationKeyboard = new ReplyKeyboards().generateRecommendationMenuKeyboard();
    */
    public final  Keyboard mainMenuKeyboards;
    public final  Keyboard aboutShelterMenuKeyboards;
    public final  Keyboard adoptDogMenuKeyboards;
    public final  Keyboard emptyKeyboard;
    public final  Keyboard recommendationMenuKeyboard;
    public final  Keyboard replyControlMainKeyboards;
    public final  Keyboard replyControlOneKeyboards;
    public final  Keyboard replyControlTwoKeyboards;

    public ReplyKeyboards() {
        this.replyControlMainKeyboards = generateControlMainKeyboard();
        this.replyControlOneKeyboards = generateControlOneKeyboard();
        this.replyControlTwoKeyboards = generateControlTwoKeyboard();
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
                });
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

    public Keyboard generateControlMainKeyboard(){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton("Контакты"),
                        new KeyboardButton("Усыновители")
                },
                new KeyboardButton[]{
                        new KeyboardButton("Испытательный срок"),
                        new KeyboardButton("Отчёты")
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateControlOneKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton("Посмотреть"),
                        new KeyboardButton("Добавить")
                },
                new KeyboardButton[]{
                        new KeyboardButton("Удалить"),
                        new KeyboardButton("Вернуться назад")
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateControlTwoKeyboard(){
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
