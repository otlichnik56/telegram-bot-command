package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VolunteerServiceTest {

    static Volunteer volunteer = new Volunteer();

    @BeforeEach
    public void SetUp() {
        volunteer.setId(1L);
        volunteer.setUsername("Mike");
    }

    @InjectMocks
    VolunteerService volunteerService;

    @Mock
    private VolunteerRepository volunteerRepository;

    @Test
    void getAllVolunteer() {
        when(volunteerRepository.findAll()).thenReturn(List.of(volunteer));
        assertEquals(volunteerService.getAllVolunteer(), List.of(volunteer));
    }

    @Test
    void getVolunteer() {
        when(volunteerRepository.getById(1L)).thenReturn(volunteer);
        assertEquals(volunteerService.getVolunteer(1L), volunteer);
    }

    @Test
    void createVolunteer() {
        when(volunteerRepository.save(volunteer)).thenReturn(volunteer);
        assertEquals(volunteerService.createVolunteer(volunteer), volunteer);
    }

    @Test
    void editVolunteer() {
        when(volunteerRepository.save(volunteer)).thenReturn(volunteer);
        assertEquals(volunteerService.editVolunteer(volunteer), volunteer);
    }

}
