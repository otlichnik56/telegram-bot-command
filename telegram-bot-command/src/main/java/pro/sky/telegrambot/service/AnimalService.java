package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Animal;
import pro.sky.telegrambot.repository.AnimalRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Сервис для работы с сущностью Animal
 */
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final Logger logger = LoggerFactory.getLogger(AnimalService.class);

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    /**
     * Возврашает список Animal
     * @return
     */
    public List<Animal> getAllAnimalsKids(String kindOfAnimal) {
        return animalRepository.findByKindOfAnimal(kindOfAnimal);
    }

    /**
     * Возврашает Animal по id
     * @param id
     * @return
     */
    public Animal getAnimal(Long id) {
        return  animalRepository.getById(id);
    }

    /**
     * Создаёт новую запись Animal в БД
     * @param animal
     * @return
     */
    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    /**
     * Редактирует запись Animal в БД
     * @param animal
     * @return
     */
    public Animal editAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    /**
     * Удаляет запись из БД по id
     * @param id
     */
    public void deleteAnimal(long id) {
        animalRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        logger.error("There is not animal with id = " + id);
        animalRepository.deleteById(id);
    }

}
