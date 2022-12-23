package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entitydatabase.Identity;
import pro.sky.telegrambot.repositoty.IdentityRepository;
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
    private final IdentityRepository identityRepository;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TelegramBotScheduler(TelegramBot telegramBot, PersonRepository personRepository, IdentityRepository identityRepository) {
        this.telegramBot = telegramBot;
        this.personRepository = personRepository;
        this.identityRepository = identityRepository;
    }


    /** Основной метод.Запускается раз в сутки в 09.00.
     * Формирует поочередно собщения для прошедших испытание, не прошедших и тем кому продлили срок испытания.
     * Также посылает волонтерам список лиц, у которых истекает испытательный срок сегодня.
     * Отчищает таблицу identity и заполняет данными полученными сегодня из таблицы person,
     * для их использования завтра.
     */
    @Scheduled(cron = "0 00 09 ? * *")
    public void run() {
        logger.info("Processing scheduled");
        sendingMessagesIdentity(getListUserComplete(), COMPLETE_MESSAGE);
        sendingMessagesIdentity(getListUserFail(), FAIL_MESSAGE);
        sendingMessagesIdentity(getListUserAdditionalTest(), ADDITIONAL_TEST_MESSAGE);
        sendingMessagesVolunteer(getUsernameEndDateNow());
        identityRepository.deleteAll();
        identityRepository.saveAll(getUsernameEndDateNow());
    }



    /** Отправка сообщения в чат волонтёров со списком усыновителей (только username), у которых заканчивается срок испытания,
     * для дольнейшего принятия решения волонтером.
     * @param identityList List<Identity>
     */
    private void sendingMessagesVolunteer(List<Identity> identityList) {
        if (!(identityList == null)) {
            StringBuilder listUsername = null;
            for (Identity identity: identityList){
                listUsername.append("@").append(identity.getUsername()).append(" ");
            }
            if (!(listUsername == null)){
                SendMessage message = new SendMessage(volunteerChatId, END_DATE_FAR_VOLUNTEER_MESSAGE + listUsername);
                telegramBot.execute(message);
            }
        }
    }

    /** Отправка сообщений всем усыновителям (прошедших, не прошедших испытание и тем кому продлили срок), при наличии таковых.
     * @param identityList List<Identity>
     * @param messageText String
     */
    private void sendingMessagesIdentity(List<Identity> identityList, String messageText) {
        if (!(identityList == null)){
            for (Identity identity: identityList){
                SendMessage message = new SendMessage(identity.getChatId(), messageText);
                telegramBot.execute(message);
            }
        }
    }

    /** Получение списка, прошедших испытание.
     * @return List<Identity>
     */
    private List<Identity> getListUserComplete() {
        return getUsernameEndDateYesterday().stream().filter(Identity::getConditionTest).collect(Collectors.toList());
    }

    /** Получение списка, не прошедших испытание.
     * @return List<Identity>
     */
    private List<Identity> getListUserFail() {
        return getUsernameEndDateYesterday().stream().filter(identity -> !identity.getConditionTest()).collect(Collectors.toList());
    }

    /** Возвращает записи, которые получены путём вычитания списка, полученного сегодня, из списка, полученного вчера.
     * Т.е. те кому продлили испытательный срок.
     * @return List<Identity>
     */
    private List<Identity> getListUserAdditionalTest() {
        List<Identity> listNow = identityRepository.findAll();
        List<Identity> listYesterday = getUsernameEndDateYesterday();
        listNow.removeAll(listYesterday);
        return listNow;
    }

    /** Возвращает записи из тиблицы person с параметром поля endDate сегодня.
     * Возвращаемые сущности Identity.
     * @return List<Identity>
     */
    private List<Identity> getUsernameEndDateNow() {
        LocalDate localDate = LocalDate.now();
        return personRepository.getUsernameEndDate(localDate);
    }

    /** Возвращает все записи из тиблицы identity.
     * @return List<Identity>
     */
    private List<Identity> getUsernameEndDateYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        LocalDate localDate = LocalDate.parse(dateFormat.format(calendar.getTime()));
        return personRepository.getUsernameEndDate(localDate);
    }

}
