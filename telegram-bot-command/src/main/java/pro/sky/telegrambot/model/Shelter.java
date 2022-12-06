package pro.sky.telegrambot.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class Shelter {
    @Value("${greetingsFileName}")
    private String greetingsFileName;
    @Value("${descriptionFileName}")
    private String descriptionFileName;
    @Value("${scheduleFileName}")
    private String scheduleAndAddressFileName;
    @Value("${documentsForAdoptionFileName}")
    private String documentsForAdoptionFileName;
    @Value("${cynologistsFileName}")
    private String cynologistsFileName;
    @Value("${safetyPrecuationsFileName")
    private String safetyPrecuationsFileName;
    @Value("${declineReasonsFileName}")
    private String declineReasonsFileName;

    private List<String> greetings;
    private List<String> description;
    private List<String> scheduleAndAddress;
    private List<String> documentsForAdoption;
    private List<String> declineReasons;

    private Logger logger = LoggerFactory.getLogger(Shelter.class);


    private List<String> contactsList;
    private List<String> safetyPrecuations;

    public Shelter() {
        updateInfoAboutShelter();
    }

    private void updateInfoAboutShelter() {
        greetings = readStringsFromFile(greetingsFileName);
        description = readStringsFromFile(descriptionFileName);
        scheduleAndAddress = readStringsFromFile(scheduleAndAddressFileName);
        documentsForAdoption = readStringsFromFile(documentsForAdoptionFileName);
        declineReasons = readStringsFromFile(declineReasonsFileName);
        safetyPrecuations = readStringsFromFile(safetyPrecuationsFileName);

    }

    private List<String> readStringsFromFile(String fileName) {
        List<String> strings = new ArrayList<>();
        try (
                FileReader fr = new FileReader(fileName);
                BufferedReader reader = new BufferedReader(fr)) {
            Stream<String> lines = reader.lines();
            strings = lines.filter(line -> !line.isBlank()).collect(Collectors.toList());

        } catch (IOException e) {
            logger.error("Can't open file + {filename}");
            strings= new ArrayList<String>(List.of("Ошибка чтения"));
        } finally {
            return strings;
        }
    }


    public List<String> greetings() {
        return greetings;
    }

    public List<String> getAbout() {
        return description;
    }

    public List<String> getScheduleAndAdress() {
        return scheduleAndAddress;
    }

    public List<String> getSafetyPrecuations() {
        return safetyPrecuations;
    }

    public List<String> getContacts() {
        return null;
    }

    public void callVolunteer() {
    }
}
