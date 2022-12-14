package pro.sky.telegrambot.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("Вызываю конструктор приюта");
        updateInfoAboutShelter();
    }

    public void updateInfoAboutShelter() {
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
                BufferedReader br = Files.newBufferedReader(Paths.get(fileName))
              /* FileReader fr = new FileReader(fileName);
                BufferedReader reader = new BufferedReader(fr)*/) {
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
    }


    public String greetings() {
        return greetings.stream().collect(Collectors.joining("\n"));
    }

    public String getAbout() {
        return description.toString();
    }

    public String getScheduleAndAdress() {
        return scheduleAndAddress.toString();
    }

    public String getSafetyPrecuations() {
        return safetyPrecuations.toString();
    }

    public String getContacts() {
        return null;
    }

    public void callVolunteer() {
    }

    public String getDocumentsForAdoption() {
        return documentsForAdoption.toString();
    }
}
