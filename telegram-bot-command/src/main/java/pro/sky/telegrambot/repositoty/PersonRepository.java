package pro.sky.telegrambot.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entitydatabase.Person;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT status FROM person WHERE username = :userName", nativeQuery = true)
    Boolean getPersonStatusFromDataBase(@Param("userName") String userName);

    @Query(value = "SELECT * FROM person WHERE status = :status", nativeQuery = true)
    List<Person> getPersonFromDataBase(@Param("status") Boolean status);

//    @Query(value = "UPDATE person SET end_date = end_date + :extendDays WHERE id = :id", nativeQuery = true)
  //  void addToEndDateById(@Param("id") Long id, @Param("endDate") Integer extendDays);

    //@Query(value = "DELETE FROM person WHERE username = :username AND status = :status", nativeQuery = true)
    //void deletePersonFromDataBase(@Param("username") String username, @Param("status") Boolean status);


    /** Метод возвращает лист пользователей из таблицы person.
     * В качестве параметра передается дата. Возвращаемые сущности Identity.
     * @param endDate
     * @return List<Identity>
     */
    @Query(value = "SELECT * FROM person WHERE end_date = :endDate", nativeQuery = true)
    List<Person> getUsernameEndDate(@Param("endDate") LocalDate endDate);

    @Query(value = "select * from person where end_date = current_date", nativeQuery = true)
    List<Person> getUsernameCompleted();
}
