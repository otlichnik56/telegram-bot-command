package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pro.sky.telegrambot.entity.Person;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.exception.TelegramBotExceptionAPI;
import pro.sky.telegrambot.model.Shelter;
import pro.sky.telegrambot.repository.PersonRepository;
import pro.sky.telegrambot.repository.ReportRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pro.sky.telegrambot.constant.ChatSettings.volunteerChatId;
import static pro.sky.telegrambot.constant.Strings.*;


public class ShelterService {
    private final Shelter shelter;
    private final PersonRepository personRepository;
    private final ReportRepository reportRepository;
    private Logger logger = LoggerFactory.getLogger(ShelterService.class);
    @Autowired
    private TelegramBot telegramBot;

    public ShelterService(Shelter shelter, PersonRepository contactRepository, ReportRepository reportRepository) {
        this.shelter = shelter;
        this.personRepository = contactRepository;
        this.reportRepository = reportRepository;
    }

    /**
     *
     * @param inputMessage
     */
    public void setContactFromChat(Message inputMessage) {
        long chatId = inputMessage.chat().id();
        String text = inputMessage.text() + " @" + inputMessage.from().username();
        addContact(chatId, text);
    }

    /**
     *
     * @param chatId
     * @param inputText
     */
    public void addContact(long chatId, String inputText) {
        String parsedPhoneString = "";
        String contactNameAndUserName = "";
        Person newContact;
        try {
            Pattern phonePattern = Pattern.compile("^((8|\\+7)[\\-\\s]?)?\\(?\\d{3}\\)?[\\d\\-\\s]{7,10}");
            Pattern letterPattern = Pattern.compile("[^0-9\\+\\(\\)\\s\\-\\_]");
            Matcher letterMatcher = letterPattern.matcher(inputText);
            Matcher phoneMatcher = phonePattern.matcher(inputText);
            if (phoneMatcher.find()) {
                parsedPhoneString = phoneMatcher.group();
            }
            if (letterMatcher.find()) {
                contactNameAndUserName = inputText.substring(letterMatcher.start(0));
            }
            String contactName = contactNameAndUserName.substring(0, contactNameAndUserName.indexOf("@"));
            String userName = contactNameAndUserName.substring(contactNameAndUserName.indexOf("@"));

            String formattedPhoneString = parsedPhoneString.replaceAll("[\\s\\-\\(\\)]", "");

            if (formattedPhoneString.charAt(0) == '+' && formattedPhoneString.charAt(1) == '7') {
                formattedPhoneString = "8" + formattedPhoneString.substring(2);
            } else if (formattedPhoneString.charAt(0) == '9') {
                formattedPhoneString = "8" + formattedPhoneString;
            } else if (formattedPhoneString.charAt(0) == '7') {
                formattedPhoneString = "7" + formattedPhoneString.substring(1);
            }
            newContact = new Person(userName, formattedPhoneString, contactName, chatId);
        } catch (TelegramBotExceptionAPI e) {
            newContact = null;
            logger.error("Ошибка. Контакт не удалось сохранить");
        }
        if (newContact != null) {
            saveContact(newContact);
        }
    }

    /**
     *
     * @param person
     */
    public void saveContact(Person person) {
        personRepository.save(person);
    }

    /**
     *
     * @param message
     */
    public void setReport(Message message) {
        Report report = new Report();
        report.setUsername(message.chat().username());
        report.setMessage(message.caption());
        report.setDateReport(LocalDate.now());
        PhotoSize photoSize = message.photo()[1];
        GetFile getFile = new GetFile(photoSize.fileId());
        GetFileResponse getFileResponse = telegramBot.execute(getFile);
        try {
            byte[] image = telegramBot.getFileContent(getFileResponse.file());
            report.setPhoto(image);
            reportRepository.save(report);
        } catch (IOException e) {
            logger.error("Ошибка чтения или записи отчёта");
        } finally {
            String text = "Отчёт не сохранён, попытайтесь его отправить заново";
            if (!(report.getMessage() == null) && !(report.getPhoto() == null)) {
                text = "Благодарим за ваш отчёт";
            }
            if (report.getMessage() == null) {
                text = WARNING_MESSAGE + " Текст где?";
                SendMessage reply = new SendMessage(volunteerChatId, "Усыновитель @" + message.chat().username() + " не прислал текст");
                telegramBot.execute(reply);
            }
            if (report.getPhoto() == null) {
                text = WARNING_MESSAGE + " Фото где?";
                SendMessage reply = new SendMessage(volunteerChatId, "Усыновитель @" + message.chat().username() + " не прислал фото");
                telegramBot.execute(reply);
            }
            SendMessage reply = new SendMessage(message.chat().id(), text);
            telegramBot.execute(reply);
            SendPhoto sendPhoto = new SendPhoto(message.chat().id(), reportRepository.findById(1L).get().getPhoto());
            telegramBot.execute(sendPhoto);
        }
    }

    /**
     *
     * @param inputMessage
     */
    public void getRequest(Message inputMessage) {
        String nickName = inputMessage.from().username();
        String requestText = inputMessage.text();
        SendMessage messageVolunteer = new SendMessage(inputMessage.chat().id(), MESSAGE_FOR_VOLUNTEER + "\n " + "@" + nickName + "\n" + requestText);
        telegramBot.execute(messageVolunteer);
        SendMessage replyMessage = new SendMessage(inputMessage.chat().id(), THANKS_FOR_REQUEST);
        telegramBot.execute(replyMessage);
    }

    /**
     *
     * @return
     */
    public Shelter getShelter() {
        return shelter;
    }

    /**
     *
     * @return
     */
    public String getAbout() {
        return shelter.getAbout();
    }

    /**
     *
     * @return
     */
    public String getScheduleAndAddress() {
        return shelter.getScheduleAndAddress();
    }

    /**
     *
     * @return
     */
    public String getSafetyPrecautions() {
        return shelter.getSafetyPrecautions();
    }

    /**
     *
     * @return
     */
    public String getDocumentsForAdoption() {
        return shelter.getDocumentsForAdoption();
    }

    /**
     *
     * @return
     */
    public String getDeclineReasons() {
        return shelter.getDeclineReasons();
    }

    /**
     *
     * @return
     */
    public String getMeetingRules() {
        return shelter.getMeetingRules();
    }

    /**
     *
     */
    public void updateInfo() {
        shelter.updateInfoAboutShelter();
    }

    /**
     *
     * @return
     */
    public String getTransportationRecommendations() {
        return shelter.getTransportationRecommendations();
    }

    /**
     *
     * @return
     */
    public String getHomeImprovementsForAdultsRecommendations() {
        return shelter.getHomeImprovementsForAdultsRecommendations();
    }

    /**
     *
     * @return
     */
    public String getHomeImprovementsForPuppiesRecommendations() {
        return shelter.getHomeImprovementsForPuppiesRecommendations();
    }

    /**
     *
     * @return
     */
    public String getHomeImprovementsForDisabledRecommendations() {
        return shelter.getHomeImprovementsForDisabledRecommendations();
    }

    /**
     *
     * @param message
     */
    public void deleteContact(String message) {
        try {
            personRepository.deleteById(Long.valueOf(message));
        } catch (RuntimeException e) {
            logger.error("Ошибка. Удаление не возможно, запись не найдена");
        }
    }

    /**
     *
     * @param id
     */
    public void appointGuardian(String id) {
        try {
            Person guardian = personRepository.findById(Long.valueOf(id)).get();
            guardian.setAdoptive(true);
            guardian.setStartProbationDate(LocalDate.now());
            LocalDate endDate = LocalDate.now().plusDays(30);
            guardian.setEndProbationDate(endDate);
            personRepository.save(guardian);
        }catch (TelegramBotExceptionAPI e){
            logger.error("Ошибка. Изменение сущности не возможно");
        }
    }

    /**
     *
     * @param message
     */
    public void extendProbation(String message) {
        try {
            String idString= message.substring(0, message.indexOf(" "));
            Long id = Long.valueOf(idString);
            String daysAdd = message.substring(message.indexOf(" ") + 1);
            int days = Integer.parseInt(daysAdd);
            Person guardian = personRepository.getById(id);
            LocalDate endDate = guardian.getEndProbationDate();
            guardian.setEndProbationDate(endDate.plusDays(days));
            personRepository.save(guardian);
            notificationExtendProbation(guardian.getChatId(), days);
        }catch (TelegramBotExceptionAPI e){
            logger.error("Ошибка. Изменение не сохранено");
        }
    }

    /**
     *
     * @param chatID
     * @param days
     */
    public void notificationExtendProbation(Long chatID, int days) {
        SendMessage message = new SendMessage(chatID, "Ваш испытательный срок увеличен на " + days + " дней");
        telegramBot.execute(message);
    }

    /**
     *
     * @return
     */
    public String printContactsList() {
        return personRepository.findAll().stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }

}
