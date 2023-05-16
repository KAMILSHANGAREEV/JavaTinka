package ru.tinkoff.edu.configuration.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.client.git.GitHubClient;
import ru.tinkoff.edu.client.so.StackOverflowClient;


@Configuration
public class ClientConfiguration {

    @Bean("gitHubClient")
    public GitHubClient gitHubClient() {
        return new GitHubClient();
    }

    @Bean("stackOverflowClient")
    public StackOverflowClient stackOverflowClient() {
        return new StackOverflowClient();
    }
}
