package pro.sky.telegrambot.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entitydatabase.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
