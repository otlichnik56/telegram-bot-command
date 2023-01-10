package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Volunteer;

/**
 *  репозиторий для работы с волонтёрами
 */
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {


}
