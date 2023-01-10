package pro.sky.telegrambot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.repository.ReportRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    private static final String USERNAME = "Nik";
    static Report report = new Report();
    static LocalDate localDate = LocalDate.now();

    @BeforeEach
    public void SetUp() {
        report.setId(1L);
        report.setUsername(USERNAME);
        report.setDateReport(localDate);
    }

    @InjectMocks
    ReportService reportService;

    @Mock
    private ReportRepository reportRepository;

    @Test
    void getAllReportsPerson() {
        when(reportRepository.findByUsernameIgnoreCase(USERNAME)).thenReturn(List.of(report));
        assertEquals(reportService.getAllReportsPerson(USERNAME), List.of(report));
    }

    @Test
    void getAllReportsDaily() {
        when(reportRepository.findByDateReport(localDate)).thenReturn(List.of(report));
        assertEquals(reportService.getAllReportsDaily(localDate), List.of(report));
    }

}
