package pro.sky.telegrambot.constants;

public class Constants {


    // заготовленные фразы, которые пишет бот. например, приветствие
    public static final String WELCOME_MESSAGE_MAIN = "Добро пожаловать в наш приют для собак. \nВы можете воспользоваться одним из пунктов гланого меню ниже ";
    public static final String WELCOME_MESSAGE_ONE = "Ещё раз здравствуйте. Вы можете узнать следующую инфу о нашем приюте ";
    public static final String WELCOME_MESSAGE_TWO = "Готов предоставить вам следующую полезную инфу ";
    public static final String WELCOME_MESSAGE_THREE = "Рад вам помочь, я готов принять ваш отчёт. В ежедневный отчет входит следующая информация: \n" +
            "- Фото животного.\n" + "- Рацион животного.\n" + "- Общее самочувствие и привыкание к новому месту.\n" +
            "- Изменение в поведении: отказ от старых привычек, приобретение новых.";
    public static final String WELCOME_MESSAGE_FOUR = "Спасибо за обращение, волонтёр скоро с вами свяжется ";
    public static final String MESSAGE_FOR_VOLUNTEER = "По братски прошу, всязитесь с  ";
    public static final String RECORD_CONTACT = "Будьте добры по-медлене, я записываю. Введите свой номер телефона и как к вам обращаться ";
    public static final String SORRY_MESSAGE = "Извинините, я вас не понимаю ";


    //название кнопок из главного меню + прислать отчёт и позвать волонтёра и оставить контактные данные
    //public static final String KEYBOARD_MAIM_SHELTER_INFORMATION = "Узнать информацию о приюте";
    public static final String TO_INFO_ABOUT_SHELTER = "Информация о приюте";
    //public static final String KEYBOARD_MAIM_ADOPT_DOG = "Как взять собаку из приюта";
    public static final String TO_ADOPT_DOG = "Взять собаку";
    //public static final String KEYBOARD_MAIM_SUBMIT_REPORT = "Прислать отчет о питомце";
    public static final String KEYBOARD_MAIM_SUBMIT_REPORT = "Отправить отчет";
    public static final String CALL_VOLUNTEER = "Позвать волонтера";
    public static final String KEYBOARD_CONTACT = "Оставить контактные данные для связи";
    public static final String TO_MAIN_MENU = "Вернуться в главное меню";



    //название кнопок из меню 1, которое выпадает по нажатию кнопки "Информация о приюте" из главного меню
    public static final String ABOUT_SHELTER_INFO = "О приюте";
    public static final String ABOUT_SHELTER_ADDRESS_SCHEDULE = "Расписание и Адрес";
    public static final String ABOUT_SHELTER_SAFETYPRECUATUINS = "Техника безопасности";


    public static final String SEND_CONTACTS = "Оставить контактные данные для связи";




    //название кнопок из меню 2, которое выпадает по нажатию кнопки "Приютить собаку" из главного меню
    public static final String ADOPT_DOG_RULES = "Правила знакомства с собакой";
    public static final String ADOPT_DOG_DOCUMENTS = "Необходимые документы";
    public static final String ADOPT_DOG_RECOMENDATIONS = "Список рекомендаций по транспортировке животного";
    public static final String KEYBOARD_TWO_SMALL_DOG = "Список рекомендаций по обустройству дома для щенка";
    public static final String KEYBOARD_TWO_BIG_DOG = "Список рекомендаций по обустройству дома для взрослой собаки";
    public static final String KEYBOARD_TWO_INVALID_DOG = "Список рекомендаций по обустройству дома для собаки с ограниченными возможностями (зрение, передвижение)";
    public static final String ADOPT_DOG_CYNOLOGYSTS_ADVICES = "Советы кинолога по первичному общению с собакой";
    public static final String ADOPT_DOG_APPROVED_CYNOLOGYSTS = "Проверенные кинологи";
    public static final String ADOPT_DOG_DECLINE_REASONS = "Причины отказа";

    public static final String START = "/start";

    public static final String greetingsFileName = "/greetings.txt";
    public static final String descriptionFileName = "/shelterDescription.txt";
    public static final String scheduleFileName = "/scheduleAndAddress.txt";
    public static final String documentsForAdoptionFileName = "/documentsForAdoption.txt";
    public static final String declineReasonsFileName = "/declineReasons.txt";
    public static final String safetyPrecuationsFileName = "/safetyPrecuations.txt";

}
