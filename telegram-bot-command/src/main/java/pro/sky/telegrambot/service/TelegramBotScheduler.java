package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entitydatabase.Person;
import pro.sky.telegrambot.repositoty.PersonRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static pro.sky.telegrambot.constants.Strings.*;
import static pro.sky.telegrambot.constants.ChatSettings.*;

@Service
public class TelegramBotScheduler {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotScheduler.class);
    private final TelegramBot telegramBot;
    private final PersonRepository personRepository;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TelegramBotScheduler(TelegramBot telegramBot, PersonRepository personRepository) {
        this.telegramBot = telegramBot;
        this.personRepository = personRepository;
    }


    /** Основной метод.Запускается раз в сутки в 09.00.
     * Формирует поочередно собщения для прошедших испытание, не прошедших и тем кому продлили срок испытания.
     * Также посылает волонтерам список лиц, у которых истекает испытательный срок сегодня.
     */
    @Scheduled(cron = "0 0/1 * ? * *")
    public void run() {
        logger.info("Processing scheduled");
        sendingMessagesPerson(getListUserComplete(), COMPLETE_MESSAGE);
        sendingMessagesVolunteer(getUsernameEndDateNow());
    }



    /** Отправка сообщения в чат волонтёров со списком усыновителей (только username), у которых заканчивается срок испытания,
     * для дольнейшего принятия решения волонтером.
     * @param personList List<Person>
     */
    private void sendingMessagesVolunteer(List<Person> personList) {
        if (!(personList == null)) {
            StringBuilder listUsername = new StringBuilder();
            for (Person person: personList){
                listUsername.append("@").append(person.getUsername()).append(" ");
            }
            if (!(listUsername == null)){
                SendMessage message = new SendMessage(volunteerChatId, END_DATE_FAR_VOLUNTEER_MESSAGE + listUsername);
                telegramBot.execute(message);
            }
        }
    }

    /** Отправка сообщений всем усыновителям (прошедших, не прошедших испытание), при наличии таковых.
     * @param personList List<Person>
     * @param messageText String
     */
    private void sendingMessagesPerson(List<Person> personList, String messageText) {
        if (!(personList == null)){
            for (Person person: personList){
                SendMessage message = new SendMessage(person.getChatId(), messageText);
                telegramBot.execute(message);
            }
        }
    }

    /** Получение списка, прошедших испытание.
     * @return List<Person>
     */
    private List<Person> getListUserComplete() {
        return personRepository.getUsernameCompleted();
    }

    /** Возвращает записи из тиблицы person с параметром поля endDate сегодня.
     * Возвращаемые сущности Person.
     * @return List<Person>
     */
    private List<Person> getUsernameEndDateNow() {
        LocalDate localDate = LocalDate.now();
        return personRepository.getUsernameEndDate(localDate);
    }

    /** Возвращает все записи из тиблицы person с параметром поля endDate вчера.
     * Возвращаемые сущности Person.
     * @return List<Person>
     */
    private List<Person> getUsernameEndDateYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        LocalDate localDate = LocalDate.parse(dateFormat.format(calendar.getTime()));
        return personRepository.getUsernameEndDate(localDate);
    }

}