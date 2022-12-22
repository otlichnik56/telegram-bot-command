package pro.sky.telegrambot.entitydatabase;

import javax.persistence.Entity;
import javax.persistence.Table;

/** Сущность необходима для хранения промежуточных результатов типа Person в БД.
 * При получении из БД сущностей Person у которых поле endDate сегодня, полученные сущности сохраняются как новые сущности Identity.
 */
@Entity
@Table(name = "identity")
public class Identity extends Person{

}
