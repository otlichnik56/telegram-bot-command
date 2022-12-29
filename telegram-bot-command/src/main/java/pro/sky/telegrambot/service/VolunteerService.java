package pro.sky.telegrambot.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Сервис для работы с сущностью Volunteer
 */
@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final Logger logger = LoggerFactory.getLogger(VolunteerService.class);


    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /**
     * Возвращает список Volunteer
     * @return
     */
    public List<Volunteer> getAllVolunteer() {
        return volunteerRepository.findAll();
    }

    /**
     * Возвращает Volunteer по id
     * @param id
     * @return
     */
    public Volunteer getVolunteer(Long id) {
        return volunteerRepository.getById(id);
    }

    /**
     * Создаёт новую запись Volunteer в БД
     * @param volunteer
     * @return
     */
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    /**
     * Редактирует запись Volunteer в БД
     * @param volunteer
     * @return
     */
    public Volunteer editVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    /**
     * Удаляет запись из БД по id
     * @param id
     */
    public void deleteVolunteer(Long id) {
        volunteerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.error("There is not volunteer with id = " + id);
        volunteerRepository.deleteById(id);
    }

}
