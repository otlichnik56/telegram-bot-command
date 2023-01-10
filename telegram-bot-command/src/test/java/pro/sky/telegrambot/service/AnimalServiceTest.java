package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.Animal;
import pro.sky.telegrambot.repository.AnimalRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    private static final String KIND_OF_ANIMAL = "dog";
    static Animal animal = new Animal();

    @BeforeEach
    public void SetUp() {
        animal.setId(1L);
        animal.setName("Duck");
        animal.setAge(2L);
        animal.setKindOfAnimal(KIND_OF_ANIMAL);
        animal.setInvalid(true);
    }

    @InjectMocks
    AnimalService animalService;

    @Mock
    private AnimalRepository animalRepository;

    @Test
    void getAllAnimalsKids() {
        when(animalRepository.findByKindOfAnimal(KIND_OF_ANIMAL)).thenReturn(List.of(animal));
        assertEquals(animalService.getAllAnimalsKids(KIND_OF_ANIMAL), List.of(animal));
    }

    @Test
    void getAnimal() {
        when(animalRepository.getById(1L)).thenReturn(animal);
        assertEquals(animalService.getAnimal(1L), animal);
    }

    @Test
    void createAnimal() {
        when(animalRepository.save(animal)).thenReturn(animal);
        assertEquals(animalService.createAnimal(animal), animal);
    }

    @Test
    void editAnimal() {
        when(animalRepository.save(animal)).thenReturn(animal);
        assertEquals(animalService.editAnimal(animal), animal);
    }


}
