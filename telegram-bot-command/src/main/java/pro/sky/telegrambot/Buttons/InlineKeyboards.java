package pro.sky.telegrambot.Buttons;


import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import pro.sky.telegrambot.constants.Constants;


public class InlineKeyboards {

    public Keyboard generateMainKeyboard(){
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_MAIM_SHELTER_INFORMATION).callbackData("/information")
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_MAIM_ADOPT_DOG).callbackData(Constants.KEYBOARD_MAIM_ADOPT_DOG)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_MAIM_SUBMIT_REPORT).callbackData(Constants.KEYBOARD_MAIM_SUBMIT_REPORT)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_CALL_VOLUNTEER).callbackData(Constants.KEYBOARD_CALL_VOLUNTEER)
                });
    }

    public Keyboard generateOneKeyboard(){
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_ONE_SHELTER_INFORMATION).callbackData(Constants.KEYBOARD_ONE_SHELTER_INFORMATION)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_ONE_WORK_SCHEDULE).callbackData(Constants.KEYBOARD_ONE_WORK_SCHEDULE)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_ONE_ACCIDENT_PREVENTION).callbackData(Constants.KEYBOARD_ONE_ACCIDENT_PREVENTION)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_CONTACT).callbackData(Constants.KEYBOARD_CONTACT)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_CALL_VOLUNTEER).callbackData(Constants.KEYBOARD_CALL_VOLUNTEER)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_MAIN_MENU).callbackData(Constants.KEYBOARD_MAIN_MENU)
                });
    }

    public Keyboard generateTwoKeyboard(){
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_TWO_DATING_DOG).callbackData(Constants.KEYBOARD_TWO_DATING_DOG)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_TWO_DOCUMENTS_ADOPT_DOG).callbackData(Constants.KEYBOARD_TWO_DOCUMENTS_ADOPT_DOG)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_TWO_TRANSPORTATION_DOG).callbackData(Constants.KEYBOARD_TWO_TRANSPORTATION_DOG)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_TWO_SMALL_DOG).callbackData(Constants.KEYBOARD_TWO_SMALL_DOG)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_TWO_BIG_DOG).callbackData(Constants.KEYBOARD_TWO_BIG_DOG)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_TWO_INVALID_DOG).callbackData(Constants.KEYBOARD_TWO_INVALID_DOG)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_TWO_CYNOLOGIST_ADVICE).callbackData(Constants.KEYBOARD_TWO_CYNOLOGIST_ADVICE)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_TWO_GOOD_CYNOLOGIST).callbackData(Constants.KEYBOARD_TWO_GOOD_CYNOLOGIST)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_TWO_NOT_DOG).callbackData(Constants.KEYBOARD_TWO_NOT_DOG)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_CONTACT).callbackData(Constants.KEYBOARD_CONTACT)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_CALL_VOLUNTEER).callbackData(Constants.KEYBOARD_CALL_VOLUNTEER)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_MAIN_MENU).callbackData(Constants.KEYBOARD_MAIN_MENU)
                });
    }

}
