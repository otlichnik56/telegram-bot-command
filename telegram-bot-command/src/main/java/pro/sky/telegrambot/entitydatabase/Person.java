package pro.sky.telegrambot.entitydatabase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String phoneNumber;
    private String contactName;

    private Boolean isAdoptive;
    private LocalDate startDate;
    private LocalDate endDate;

    public Person() {
    }

    public Person(String username, String numberPhone, String contactName) {
        this.username = username;
        this.phoneNumber = numberPhone;
        this.contactName = contactName;
        isAdoptive = false;
        startDate = null;
        endDate = null;
    }

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

    public Boolean getAdoptive() {
        return isAdoptive;
    }

    public void setAdoptive(Boolean adoptive) {
        isAdoptive = adoptive;
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

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        Person otherPerson = (Person) obj;
        return Objects.equals(this.username, otherPerson.getUsername());
    }

    @Override
    public String toString() {
        String status = isAdoptive ? "взял собаку " + startDate + "конец исп. + " + endDate : "не брал животное";
        String personString = String.format("%d. %s, телефон %s, ник в ТГ: %s. %s", id, contactName, phoneNumber, username, status);
        return personString;
    }
}