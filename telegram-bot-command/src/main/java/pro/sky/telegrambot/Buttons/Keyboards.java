package pro.sky.telegrambot.Buttons;


import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import pro.sky.telegrambot.constants.Constants;

public class Keyboards {
    private  final Keyboard mainMenuKeyboard;
    private  final Keyboard aboutMenuKeyboard;
    private  final Keyboard getDogMenuKeyboard ;
    private   final Keyboard recommedationMenuKeboard;

    public Keyboards() {
        this.mainMenuKeyboard = generateMainMenuKeyboard();
        aboutMenuKeyboard = new ReplyKeyboardMarkup("");
        getDogMenuKeyboard = new ReplyKeyboardMarkup("");
        recommedationMenuKeboard = new ReplyKeyboardMarkup("");



    }

    public Keyboard getMainMenuKeyboard() {
        return mainMenuKeyboard;
    }

    public Keyboard getAboutMenuKeyboard() {
        return aboutMenuKeyboard;
    }

    public Keyboard getGetDogMenuKeyboard() {
        return getDogMenuKeyboard;
    }

    public Keyboard getRecommedationMenuKeboard() {
        return recommedationMenuKeboard;
    }

    public Keyboard generateMainMenuKeyboard(){
        ReplyKeyboardMarkup menuKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton[]{
                new KeyboardButton(Constants.TO_INFO_ABOUT_SHELTER),
       new KeyboardButton(Constants.TO_ADOPT_DOG),
        new KeyboardButton(Constants.KEYBOARD_MAIM_SUBMIT_REPORT),
       new KeyboardButton(Constants.CALL_VOLUNTEER)
        });
        menuKeyboardMarkup.resizeKeyboard(true);
        return menuKeyboardMarkup;

    }


}
