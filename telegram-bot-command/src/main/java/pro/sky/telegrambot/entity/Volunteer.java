package pro.sky.telegrambot.entity;

import javax.persistence.*;

/**
 *Класс для сохранения волонтёров в БД.
 * */
@Entity
@Table(name = "volunteer")
public class Volunteer {

    /**
     * Первичный ключ для записи в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     *  Имя пользователя из Телеграмм, начинается на символ @
     */
    private String username;
    /**
     Номер телефона для информации
     */
    private String phoneNumber;
    /**
     Контактное имя для связи для информации
     */
    private String contactName;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

}
