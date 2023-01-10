package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Report;

import java.time.LocalDate;
import java.util.List;

/**
 *  репозиторий для работы с отчетами
*/
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByUsernameIgnoreCase(String username);

    List<Report> findByDateReport(LocalDate localDate);

}
