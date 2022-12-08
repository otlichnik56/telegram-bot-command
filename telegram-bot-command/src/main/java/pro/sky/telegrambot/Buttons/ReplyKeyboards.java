package pro.sky.telegrambot.Buttons;

import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;

public class ReplyKeyboards {

    public Keyboard generateMainKeyboard(){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(  "Информация о приюте",
                                                                            "Приютить собаку" ,
                                                                            "Прислать отчет о питомце",
                                                                            "Позвать волонтера" );
        replyKeyboardMarkup.resizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.oneTimeKeyboard(true); //скрываем после использования
        Keyboard keyboard = replyKeyboardMarkup;
        return keyboard;
    }




}
