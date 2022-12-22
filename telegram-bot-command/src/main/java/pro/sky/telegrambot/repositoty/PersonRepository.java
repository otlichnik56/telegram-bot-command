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

    @Query(value = "UPDATE person SET start_date = :startDate AND end_date = :endDate WHERE id = :id", nativeQuery = true)
    void updatePersonDateFromDataBase(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("id") Long id);

    @Query(value = "UPDATE person SET end_date = :endDate WHERE id = :id", nativeQuery = true)
    void updatePersonFromDataBase(@Param("endDate") LocalDate endDate, @Param("id") Long id);

    //@Query(value = "DELETE FROM person WHERE username = :username AND status = :status", nativeQuery = true)
    //void deletePersonFromDataBase(@Param("username") String username, @Param("status") Boolean status);

}
