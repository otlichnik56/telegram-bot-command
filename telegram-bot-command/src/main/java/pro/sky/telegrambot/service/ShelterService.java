package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Shelter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ShelterService {
    private final Shelter shelter;

    public ShelterService(Shelter shelter) {

        this.shelter = shelter;
    }

    public String greetings() {
        return shelter.greetings();
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
    public String getDocumentsForAdpotion(){return shelter.getDocumentsForAdoption();}
    public String getDeclineReasons(){return shelter.getDeclineReasons();}

    public String getContacts() {
        return shelter.getContacts();
    }

    public void callVolunteer() {
        shelter.callVolunteer();
    }

    public void updateInfo(){
        shelter.updateInfoAboutShelter();
    }

    public String hello() {
        try (BufferedReader br = Files.newBufferedReader(     Paths.get("C:\\Users\\mishutkin.va\\IdeaProjects\\commandProject\\telegram-bot-command\\src\\main\\resources\\files\\hello.txt"))){
            return br.readLine();
        }catch (IOException e){

        }
     return null;

    }
}
