package pro.sky.telegrambot.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.TelegramBotApplication;
import pro.sky.telegrambot.constants.Constants;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Shelter {


    //  @Value("${greetingsFileName}")
    private String greetingsFileName = Constants.greetingsFileName;
    //@Value("${descriptionFileName}")
    private String descriptionFileName = Constants.descriptionFileName;
    //    @Value("${scheduleFileName}")
    private String scheduleAndAddressFileName = Constants.scheduleFileName;

    //    @Value("${documentsForAdoptionFileName}")
    private String documentsForAdoptionFileName = Constants.documentsForAdoptionFileName;
    //    @Value("${safetyPrecuationsFileName")
    private String safetyPrecuationsFileName = Constants.safetyPrecuationsFileName;
    //    @Value("${declineReasonsFileName}")
    private String declineReasonsFileName = Constants.declineReasonsFileName;
    private String meetingRulesFileName = Constants.meetingRulesFileName;
    private String approvedCynologystsFileName = Constants.approvedCynologystsFileName;
    private String cynologystsAdvicesFileName = Constants.cynologystsAdvicesFileName;

    private String transportationRecommendationsFileName = Constants.transportationRecommendationsFileName;
    private String homeImprovementsForPuppiesFileName = Constants.homeImprovementsForPuppiesFileName;
    private String homeImprovementsForDisabledFileName = Constants.homeImprovementsForDisabledFileName;
    private String homeImprovementsForAdultsFileName = Constants.homeImprovementsForAdultsFileName;



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


    private List<String> contactsList;
    private String safetyPrecuations;

    private Logger logger = LoggerFactory.getLogger(Shelter.class);



    public Shelter() {

        updateInfoAboutShelter();
    }

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

    private String readStringsFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(Objects.requireNonNull(TelegramBotApplication.class.getResource(fileName).toURI())))
                    .stream().collect(Collectors.joining("\n"));
        } catch (IOException e) {

        } catch (URISyntaxException e) {

        }

        return "Не могу считать информацию";
    }

/*    private List<String> readStringsFromFile(String fileName) {
        List<String> strings = new ArrayList<>();
        try (
                BufferedReader br = Files.newBufferedReader(Paths.get(fileName))
              *//* FileReader fr = new FileReader(fileName);
                BufferedReader reader = new BufferedReader(fr)*//*) {
            while (br.ready()) {
                strings.add(br.readLine());
            }
            System.out.println("пытаюсь прочитать " + fileName);
            //strings = new BufferedReader(new InputStreamReader(/is)).lines().filter(line-> !line.isBlank()).collect(Collectors.toList());
            //strings = lines.filter(line -> !line.isBlank()).collect(Collectors.toList());
            System.out.println("!!! " + strings);

        } catch (IOException e) {
            logger.error("Can't open file + " + fileName);
            strings = new ArrayList<String>(List.of("Ошибка чтения"));
        } finally {
            return strings;
        }
    }*/


    public String greetings() {
        return greetings;
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

    public String getContacts() {
        return null;
    }

    public void callVolunteer() {
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
