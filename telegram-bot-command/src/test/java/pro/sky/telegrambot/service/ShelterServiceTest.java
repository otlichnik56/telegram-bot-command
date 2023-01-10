package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.Person;
import pro.sky.telegrambot.model.Shelter;
import pro.sky.telegrambot.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShelterServiceTest {

    static Person person = new Person();
    static LocalDate localDate = LocalDate.now();
    static String fileContent = "it is epic fail";

    @BeforeEach
    public void SetUp() {
        person.setId(1L);
        person.setUsername("Jacky");
        person.setPhoneNumber("7894562130");
        person.setContactName("Jacky Chan");
        person.setChatId(77777777777L);
        person.setAdoptive(true);
        person.setStartProbationDate(localDate);
        person.setEndProbationDate(localDate);
    }

    @InjectMocks
    ShelterService shelterService;

    @Mock
    private Shelter shelter;

    @Mock
    private PersonRepository personRepository;


    @Test
    void saveContact() {
        shelterService.saveContact(person);
        assertNotNull(personRepository);
        verify(personRepository,only()).save(person);
    }

    @Test
    void getAbout() {
        when(shelter.getAbout()).thenReturn(fileContent);
        assertEquals(shelterService.getAbout(), fileContent);
        verify(shelter,only()).getAbout();
    }

    @Test
    void getScheduleAndAddress() {
        when(shelter.getScheduleAndAddress()).thenReturn(fileContent);
        assertEquals(shelterService.getScheduleAndAddress(), fileContent);
        verify(shelter,only()).getScheduleAndAddress();
    }

    @Test
    void getSafetyPrecautions() {
        when(shelter.getSafetyPrecautions()).thenReturn(fileContent);
        assertEquals(shelterService.getSafetyPrecautions(), fileContent);
        verify(shelter,only()).getSafetyPrecautions();
    }

    @Test
    void getDocumentsForAdoption() {
        when(shelter.getDocumentsForAdoption()).thenReturn(fileContent);
        assertEquals(shelterService.getDocumentsForAdoption(), fileContent);
        verify(shelter,only()).getDocumentsForAdoption();
    }

    @Test
    void getDeclineReasons() {
        when(shelter.getDeclineReasons()).thenReturn(fileContent);
        assertEquals(shelterService.getDeclineReasons(), fileContent);
        verify(shelter,only()).getDeclineReasons();
    }

    @Test
    void getMeetingRules() {
        when(shelter.getMeetingRules()).thenReturn(fileContent);
        assertEquals(shelterService.getMeetingRules(), fileContent);
        verify(shelter,only()).getMeetingRules();
    }

    @Test
    void getTransportationRecommendations() {
        when(shelter.getTransportationRecommendations()).thenReturn(fileContent);
        assertEquals(shelterService.getTransportationRecommendations(), fileContent);
        verify(shelter,only()).getTransportationRecommendations();
    }

    @Test
    void getHomeImprovementsForAdultsRecommendations() {
        when(shelter.getHomeImprovementsForAdultsRecommendations()).thenReturn(fileContent);
        assertEquals(shelterService.getHomeImprovementsForAdultsRecommendations(), fileContent);
        verify(shelter,only()).getHomeImprovementsForAdultsRecommendations();
    }

    @Test
    void getHomeImprovementsForPuppiesRecommendations() {
        when(shelter.getHomeImprovementsForPuppiesRecommendations()).thenReturn(fileContent);
        assertEquals(shelterService.getHomeImprovementsForPuppiesRecommendations(), fileContent);
        verify(shelter,only()).getHomeImprovementsForPuppiesRecommendations();
    }

    @Test
    void getHomeImprovementsForDisabledRecommendations() {
        when(shelter.getHomeImprovementsForDisabledRecommendations()).thenReturn(fileContent);
        assertEquals(shelterService.getHomeImprovementsForDisabledRecommendations(), fileContent);
        verify(shelter,only()).getHomeImprovementsForDisabledRecommendations();
    }

    @Test
    void printContactsList() {
        when(personRepository.findAll()).thenReturn(List.of(person));
        assertEquals(shelterService.printContactsList(), person.toString());
        verify(personRepository,only()).findAll();
    }

}