package pro.sky.telegrambot.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.sky.telegrambot.TelegramBotApplication;
import pro.sky.telegrambot.constants.FileNames;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Класс для загрузки и хранения строк из текстовых файлов, которые не будут изменяться часто - адреса, рекомендации, описания.
 */

public abstract class Shelter {

    private String greetingsFileName = FileNames.GREETINGS_CAT_SHELTER;
    private String descriptionFileName = FileNames.DESCRIPTION_CAT_SHELTER;
    private String scheduleAndAddressFileName = FileNames.SCHEDULE_AND_ADDRESS_CAT_SHELTER;
    private String documentsForAdoptionFileName = FileNames.DOCUMENTS_FOR_ADOPTION_CAT_SHELTER;
    private String safetyPrecuationsFileName = FileNames.SAFETY_PRECUATIONS_CAT_SHELTER;
    private String declineReasonsFileName = FileNames.DECLINE_REASONS_CAT_SHELTER;
    private String meetingRulesFileName = FileNames.MEETING_RULES_CAT_SHELTER;
    private String transportationRecommendationsFileName = FileNames.TRANSPORTATION_RECOMMENDATIONS_CAT_SHELTER;
    private String homeImprovementsForPuppiesFileName = FileNames.HOME_IMPROVEMENTS_FOR_PUPPIES_CAT_SHELTER;
    private String homeImprovementsForDisabledFileName = FileNames.HOME_IMPROVEMENTS_FOR_DISABLED_CAT_SHELTER;
    private String homeImprovementsForAdultsFileName = FileNames.HOME_IMPROVEMENTS_FOR_ADULTS_CAT_SHELTER;

    private String greetings;
    private String description;
    private String scheduleAndAddress;
    private String documentsForAdoption;
    private String declineReasons;
    private String meetingRules;

    private String transportationRecommendations;
    private String homeImprovementsForPuppies;
    private String homeImprovementsForDisabled;
    private String homeImprovementsForAdults;
    private String safetyPrecuations;

    private Logger logger = LoggerFactory.getLogger(Shelter.class);


    /**
     * При создании бина все строковые поля инициализуруются из файлов одним общим методом {@link #updateInfoAboutShelter()}
     */
    public Shelter(String greetingsFileName,
                   String descriptionFileName,
                   String scheduleAndAddressFileName,
                   String documentsForAdoptionFileName,
                   String safetyPrecuationsFileName,
                   String declineReasonsFileName,
                   String meetingRulesFileName,
                   String transportationRecommendationsFileName,
                   String homeImprovementsForPuppiesFileName,
                   String homeImprovementsForDisabledFileName,
                   String homeImprovementsForAdultsFileName) {
        initializeFileNames(greetingsFileName,
                descriptionFileName,
                scheduleAndAddressFileName,
                documentsForAdoptionFileName,
                safetyPrecuationsFileName,
                declineReasonsFileName,
                meetingRulesFileName,
                transportationRecommendationsFileName,
                homeImprovementsForPuppiesFileName,
                homeImprovementsForDisabledFileName,
                homeImprovementsForAdultsFileName);
        updateInfoAboutShelter();
    }


    protected void initializeFileNames(String greetingsFileName,
                                       String descriptionFileName,
                                       String scheduleAndAddressFileName,
                                       String documentsForAdoptionFileName,
                                       String safetyPrecuationsFileName,
                                       String declineReasonsFileName,
                                       String meetingRulesFileName,
                                       String transportationRecommendationsFileName,
                                       String homeImprovementsForPuppiesFileName,
                                       String homeImprovementsForDisabledFileName,
                                       String homeImprovementsForAdultsFileName) {
        this.greetingsFileName = greetingsFileName;
        this.descriptionFileName = descriptionFileName;
        this.scheduleAndAddressFileName = scheduleAndAddressFileName;
        this.documentsForAdoptionFileName = documentsForAdoptionFileName;
        this.safetyPrecuationsFileName = safetyPrecuationsFileName;
        this.declineReasonsFileName = declineReasonsFileName;
        this.meetingRulesFileName = meetingRulesFileName;
        this.transportationRecommendationsFileName = transportationRecommendationsFileName;
        this.homeImprovementsForPuppiesFileName = homeImprovementsForPuppiesFileName;
        this.homeImprovementsForDisabledFileName = homeImprovementsForDisabledFileName;
        this.homeImprovementsForAdultsFileName = homeImprovementsForAdultsFileName;
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
        transportationRecommendations = readStringsFromFile(transportationRecommendationsFileName);
        homeImprovementsForAdults = readStringsFromFile(homeImprovementsForAdultsFileName);
        homeImprovementsForPuppies = readStringsFromFile(homeImprovementsForPuppiesFileName);
        homeImprovementsForDisabled = readStringsFromFile(homeImprovementsForDisabledFileName);

    }

    /**
     * Метод чтения из файла содержимого в виде массива строк. Затем строки склеиваются в одну чтобы передать ее в {@link com.pengrad.telegrambot.request.SendMessage#SendMessage(Object, String)}
     */
    protected String readStringsFromFile(String fileName) {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(Objects.requireNonNull(TelegramBotApplication.class.getResource(fileName).toURI()))));
        } catch (IOException e) {
            logger.error("Ошибка чтения-записи");
        } catch (URISyntaxException e) {
            logger.error("Ошибка URISyntaxException");
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
