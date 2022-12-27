package pro.sky.telegrambot.entitydatabase;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String numberPhone;
    private String contactName;
    private Boolean status;
    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean conditionTest;

    private Long chatId;
    public Person() {
    }


    public Person(String username, String numberPhone, String contactName, Long chatId) {
        this.username = username;
        this.numberPhone = numberPhone;
        this.contactName = contactName;
        status = false;
        startDate = null;
        endDate = null;
        conditionTest = true;
        this.chatId = chatId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String firstName) {
        this.contactName = firstName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getConditionTest() {
        return conditionTest;
    }

    public void setConditionTest(Boolean conditionTest) {
        this.conditionTest = conditionTest;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}