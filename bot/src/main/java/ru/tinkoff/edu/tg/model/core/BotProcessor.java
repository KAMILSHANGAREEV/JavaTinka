package ru.tinkoff.edu.tg.model.core;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import ru.tinkoff.edu.tg.command.Command;
import ru.tinkoff.edu.tg.command.TrackCommand;
import ru.tinkoff.edu.tg.command.UntrackCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BotProcessor {
    private final List<? extends Command> commands;
    private final List<Long> trackRequest = new ArrayList<>();
    private final List<Long> untrackRequest = new ArrayList<>();

    public BotProcessor(Command... commands) {
        this.commands = Arrays.stream(commands).toList();
    }

    public SendMessage processCommand(Update update) {
        String messageText = update.message().text();
        long chatId = update.message().chat().id();

        if (messageText.startsWith("/")) {
            trackRequest.remove(chatId);
            untrackRequest.remove(chatId);

            String command = messageText.substring(1);
            Command processor = commands.stream()
                    .filter(el -> el.command().equals(command))
                    .findFirst()
                    .orElse(null);

            if (processor == null) {
                return invalidCommandMessage(update);
            } else {
                if (processor instanceof TrackCommand) {
                    trackRequest.add(chatId);
                }
                if (processor instanceof UntrackCommand) {
                    untrackRequest.add(chatId);
                }
                return processor.process(update);
            }
        }

        if (trackRequest.contains(chatId)) {
            Command trackCommand = commands.stream()
                    .filter(el -> el.command().equals("track"))
                    .findFirst()
                    .orElse(null);

            if (trackCommand != null) {
                return trackCommand.process(update);
            }
        }

        if (untrackRequest.contains(chatId)) {
            Command untrackCommand = commands.stream()
                    .filter(el -> el.command().equals("untrack"))
                    .findFirst()
                    .orElse(null);

            if (untrackCommand != null) {
                return untrackCommand.process(update);
            }
        }

        return invalidCommandMessage(update);
    }

    private SendMessage invalidCommandMessage(Update update) {
        return new SendMessage(update.message().chat().id(), "Неизвестная команда");
    }
}
