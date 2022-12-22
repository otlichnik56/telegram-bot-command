package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entitydatabase.Person;
import pro.sky.telegrambot.repositoty.PersonRepository;
import pro.sky.telegrambot.repositoty.ReportRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @Scheduled(cron = "0 00 09 ? * *")
    public void run() {
        logger.info("Processing scheduled");
        sendingMessagesPerson(getListUserComplete(), COMPLETE_MESSAGE);
        sendingMessagesPerson(getListUserFail(), FAIL_MESSAGE);
        sendingMessagesPerson(getListUserAdditionalTest(), ADDITIONAL_TEST_MESSAGE);
        sendingMessagesVolunteer(getUsernameEndDateNow());
    }

    private void sendingMessagesVolunteer(List<Person> personList) {
        if (!(personList == null)) {
            StringBuilder listUsername = null;
            for (Person person: personList){
                listUsername.append(person.getUsername()).append(" ");
            }
            if (!(listUsername == null)){
                SendMessage message = new SendMessage(volunteerChatId, END_DATE_FAR_VOLUNTEER_MESSAGE + listUsername);
                telegramBot.execute(message);
            }
        }
    }

    private void sendingMessagesPerson(List<Person> personList, String messageText) {
        if (!(personList == null)){
            for (Person person: personList){
                SendMessage message = new SendMessage(person.getChatId(), messageText);
                telegramBot.execute(message);
            }
        }
    }

    private List<Person> getListUserComplete() {
        return getUsernameEndDateYesterday().stream().filter(Person::getConditionTest).collect(Collectors.toList());
    }

    private List<Person> getListUserFail() {
        return getUsernameEndDateYesterday().stream().filter(person -> !person.getConditionTest()).collect(Collectors.toList());
    }

    private List<Person> getListUserAdditionalTest() {
        List<Person> listNow = getUsernameEndDateNow();
        List<Person> listYesterday = getUsernameEndDateYesterday();
        listYesterday.removeAll(listNow);
        return listYesterday;
    }

    private List<Person> getUsernameEndDateNow() {
        LocalDate localDate = LocalDate.now();
        return personRepository.getUsernameEndDate(localDate);
    }

    private List<Person> getUsernameEndDateYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        LocalDate localDate = LocalDate.parse(dateFormat.format(calendar.getTime()));
        return personRepository.getUsernameEndDate(localDate);
    }

}
