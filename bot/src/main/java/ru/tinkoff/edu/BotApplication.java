package ru.tinkoff.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.edu.configuration.client.ScrapperClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tinkoff.edu.configuration.ApplicationConfig;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication
{

    public static void main( String[] args )
    {
        var ctx = SpringApplication.run(BotApplication.class, args);
    }

    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperClient();
    }
}
