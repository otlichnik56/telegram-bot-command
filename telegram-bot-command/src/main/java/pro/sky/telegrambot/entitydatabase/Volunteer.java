package pro.sky.telegrambot.entitydatabase;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Volunteer extends Person{
    public Volunteer(Long chatId, String numberPhone, String fullName, Boolean status) {
        super(chatId, numberPhone, fullName, status);
    }

    public Volunteer() {
    }
}
