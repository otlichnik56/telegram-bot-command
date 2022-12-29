package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.repository.ReportRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для работы с сущностью Person
 */
@Service
public class ReportService {

    private final ReportRepository reportRepository;


    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Возвращает список отчётов определенного контакта
     * @param username
     * @return List<Report>
     */
    public List<Report> getAllReportsPerson(String username) {
        return reportRepository.findByUsernameIgnoreCase(username);
    }

    /**
     * Возвращает отчётов за определенный день
     * @param localDate
     * @return List<Report>
     */
    public List<Report> getAllReportsDaily(LocalDate localDate) {
        return  reportRepository.findByDateReport(localDate);
    }


}
