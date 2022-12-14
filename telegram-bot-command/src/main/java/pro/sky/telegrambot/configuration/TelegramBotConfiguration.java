package pro.sky.telegrambot.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import com.pengrad.telegrambot.request.SetMyCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TelegramBotConfiguration {

    @Value("${telegram.bot.token}")
    private String token;

    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);

        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("/menu", "Меню"));
        commands.add(new BotCommand("/hello", "Привет"));
        bot.execute(new SetMyCommands(commands.toArray(new BotCommand[]{})));

        bot.execute(new DeleteMyCommands());
        return bot;
    }

}
