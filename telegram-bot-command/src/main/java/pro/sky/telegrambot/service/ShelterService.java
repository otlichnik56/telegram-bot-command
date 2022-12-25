package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entitydatabase.Person;
import pro.sky.telegrambot.entitydatabase.Report;
import pro.sky.telegrambot.model.Shelter;
import pro.sky.telegrambot.repositoty.PersonRepository;
import pro.sky.telegrambot.repositoty.ReportRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pro.sky.telegrambot.constants.Strings.MESSAGE_FOR_VOLUNTEER;
import static pro.sky.telegrambot.constants.Strings.THANKS_FOR_REQUEST;

@Service
public class ShelterService {
    private final Shelter shelter;
    private final PersonRepository personRepository;
    private final ReportRepository reportRepository;
    @Autowired
    private TelegramBot telegramBot;

    public ShelterService(Shelter shelter, PersonRepository contactRepository, ReportRepository reportRepository) {

        this.shelter = shelter;
        this.personRepository = contactRepository;
        this.reportRepository = reportRepository;
    }

    public void getContactFromChat(Message inputMessage) {
        long chatId = inputMessage.chat().id();
        String text = inputMessage.text() + " @" + inputMessage.from().username();
        addContact(chatId, text);

    }

    public void saveContact(Person person) {
        personRepository.save(person);
    }

    public void getReport(Message message) {

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
            System.out.println("Ошибка чтения или записи отчёта");
        } finally {
            // Переводим в состояние чтения, чтобы перестать записывать

            SendMessage reply = new SendMessage(message.chat().id(), "Благодарим за ваш отчёт");
            telegramBot.execute(reply);
            SendPhoto sendPhoto = new SendPhoto(message.chat().id(), reportRepository.findById(1L).get().getPhoto());
            telegramBot.execute(sendPhoto);
        }
    }

    public void getRequest(Message inputMessage) {
        String nickName = inputMessage.from().username();
        String requestText = inputMessage.text();

        SendMessage messageVolunteer = new SendMessage(inputMessage.chat().id(), MESSAGE_FOR_VOLUNTEER + "\n " + "@" + nickName + "\n" + requestText);
        telegramBot.execute(messageVolunteer);
        SendMessage replyMessage = new SendMessage(inputMessage.chat().id(), THANKS_FOR_REQUEST);

        telegramBot.execute(replyMessage);

    }


    public String getAbout() {
        return shelter.getAbout();
    }

    public String getScheduleAndAdress() {
        return shelter.getScheduleAndAdress();
    }

    public String getSafetyPrecautions() {
        return shelter.getSafetyPrecuations();
    }

    public String getDocumentsForAdpotion() {
        return shelter.getDocumentsForAdoption();
    }

    public String getDeclineReasons() {
        return shelter.getDeclineReasons();
    }

    public String getmeetingRules() {
        return shelter.getMeetingRules();
    }

    public void updateInfo() {
        shelter.updateInfoAboutShelter();
    }


    public String getApprovedCynologysts() {
        return shelter.getApprovedCunologysts();
    }

    public String getCynologystsAdvices() {
        return shelter.getCynologystsAdvices();
    }

    public String getTransportationRecommendations() {
        return shelter.getTransportationRecommendations();
    }

    public String getHomeImprovementsForAdultsRecommendations() {
        return shelter.getHomeImprovementsForAdultsRecommendations();
    }

    public String getHomeImprovementsForPuppiesRecommendations() {
        return shelter.getHomeImprovementsForPuppiesRecommendations();
    }

    public String getHomeImprovementsForDisabledRecommendations() {
        return shelter.getHomeImprovementsForDisabledRecommendations();
    }

    public void deleteContact(String message) {
        try {
            personRepository.deleteById(Long.valueOf(message));
        } catch (RuntimeException e) {

        }
    }

    public void appointGuardian(String id) {
        //@Query(value = "UPDATE person SET start_date = CURRENT_DATE, end_date = (CURRENT_DATE + integer '30'), is_adoptive = true WHERE id = :id", nativeQuery = true)
        //void setStatusAndStartDateById(@Param("id") Long id);
        try {
            Person guardian = personRepository.findById(Long.valueOf(id)).get();
            guardian.setAdoptive(true);
            guardian.setStartDate(LocalDate.now());
            LocalDate endDate = LocalDate.now().plusDays(30);
            guardian.setEndDate(endDate);
            personRepository.save(guardian);
        }catch (RuntimeException e){

        }


    }

    public void addContact(long chatId, String inputText) {
        //+7999 876 44 22 Петух Иванов @OrangeUp
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


            newContact = new Person(userName, formattedPhoneString, contactName);
        } catch (Exception e) {
            newContact = null;
        }
        if (newContact != null) {
            saveContact(newContact);
        }
    }

    public void extendProbation(String message) {
        try {
            String idString= message.substring(0, message.indexOf(" "));
            Long id = Long.valueOf(idString);
            String daysAdd = message.substring(message.indexOf(" ") + 1);

            int days = Integer.parseInt(daysAdd);
            Person guardian = personRepository.getById(id);
            LocalDate endDate = guardian.getEndDate();

            guardian.setEndDate(endDate.plusDays(days));
            personRepository.save(guardian);
        }catch (RuntimeException e){

        }

    }

    public String printContactsList() {

        return personRepository.findAll().stream().map(Objects::toString).collect(Collectors.joining("\n"));
//        text = people.toString();
    }
}
