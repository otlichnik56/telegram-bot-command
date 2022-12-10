package pro.sky.telegrambot.Buttons;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import pro.sky.telegrambot.constants.Constants;


// кнопки, котарые отображаются из главного меню, этапа 1 и 2. генерирует три разные клавиатуры
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
        replyKeyboardMarkup.resizeKeyboard(true); //подгоняем размер
        return replyKeyboardMarkup;
    }

    public Keyboard generateOneKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                Constants.KEYBOARD_ONE_SHELTER_INFORMATION,
                Constants.KEYBOARD_ONE_WORK_SCHEDULE ,
                Constants.KEYBOARD_ONE_ACCIDENT_PREVENTION,
                Constants.KEYBOARD_CONTACT,
                Constants.KEYBOARD_CALL_VOLUNTEER);
        replyKeyboardMarkup.resizeKeyboard(true); //подгоняем размер
        return replyKeyboardMarkup;
    }

    public Keyboard generateTwoKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                Constants.KEYBOARD_TWO_DATING_DOG,
                Constants.KEYBOARD_TWO_DOCUMENTS_ADOPT_DOG ,
                Constants.KEYBOARD_TWO_TRANSPORTATION_DOG,
                Constants.KEYBOARD_TWO_SMALL_DOG,
                Constants.KEYBOARD_TWO_BIG_DOG,
                Constants.KEYBOARD_TWO_INVALID_DOG,
                Constants.KEYBOARD_TWO_CYNOLOGIST_ADVICE,
                Constants.KEYBOARD_TWO_GOOD_CYNOLOGIST,
                Constants.KEYBOARD_TWO_NOT_DOG,
                Constants.KEYBOARD_CONTACT,
                Constants.KEYBOARD_CALL_VOLUNTEER);
        replyKeyboardMarkup.resizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.oneTimeKeyboard(true); //скрываем после использования
        return replyKeyboardMarkup;
    }




}
