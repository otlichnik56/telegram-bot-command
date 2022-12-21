package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.Shelter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
}
