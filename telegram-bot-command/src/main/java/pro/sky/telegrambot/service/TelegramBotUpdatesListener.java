package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * сервис будет делиться на 3 других, временно существует в таком виде
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final MenuService menuService;
    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private TelegramBot telegramBot;



    public TelegramBotUpdatesListener(MenuService menuService, TelegramBot telegramBot) {
        this.menuService = menuService;
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            menuService.shelterServiceChooser(update.message());
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
