package pro.sky.telegrambot.Buttons;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

// Класс не используется
public class InlineKeyboards {

    public void generate(){

        List<List<InlineKeyboardButton>> buttonsMain = new ArrayList<>();

        List<InlineKeyboardButton> buttonShelterInformation = new ArrayList<>();
        buttonShelterInformation.add(new InlineKeyboardButton().setText("Информация о приюте").setCallbackData("CallFi4a"));
        buttonsMain.add(buttonShelterInformation);

        List<InlineKeyboardButton> buttonAdoptDog = new ArrayList<>();
        buttonAdoptDog.add(new InlineKeyboardButton().setText("приютить собаку").setCallbackData("CallFi4a"));
        buttonsMain.add(buttonAdoptDog);

        List<InlineKeyboardButton> buttonSubmitReport = new ArrayList<>();
        buttonSubmitReport.add(new InlineKeyboardButton().setText("Прислать отчет о питомце").setCallbackData("CallFi4a"));
        buttonsMain.add(buttonSubmitReport);

        List<InlineKeyboardButton> buttonCallVolunteer = new ArrayList<>();
        buttonCallVolunteer.add(new InlineKeyboardButton().setText("Позвать волонтера").setCallbackData("CallFi4a"));
        buttonsMain.add(buttonCallVolunteer);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(buttonsMain);
    }
}
