package pro.sky.telegrambot.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entitydatabase.Report;
/**
 *  репозиторий для работы с отчетами
*/
 @Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
