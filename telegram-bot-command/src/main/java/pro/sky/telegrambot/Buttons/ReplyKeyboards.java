package pro.sky.telegrambot.Buttons;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import pro.sky.telegrambot.constants.Constants;

import static pro.sky.telegrambot.constants.Constants.*;


// кнопки, которые отображаются из главного меню, этапа 1 и 2. генерирует три разные клавиатуры
public class ReplyKeyboards {

    public Keyboard generateMainKeyboard(){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(Constants.KEYBOARD_MAIM_SHELTER_INFORMATION),
                        new KeyboardButton(Constants.KEYBOARD_MAIM_ADOPT_DOG)
                },
                new KeyboardButton[]{
                        new KeyboardButton(Constants.KEYBOARD_MAIM_SUBMIT_REPORT),
                        new KeyboardButton(Constants.KEYBOARD_CALL_VOLUNTEER)
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateOneKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_ONE_SHELTER_INFORMATION),
                        new KeyboardButton(KEYBOARD_ONE_WORK_SCHEDULE)
                },
                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_ONE_ACCIDENT_PREVENTION),
                        new KeyboardButton(KEYBOARD_CONTACT)
                },
                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_CALL_VOLUNTEER),
                        new KeyboardButton("Главное меню")
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

    public Keyboard generateTwoKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_TWO_DATING_DOG),
                        new KeyboardButton(KEYBOARD_TWO_DOCUMENTS_ADOPT_DOG)
                },
                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_TWO_TRANSPORTATION_DOG),
                        new KeyboardButton(KEYBOARD_TWO_SMALL_DOG)
                },
                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_TWO_BIG_DOG),
                        new KeyboardButton(KEYBOARD_TWO_INVALID_DOG)
                },
                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_TWO_CYNOLOGIST_ADVICE),
                        new KeyboardButton(KEYBOARD_TWO_GOOD_CYNOLOGIST)
                },
                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_TWO_NOT_DOG),
                        new KeyboardButton(KEYBOARD_CONTACT)
                },
                new KeyboardButton[]{
                        new KeyboardButton(KEYBOARD_CALL_VOLUNTEER),
                        new KeyboardButton("Главное меню")
                });
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }

}
