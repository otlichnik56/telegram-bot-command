package pro.sky.telegrambot.entitydatabase;

import javax.persistence.*;
import java.time.LocalDate;
/**
 * Класс для сохранения отчетов в БД
 */
@Entity
@Table(name = "report")
public class Report {
    /**
     * Первичный ключ записи в БД
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Имя пользователя в телеграмме, начинается с @
     */
    private String username;
    /**
     * текст сопровождение отчета - описание рациона, поведения..
     */
    private String message;
    /**
     * Представление фото отчета в виде массива байт
     */
    @Lob
    private byte[] photo;
    /**
     * Дата фотоотчета
     */
    private LocalDate dateReport;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public LocalDate getDateReport() {
        return dateReport;
    }

    public void setDateReport(LocalDate dateReport) {
        this.dateReport = dateReport;
    }

}