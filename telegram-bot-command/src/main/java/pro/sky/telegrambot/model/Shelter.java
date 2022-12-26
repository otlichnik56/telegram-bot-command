package pro.sky.telegrambot.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.TelegramBotApplication;
import pro.sky.telegrambot.constants.FileNames;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Класс для загрузки и хранения строк из текстовых файлов, которые не будут изменяться часто - адреса, рекомендации, описания.
 */
@Component
public class Shelter {


    //  @Value("${greetingsFileName}")
    private String greetingsFileName = FileNames.GREETINGS;
    //@Value("${descriptionFileName}")
    private String descriptionFileName = FileNames.DESCRIPTION;
    //    @Value("${scheduleFileName}")
    private String scheduleAndAddressFileName = FileNames.SCHEDULE_AND_ADDRESS;

    //    @Value("${documentsForAdoptionFileName}")
    private String documentsForAdoptionFileName = FileNames.DOCUMENTS_FOR_ADOPTION;
    //    @Value("${safetyPrecuationsFileName")
    private String safetyPrecuationsFileName = FileNames.SAFETY_PRECUATIONS;
    //    @Value("${declineReasonsFileName}")
    private String declineReasonsFileName = FileNames.DECLINE_REASONS;
    private String meetingRulesFileName = FileNames.MEETING_RULES;
    private String approvedCynologystsFileName = FileNames.APPROVED_CYNOLOGYSTS;
    private String cynologystsAdvicesFileName = FileNames.CYNOLOGYSTS_ADVICES;

    private String transportationRecommendationsFileName = FileNames.TRANSPORTATION_RECOMMENDATIONS;
    private String homeImprovementsForPuppiesFileName = FileNames.HOME_IMPROVEMENTS_FOR_PUPPIES;
    private String homeImprovementsForDisabledFileName = FileNames.HOME_IMPROVEMENTS_FOR_DISABLED;
    private String homeImprovementsForAdultsFileName = FileNames.HOME_IMPROVEMENTS_FOR_ADULTS;



    private String greetings;
    private String description;
    private String scheduleAndAddress;
    private String documentsForAdoption;
    private String declineReasons;
    private String meetingRules;
    private String approvedCynologysts;
    private String cynologystsAdvices;
    private String transportationRecommendations;
    private String homeImprovementsForPuppies;
    private String homeImprovementsForDisabled;
    private String homeImprovementsForAdults;

   private String safetyPrecuations;

    private Logger logger = LoggerFactory.getLogger(Shelter.class);


    /**
     * При создании бина все строковые поля инициализуруются из файлов одним общим методом {@link #updateInfoAboutShelter()}
     */
    public Shelter() {
        updateInfoAboutShelter();
    }

    /**
     * инициализация строковых полей из соответсвующих файлов методом {@link #readStringsFromFile(String)}
     */
    public void updateInfoAboutShelter() {
        greetings = readStringsFromFile(greetingsFileName);
        description = readStringsFromFile(descriptionFileName);
        scheduleAndAddress = readStringsFromFile(scheduleAndAddressFileName);
        documentsForAdoption = readStringsFromFile(documentsForAdoptionFileName);
        declineReasons = readStringsFromFile(declineReasonsFileName);
        safetyPrecuations = readStringsFromFile(safetyPrecuationsFileName);
        meetingRules = readStringsFromFile(meetingRulesFileName);
        approvedCynologysts = readStringsFromFile(approvedCynologystsFileName);
        cynologystsAdvices = readStringsFromFile(cynologystsAdvicesFileName);
        transportationRecommendations = readStringsFromFile(transportationRecommendationsFileName);
        homeImprovementsForAdults = readStringsFromFile(homeImprovementsForAdultsFileName);
        homeImprovementsForPuppies = readStringsFromFile(homeImprovementsForPuppiesFileName);
        homeImprovementsForDisabled = readStringsFromFile(homeImprovementsForDisabledFileName);

    }
    /**
    Метод чтения из файла содержимого в виде массива строк. Затем строки склеиваются в одну чтобы передать ее в {@link com.pengrad.telegrambot.request.SendMessage#SendMessage(Object, String)}
     */
    private String readStringsFromFile(String fileName) {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(Objects.requireNonNull(TelegramBotApplication.class.getResource(fileName).toURI()))));
        } catch (IOException e) {

        } catch (URISyntaxException e) {

        }

        return "Не могу считать информацию";
    }


    public String getAbout() {
        return description;
    }
    public String getMeetingRules() {
        return meetingRules;
    }

    public String getScheduleAndAdress() {
        return scheduleAndAddress;
    }

    public String getSafetyPrecuations() {
        return safetyPrecuations;
    }

    public String getDocumentsForAdoption() {
        return documentsForAdoption;
    }

    public String getDeclineReasons() {
        return declineReasons;
    }

    public String getApprovedCunologysts() {
        return approvedCynologysts;

    }

    public String getCynologystsAdvices() {
        return cynologystsAdvices;
    }

    public String getTransportationRecommendations() {
        return transportationRecommendations;
    }

    public String getHomeImprovementsForAdultsRecommendations() {
        return homeImprovementsForAdults;
    }

    public String getHomeImprovementsForPuppiesRecommendations() {
        return homeImprovementsForPuppies;
    }

    public String getHomeImprovementsForDisabledRecommendations() {
        return homeImprovementsForDisabled;
    }
}
