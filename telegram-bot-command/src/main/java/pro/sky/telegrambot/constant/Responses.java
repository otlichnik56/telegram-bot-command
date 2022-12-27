package pro.sky.telegrambot.constant;
/**
 * Отметки, которыми помечаются пользовательские запросы, чтобы предоставить данные для определенных методов, а не как пункты меню
 * {@link #CONTACT} - запрос от пользователя на добавление пользователя через телеграмм бот
 * {@link #REPORT} - запрос на отправку отчета
 * {@link #REQUEST} - запрос в службу волонтеров для помощи
 */
public enum Responses {
    CONTACT,
    REPORT,
    REQUEST
}
