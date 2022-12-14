package pro.sky.telegrambot.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entitydatabase.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
