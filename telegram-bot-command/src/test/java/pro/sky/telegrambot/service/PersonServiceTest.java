package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.Person;
import pro.sky.telegrambot.repository.PersonRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    static Person person = new Person();

    @BeforeEach
    public void SetUp() {
        person.setId(1L);
        person.setUsername("Jack");
    }

    @InjectMocks
    PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void getAllPersons() {
        when(personRepository.findAll()).thenReturn(List.of(person));
        assertEquals(personService.getAllPersons(), List.of(person));
    }

    @Test
    void getPerson() {
        when(personRepository.getById(1L)).thenReturn(person);
        assertEquals(personService.getPerson(1L), person);
    }

    @Test
    void createPerson() {
        when(personRepository.save(person)).thenReturn(person);
        assertEquals(personService.createPerson(person), person);
    }

    @Test
    void editPerson() {
        when(personRepository.save(person)).thenReturn(person);
        assertEquals(personService.editPerson(person), person);
    }


}
