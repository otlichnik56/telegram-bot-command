package pro.sky.telegrambot.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entitydatabase.Identity;

public interface IdentityRepository extends JpaRepository<Identity, Long> {

}
