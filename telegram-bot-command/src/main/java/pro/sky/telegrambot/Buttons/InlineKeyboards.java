package pro.sky.telegrambot.Buttons;


import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import pro.sky.telegrambot.constants.Constants;

import java.util.ArrayList;
import java.util.List;

// Класс не используется
public class InlineKeyboards {


    public Keyboard generateMainKeyboard(){

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_MAIM_SHELTER_INFORMATION).callbackData(Constants.KEYBOARD_MAIM_SHELTER_INFORMATION)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_MAIM_ADOPT_DOG).callbackData(Constants.KEYBOARD_MAIM_ADOPT_DOG)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_MAIM_SUBMIT_REPORT).callbackData(Constants.KEYBOARD_MAIM_SUBMIT_REPORT)
                },
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(Constants.KEYBOARD_CALL_VOLUNTEER).callbackData(Constants.KEYBOARD_CALL_VOLUNTEER),
                });

        //InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(new InlineKeyboardButton(Constants.KEYBOARD_MAIM_SHELTER_INFORMATION));
        //inlineKeyboardMarkup.addRow(new InlineKeyboardButton(Constants.KEYBOARD_MAIM_SHELTER_INFORMATION));
        //inlineKeyboardMarkup.addRow(new InlineKeyboardButton(Constants.KEYBOARD_MAIM_ADOPT_DOG));
        //inlineKeyboardMarkup.addRow(new InlineKeyboardButton(Constants.KEYBOARD_MAIM_SUBMIT_REPORT));
        //inlineKeyboardMarkup.addRow(new InlineKeyboardButton(Constants.KEYBOARD_CALL_VOLUNTEER));
        //inlineKeyboardMarkup.inlineKeyboard();
        return inlineKeyboard;
    }

    public void never() {









        List<List<InlineKeyboardButton>> buttonsMain = new ArrayList<>();

        List<InlineKeyboardButton> buttonShelterInformation = new ArrayList<>();
        buttonShelterInformation.add(new InlineKeyboardButton(Constants.KEYBOARD_MAIM_SHELTER_INFORMATION));
        buttonsMain.add(buttonShelterInformation);

        List<InlineKeyboardButton> buttonAdoptDog = new ArrayList<>();
        buttonAdoptDog.add(new InlineKeyboardButton(Constants.KEYBOARD_MAIM_ADOPT_DOG));
        buttonsMain.add(buttonAdoptDog);

        List<InlineKeyboardButton> buttonSubmitReport = new ArrayList<>();
        buttonSubmitReport.add(new InlineKeyboardButton(Constants.KEYBOARD_MAIM_SUBMIT_REPORT));
        buttonsMain.add(buttonSubmitReport);

        List<InlineKeyboardButton> buttonCallVolunteer = new ArrayList<>();
        buttonCallVolunteer.add(new InlineKeyboardButton(Constants.KEYBOARD_CALL_VOLUNTEER));
        buttonsMain.add(buttonCallVolunteer);

    }

}
