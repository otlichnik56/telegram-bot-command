package pro.sky.telegrambot.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
/**
 *Класс для сохранения контактов в БД.
 * */
@Entity
@Table(name = "person")
public class Person {

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
    /**
     * идентификатор чата в телеграмме
     */
    private Long chatId;
    /**
     * переменная типа boolean. true если человек взял собаку из приюта
     */
    private Boolean isAdoptive;
    /**
     * дата начала испытательного срока
     */
    private LocalDate startProbationDate;
    /**
     * дата окончания испытательного срока, может сдвигаться методом {@link pro.sky.telegrambot.service.ShelterService#extendProbation(String)}}
     */
    private LocalDate endProbationDate;

    public Person() {
    }

    public Person(String username, String numberPhone, String contactName, Long chatId) {
        this.username = username;
        this.phoneNumber = numberPhone;
        this.contactName = contactName;
        this.chatId = chatId;
        isAdoptive = false;
        startProbationDate = null;
        endProbationDate = null;
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

    public LocalDate getStartProbationDate() {
        return startProbationDate;
    }

    public void setStartProbationDate(LocalDate startDate) {
        this.startProbationDate = startDate;
    }

    public LocalDate getEndProbationDate() {
        return endProbationDate;
    }

    public void setEndProbationDate(LocalDate endDate) {
        this.endProbationDate = endDate;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
    this.chatId = chatId;
    }


    @Override
    public int hashCode() {
            return Objects.hashCode(this);
        }

    @Override
    public boolean equals (Object obj){
        Person otherPerson = (Person) obj;
        return Objects.equals(this.username, otherPerson.getUsername());
    }

    @Override
    public String toString () {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        String status = isAdoptive ? " взял собаку " + startProbationDate.format(dateFormatter) + " конец исп. " + endProbationDate.format(dateFormatter) : "не брал(а) животное";
        String personString = String.format("%d. %s, телефон %s, ник в ТГ: %s. %s", id, contactName, phoneNumber, username, status);
        return personString;
    }


}