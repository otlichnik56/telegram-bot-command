package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Person;
import pro.sky.telegrambot.repository.PersonRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Сервис для работы с сущностью Person
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    /**
     * Возвращает список Person
     * @return
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Возвращает Person по id
     * @param id
     * @return
     */
    public Person getPerson(Long id) {
        return  personRepository.getById(id);
    }

    /**
     * Создаёт новую запись Person в БД
     * @param person
     * @return
     */
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * Редактирует запись Person в БД
     * @param person
     * @return
     */
    public Person editPerson(Person person) {
        return personRepository.save(person);
    }

    /**
     * Удаляет запись из БД по id
     * @param id
     */
    public void deletePerson(Long id) {
        personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.error("There is not animal with id = " + id);
        personRepository.deleteById(id);
    }


}
