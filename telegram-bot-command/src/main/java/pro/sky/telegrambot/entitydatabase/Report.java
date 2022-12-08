package pro.sky.telegrambot.entitydatabase;

import javax.persistence.*;

@Entity
@Table
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

    private String message;
}
