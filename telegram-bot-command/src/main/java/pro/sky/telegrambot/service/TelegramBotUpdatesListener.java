package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.UpdatesListener;


public abstract class TelegramBotUpdatesListener implements UpdatesListener {
    /*

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final static Keyboard replyMainKeyboards = new ReplyKeyboards().generateMainMenuKeyboard();
    private final static Keyboard replyOneKeyboards = new ReplyKeyboards().generateAboutShelterMenuKeyboard();
    private final static Keyboard replyTwoKeyboards = new ReplyKeyboards().generateGetDogMenuKeyboard();
    private final static Keyboard inlineMainKeyboards = new InlineKeyboards().generateMainKeyboard();
    private final static Keyboard inlineOneKeyboards = new InlineKeyboards().generateOneKeyboard();
    private final static Keyboard inlineTwoKeyboards = new InlineKeyboards().generateTwoKeyboard();
    private Boolean recordStatus = false;
    private final static Long volunteerChatId = -1001816802535L;

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            messageProcessing(update);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void messageProcessing(Update update) {
        Long chatId = getChatId(update);
        if (recordStatus) {
            if (!(update.message() == null)) {
                String text = "Ребят, новый контакт, запишите  " + update.message().text() + " " + update.message().chat().firstName() + " " + update.message().chat().lastName();
                SendMessage message = new SendMessage(volunteerChatId, text);
                telegramBot.execute(message);
                recordStatus = false;
            }
        } else {
            if (!(update.message() == null)) {
                telegramBot.execute(createMessage(chatId, update.message().text()));
                if (!(update.message().text() == null) && update.message().text().equals(CALL_VOLUNTEER)) {
                    SendMessage messageVolunteer = new SendMessage(volunteerChatId, MESSAGE_FOR_VOLUNTEER + " " + "@" + update.message().from().username());
                    telegramBot.execute(messageVolunteer);
                }
            }
        }
    }

    private Long getChatId(Update update) {
        Long chatId = null;
        try {
            if (update.message() == null) {
                if (update.callbackQuery() == null) {
                    chatId = update.myChatMember().chat().id();
                } else {
                    chatId = update.callbackQuery().message().chat().id();
                }
            } else {
                chatId = update.message().chat().id();
            }
        } catch (NullPointerException notInitializedChatId) {
            System.out.println("Сработал блок try catch в методе getChatId() " + chatId);
        }
        return chatId;
    }

    private SendMessage createMessage(Long chatId, String text) {
        SendMessage message;
        System.out.println(text);
        if (text == null) {
            message = new SendMessage(chatId, SORRY_MESSAGE);
            return message;
        }
        ;

        switch (text) {
            // главное меню и общее
            case "null":
            case "/start":
            case "Главное меню":
                message = new SendMessage(chatId, WELCOME_MESSAGE_MAIN);
                message.replyMarkup(replyMainKeyboards);
                break;
            case TO_INFO_ABOUT_SHELTER:
                message = new SendMessage(chatId, WELCOME_MESSAGE_ONE);
                message.replyMarkup(replyOneKeyboards);
                break;
            case TO_ADOPT_DOG:
                message = new SendMessage(chatId, WELCOME_MESSAGE_TWO);
                message.replyMarkup(replyTwoKeyboards);
                break;
            case KEYBOARD_MAIM_SUBMIT_REPORT:
                message = new SendMessage(chatId, WELCOME_MESSAGE_THREE);
                recordStatus = true;
                break;
            case CALL_VOLUNTEER:
                message = new SendMessage(chatId, WELCOME_MESSAGE_FOUR);
                break;
            case SEND_CONTACTS:
                message = new SendMessage(chatId, RECORD_CONTACT);
                recordStatus = true;
                break;
            // меню один
            case ABOUT_SHELTER:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_ONE_WORK_SCHEDULE:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_ONE_ACCIDENT_PREVENTION:
                message = new SendMessage(chatId, "заглушка");
                break;
            // меню два
            case KEYBOARD_TWO_DATING_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_DOCUMENTS_ADOPT_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_TRANSPORTATION_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_SMALL_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_BIG_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_INVALID_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_CYNOLOGIST_ADVICE:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_GOOD_CYNOLOGIST:
                message = new SendMessage(chatId, "заглушка");
                break;
            case KEYBOARD_TWO_NOT_DOG:
                message = new SendMessage(chatId, "заглушка");
                break;
            default:
                message = new SendMessage(chatId, SORRY_MESSAGE);
        }
        return message;
    }

    private void saveReport(Update update) {

    }
*/

}
