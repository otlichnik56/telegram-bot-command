package pro.sky.telegrambot.constants;

public class Constants {


    // заготовленные фразы, которые пишет бот. например, приветствие
    public static final String WELCOME_MESSAGE_MENU_MAIN = "Добро пожаловать в наш приют для собак. \nВы можете воспользоваться одним из пунктов гланого меню ниже ";
    public static final String WELCOME_MESSAGE_MENU_ABOUT_SHELTER = "Ещё раз здравствуйте. Вы можете узнать следующую инфу о нашем приюте ";
    public static final String WELCOME_MESSAGE_MENU_ADOPT_DOG = "Готов предоставить вам следующую полезную инфу ";
    public static final String WELCOME_MESSAGE_THREE = "Рад вам помочь, я готов принять ваш отчёт. В ежедневный отчет входит следующая информация: \n" +
            "- Фото животного.\n" + "- Рацион животного.\n" + "- Общее самочувствие и привыкание к новому месту.\n" +
            "- Изменение в поведении: отказ от старых привычек, приобретение новых.";
    public static final String WELCOME_MESSAGE_FOUR = "Опишите пожалуйста свою проблему";
    public static final String MESSAGE_FOR_VOLUNTEER = "По братски прошу, свяжитесь с  ";
    public static final String RECORD_CONTACT = "Оставьте пожалуйста свой телефон и как к Вам обращаться";
    public static final String SORRY_MESSAGE = "Извинините, я вас не понимаю ";

    public static final String THANKS_FOR_REQUEST = "Спасибо за обращение, Вам скоро ответит первый свободный волонтер";



    //название кнопок из главного меню + прислать отчёт и позвать волонтёра и оставить контактные данные
    //public static final String KEYBOARD_MAIM_SHELTER_INFORMATION = "Узнать информацию о приюте";
    public static final String TO_INFO_ABOUT_SHELTER = "Информация о приюте";
    //public static final String KEYBOARD_MAIM_ADOPT_DOG = "Как взять собаку из приюта";
    public static final String TO_ADOPT_DOG = "Взять собаку";
    //public static final String KEYBOARD_MAIM_SUBMIT_REPORT = "Прислать отчет о питомце";
    public static final String KEYBOARD_MAIM_SUBMIT_REPORT = "Отправить отчет";
    public static final String CALL_VOLUNTEER = "Позвать волонтера";
    public static final String KEYBOARD_CONTACT = "Оставить контактные данные для связи";
    public static final String TO_MAIN_MENU = "Главное меню";


    //название кнопок из меню 1, которое выпадает по нажатию кнопки "Информация о приюте" из главного меню
    public static final String ABOUT_SHELTER_INFO = "О приюте";
    public static final String ABOUT_SHELTER_ADDRESS_SCHEDULE = "Расписание и Адрес";
    public static final String ABOUT_SHELTER_SAFETY_PRECUATUINS = "Техника безопасности";


    public static final String SEND_CONTACTS = "Оставить контактные данные";


    //название кнопок из меню 2, которое выпадает по нажатию кнопки "Приютить собаку" из главного меню
    public static final String ADOPT_DOG_MEETING_RULES = "Правила знакомства";
    public static final String ADOPT_DOG_DOCUMENTS = "Необходимые документы";
    public static final String ADOPT_DOG_RECOMENDATIONS = "Списки рекомендаций";
    public static final String KEYBOARD_TWO_SMALL_DOG = "Список рекомендаций по обустройству дома для щенка";
    public static final String KEYBOARD_TWO_BIG_DOG = "Список рекомендаций по обустройству дома для взрослой собаки";
    public static final String KEYBOARD_TWO_INVALID_DOG = "Список рекомендаций по обустройству дома для собаки с ограниченными возможностями (зрение, передвижение)";
    public static final String RECOMMENDATIONS_CYNOLOGYSTS_ADVICES = "Советы кинолога";
    public static final String ADOPT_DOG_APPROVED_CYNOLOGYSTS = "Проверенные кинологи";
    public static final String ADOPT_DOG_DECLINE_REASONS = "Причины отказа";

    public static final String START = "/start";

    public static final String greetingsFileName = "/greetings.txt";
    public static final String descriptionFileName = "/shelterDescription.txt";
    public static final String scheduleFileName = "/scheduleAndAddress.txt";
    public static final String documentsForAdoptionFileName = "/documentsForAdoption.txt";
    public static final String declineReasonsFileName = "/declineReasons.txt";
    public static final String safetyPrecuationsFileName = "/safetyPrecuations.txt";
    public static final String RECOMMENDATIONS_TRANSPORTATION = "Рекомендации по транспортировке";
    public static final String RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_PUPPIES = "Обустройство дома для щенков";
    public static final String RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_ADUALTS = "Обустройство дома для взрослой собаки";
    public static final String RECOMMENDATIONS_HOME_IMPROVEMENT_FOR_DISABLED = "Обустройство дома для собаки с ограничениями";
    public static final String RECOMMENDATIONS_MENU_GREETINGS = "Выберите список рекоммендаций для просмотра";

    public static String meetingRulesFileName = "/meetingRules.txt";
    public static String approvedCynologystsFileName="/approvedCynologysts.txt";
    public static String cynologystsAdvicesFileName = "/cynologystsAdvices.txt";
    public static String transportationRecommendationsFileName = "/transportationRecommendations.txt";
    public static String homeImprovementsForPuppiesFileName = "/homeImprovementsForPuppies.txt";
    public static String homeImprovementsForDisabledFileName = "/homeImprovementsForDisabled.txt";
    public static String homeImprovementsForAdultsFileName = "/homeImprovementsForAdults.txt";
}
