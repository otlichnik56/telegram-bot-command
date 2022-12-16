package pro.sky.telegrambot.Buttons;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import pro.sky.telegrambot.constants.Constants;

import static pro.sky.telegrambot.constants.Constants.*;


// кнопки, которые отображаются из главного меню, этапа 1 и 2. генерирует три разные клавиатуры
public class ReplyKeyboards {

    public Keyboard generateMainMenuKeyboard() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(Constants.TO_INFO_ABOUT_SHELTER),
                        new KeyboardButton(Constants.TO_ADOPT_DOG)
                },
                new KeyboardButton[]{
                        new KeyboardButton(Constants.KEYBOARD_MAIM_SUBMIT_REPORT),
                        new KeyboardButton(Constants.CALL_VOLUNTEER)
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateAboutShelterMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(ABOUT_SHELTER_INFO),
                        new KeyboardButton(ABOUT_SHELTER_ADDRESS_SCHEDULE)
                },
                new KeyboardButton[]{
                        new KeyboardButton(ABOUT_SHELTER_SAFETY_PRECUATUINS),
                        new KeyboardButton(SEND_CONTACTS)
                },
                new KeyboardButton[]{
                        new KeyboardButton(CALL_VOLUNTEER),
                        new KeyboardButton(TO_MAIN_MENU)
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateAdoptDogMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(ADOPT_DOG_MEETING_RULES),
                        new KeyboardButton(ADOPT_DOG_DOCUMENTS)
                },
                new KeyboardButton[]{
                        new KeyboardButton(ADOPT_DOG_RECOMENDATIONS)

                },
/*                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_TWO_BIG_DOG),
                        new KeyboardButton(KEYBOARD_TWO_INVALID_DOG)
                },*/
                new KeyboardButton[]{
                        new KeyboardButton(ADOPT_DOG_CYNOLOGYSTS_ADVICES),
                        new KeyboardButton(ADOPT_DOG_APPROVED_CYNOLOGYSTS)
                },
                new KeyboardButton[]{
                        new KeyboardButton(ADOPT_DOG_DECLINE_REASONS),
                        new KeyboardButton(SEND_CONTACTS)
                },
                new KeyboardButton[]{
                        new KeyboardButton(CALL_VOLUNTEER),
                        new KeyboardButton(TO_MAIN_MENU)
                });


        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateEmptyMenuKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(TO_MAIN_MENU)
                });


        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

}
