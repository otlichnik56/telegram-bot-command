package pro.sky.telegrambot.service;

import java.util.List;

public  class StringPrinter {
    public static String printList(List<String> strings){
        StringBuilder resultString = new StringBuilder();
        for (String string : strings) {
            resultString.append(string);
        }
        return resultString.toString();
    }
}
