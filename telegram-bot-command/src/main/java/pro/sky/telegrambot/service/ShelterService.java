package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entitydatabase.Person;
import pro.sky.telegrambot.entitydatabase.Report;
import pro.sky.telegrambot.model.Shelter;
import pro.sky.telegrambot.repositoty.PersonRepository;
import pro.sky.telegrambot.repositoty.ReportRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void getContact(Message inputMessage) {
        String parsedPhoneString = "";
        String contactName = "";
        String inputText = inputMessage.text();

        Pattern phonePattern = Pattern.compile("^((8|\\+7)[\\-\\s]?)?\\(?\\d{3}\\)?[\\d\\-\\s]{7,10}");
        Pattern letterPattern = Pattern.compile("[^0-9\\+\\(\\)\\s\\-\\_]");

        Matcher letterMatcher = letterPattern.matcher(inputText);
        Matcher phoneMatcher = phonePattern.matcher(inputText);
        if (phoneMatcher.find()) {
            parsedPhoneString = phoneMatcher.group();
        }
        if (letterMatcher.find()) {
            contactName = inputText.substring(letterMatcher.start(0));
        }
        String formattedPhoneString = parsedPhoneString.replaceAll("[\\s\\-\\(\\)]", "");
        if (formattedPhoneString.charAt(0) == '+' && formattedPhoneString.charAt(1) == '7') {
            formattedPhoneString = "8" + formattedPhoneString.substring(2);
        } else if (formattedPhoneString.charAt(0) == '9') {
            formattedPhoneString = "8" + formattedPhoneString;
        }

        Person newContact = new Person(inputMessage.from().username(), formattedPhoneString, contactName);

        saveContact(newContact);
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
        personRepository.deleteById(Long.valueOf(message));
    }

    public void updateContact(String message) {
     //   personRepository.updatePersonFromDataBase(LocalDate.parse(data[1]), Long.valueOf(data[0]));
    }

    public void addContact(String message) {
        Person person = new Person();
//        person.setUsername(data[0]);
//        person.setNumberPhone(data[1]);
//        person.setFirstName(data[2]);
//        person.setLastName(data[3]);
        person.setStatus(false);
        person.setStartDate(null);
        person.setEndDate(null);
        System.out.println(person);
        personRepository.save(person);
    }

    public void extendProbation(String message) {

        //personRepository.updatePersonDateFromDataBase(LocalDate.parse(data[2]), LocalDate.parse(data[1]), Long.valueOf(data[0]));
    }

    public void printContactsList() {
//        List<Person> people = personRepository.getPersonFromDataBase(status);
//        text = people.toString();
    }
}
