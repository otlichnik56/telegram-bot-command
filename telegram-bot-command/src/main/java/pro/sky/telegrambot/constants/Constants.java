package pro.sky.telegrambot.constants;

public class Constants {


    // заготовленные фразы, которые пишет бот. например, приветствие
    public static final String WELCOME_MESSAGE_MAIN = "Добро пожаловать в наш приют для собак. \nВы можете воспользоваться одним из пунктов гланого меню ";
    public static final String WELCOME_MESSAGE_ONE = "Ещё раз здравствуйте. Вы можете узнать следующую инфу о нашем приюте ";
    public static final String WELCOME_MESSAGE_TWO = "Готов предоставить вам следующую полезную инфу ";
    public static final String WELCOME_MESSAGE_THREE = "Рад вам помочь, я готов принять ваш отчёт. В ежедневный отчет входит следующая информация: \n" +
            "- Фото животного.\n" + "- Рацион животного.\n" + "- Общее самочувствие и привыкание к новому месту.\n" +
            "- Изменение в поведении: отказ от старых привычек, приобретение новых.";
    public static final String WELCOME_MESSAGE_FOUR = "Спасибо за обращение, волонтёр скоро с вами свяжется ";
    public static final String SORRY_MESSAGE = "Извинините, я вас не понимаю ";


    //название кнопок из главного меню + прислать отчёт и позвать волонтёра и оставить контактные данные
    public static final String KEYBOARD_MAIM_SHELTER_INFORMATION = "Узнать информацию о приюте";
    public static final String KEYBOARD_MAIM_ADOPT_DOG = "Как взять собаку из приюта";
    public static final String KEYBOARD_MAIM_SUBMIT_REPORT = "Прислать отчет о питомце";
    public static final String KEYBOARD_CALL_VOLUNTEER = "Позвать волонтера";
    public static final String KEYBOARD_CONTACT = "Оставить контактные данные для связи";
    public static final String KEYBOARD_MAIN_MENU = "Вернуться в главное меню";


    //название кнопок из меню 1, которое выпадает по нажатию кнопки "Информация о приюте" из главного меню
    public static final String KEYBOARD_ONE_SHELTER_INFORMATION = "История приюта";
    public static final String KEYBOARD_ONE_WORK_SCHEDULE = "Расписание работы приюта, адрес, схема проезда";
    public static final String KEYBOARD_ONE_ACCIDENT_PREVENTION = "Рекомендации о технике безопасности";


    //название кнопок из меню 2, которое выпадает по нажатию кнопки "Приютить собаку" из главного меню
    public static final String KEYBOARD_TWO_DATING_DOG = "Правила знакомства с собакой";
    public static final String KEYBOARD_TWO_DOCUMENTS_ADOPT_DOG = "Список документов, необходимых для того, чтобы взять собаку из приюта";
    public static final String KEYBOARD_TWO_TRANSPORTATION_DOG = "Список рекомендаций по транспортировке животного";
    public static final String KEYBOARD_TWO_SMALL_DOG = "Список рекомендаций по обустройству дома для щенка";
    public static final String KEYBOARD_TWO_BIG_DOG = "Список рекомендаций по обустройству дома для взрослой собаки";
    public static final String KEYBOARD_TWO_INVALID_DOG = "Список рекомендаций по обустройству дома для собаки с ограниченными возможностями (зрение, передвижение)";
    public static final String KEYBOARD_TWO_CYNOLOGIST_ADVICE = "Советы кинолога по первичному общению с собакой";
    public static final String KEYBOARD_TWO_GOOD_CYNOLOGIST = "Проверенные кинологи для дальнейшего обращения к ним";
    public static final String KEYBOARD_TWO_NOT_DOG = "Список причин, почему могут отказать и не дать забрать собаку из приюта";


}
