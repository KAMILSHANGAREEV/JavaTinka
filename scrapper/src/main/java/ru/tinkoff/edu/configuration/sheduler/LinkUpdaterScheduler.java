package ru.tinkoff.edu.configuration.sheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LinkUpdaterScheduler {

    @Scheduled(fixedDelayString = "#{@applicationConfig.scheduler.interval()}")
    public void update() {
        System.out.println("Data update started");
        System.out.println("Data update finished");
    }
}
