package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.CatShelter;
import pro.sky.telegrambot.repository.PersonRepository;
import pro.sky.telegrambot.repository.ReportRepository;

/**
 * Пока в работе
 */
@Service
public class CatShelterService extends ShelterService{
    public CatShelterService(CatShelter catShelter, PersonRepository contactRepository, ReportRepository reportRepository) {
        super(catShelter, contactRepository, reportRepository);
    }
}
