package pro.sky.telegrambot.Buttons;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import pro.sky.telegrambot.constants.Constants;

public class ReplyKeyboards {


    public Keyboard generateMainKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(Constants.KEYBOARD_MAIM_SHELTER_INFORMATION,
                Constants.KEYBOARD_MAIM_ADOPT_DOG ,
                Constants.KEYBOARD_MAIM_SUBMIT_REPORT,
                Constants.KEYBOARD_CALL_VOLUNTEER);
        replyKeyboardMarkup.resizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.oneTimeKeyboard(true); //скрываем после использования
        Keyboard keyboard = replyKeyboardMarkup;
        return keyboard;
    }

    public Keyboard generateOneKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(Constants.KEYBOARD_ONE_SHELTER_INFORMATION,
                Constants.KEYBOARD_ONE_WORK_SCHEDULE ,
                Constants.KEYBOARD_ONE_ACCIDENT_PREVENTION,
                Constants.KEYBOARD_CONTACT,
                Constants.KEYBOARD_CALL_VOLUNTEER);
        replyKeyboardMarkup.resizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.oneTimeKeyboard(true); //скрываем после использования
        Keyboard keyboard = replyKeyboardMarkup;
        return keyboard;
    }

    public Keyboard generateTwoKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(Constants.KEYBOARD_TWO_DATING_DOG,
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
        Keyboard keyboard = replyKeyboardMarkup;
        return keyboard;
    }




}
