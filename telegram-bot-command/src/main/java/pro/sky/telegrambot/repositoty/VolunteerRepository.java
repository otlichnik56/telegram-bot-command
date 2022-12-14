package pro.sky.telegrambot.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entitydatabase.Volunteer;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

}
