package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.DogShelter;
import pro.sky.telegrambot.repository.PersonRepository;
import pro.sky.telegrambot.repository.ReportRepository;

/**
 * Пока в работе
 */

@Service
public class DogShelterService extends ShelterService{
    public DogShelterService(DogShelter dogShelter, PersonRepository contactRepository, ReportRepository reportRepository) {
        super(dogShelter, contactRepository, reportRepository);
    }

    public String getApprovedCynologysts() {
        DogShelter dogShelter = (DogShelter) this.getShelter();
        return dogShelter.getApprovedCunologysts();
    }

    public String getCynologystsAdvices() {
        DogShelter dogShelter = (DogShelter) this.getShelter();
        return dogShelter.getCynologystsAdvices();
    }
}
