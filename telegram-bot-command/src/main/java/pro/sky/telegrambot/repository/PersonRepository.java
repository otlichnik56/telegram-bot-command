package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Person;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс JPA репозитория для сохранения контактов
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * метод поиска статуса человека. не используется
     * @deprecated
     * @param userName
     * @return
     */
    @Query(value = "SELECT status FROM person WHERE username = :userName", nativeQuery = true)
    Boolean getPersonStatusFromDataBase(@Param("userName") String userName);

    /**
     * Метод не используется
     * @deprecated
     * @param status
     * @return
     */
    @Query(value = "SELECT * FROM person WHERE status = :status", nativeQuery = true)
    List<Person> getPersonFromDataBase(@Param("status") Boolean status);

    /** Метод возвращает лист пользователей из таблицы person.
     * В качестве параметра передается дата. Возвращаемые сущности Identity.
     * @deprecated
     * @param endDate
     * @return List<Identity>
     */
    @Query(value = "SELECT * FROM person WHERE end_date = :endDate", nativeQuery = true)
    List<Person> getUsernameEndDate(@Param("endDate") LocalDate endDate);

    /**
     * Запрос людей, которые сегодня прошли испытательный срок для поздравления
     * @return Список людей у которых сегодня прошел испытательный срок
     */
    @Query(value = "select * from person where end_date = current_date", nativeQuery = true)
    List<Person> getUsernameCompleted();
}
