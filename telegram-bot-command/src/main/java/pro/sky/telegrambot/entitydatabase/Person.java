package pro.sky.telegrambot.entitydatabase;

import javax.persistence.*;

@Entity
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

    private String numberPhone;

    private String fullName;

    private Boolean status;
    //nickname
    private Boolean status;


    //начало испытательного срока
    // конец испытательного срока
    //статус усыновления


    public Person(Long chatId, String numberPhone, String fullName, Boolean status) {
        this.chatId = chatId;
        this.numberPhone = numberPhone;
        this.fullName = fullName;
        this.status = status;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
