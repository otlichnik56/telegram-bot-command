package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Shelter;

import java.util.List;

@Service
public class ShelterService {
    private final Shelter shelter;

    public ShelterService(Shelter shelter) {
        this.shelter = shelter;
    }

    public List<String> greetings() {
        return shelter.greetings();
    }

    public List<String> getAbout() {
        return shelter.getAbout();
    }

    public List<String> getSheduleAndAdress() {
        return shelter.getScheduleAndAdress();
    }

    public List<String> getSafetyPrecautions() {
        return shelter.getSafetyPrecuations();
    }

    public List<String> getContacts() {
        return shelter.getContacts();
    }

    public void callVolunteer() {
        shelter.callVolunteer();
    }

}
