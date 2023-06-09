package ru.tinkoff.edu.bot;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.tg.command.ListCommand;
import ru.tinkoff.edu.configuration.client.ScrapperClient;
import ru.tinkoff.edu.dto.response.LinkResponse;
import ru.tinkoff.edu.dto.response.ListLinksResponse;
import ru.tinkoff.edu.tg.model.core.BotProcessor;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BotTest {
    @Mock
    private ScrapperClient client;
    @Mock
    private Update update;
    @Mock
    private Message message;
    @Mock
    private Chat chat;
    private BotProcessor botProcessor;

    @BeforeEach
    public void set() {
        botProcessor = new BotProcessor(new ListCommand(client));
    }

    @Test
    public void processListCommand_fullList() throws URISyntaxException {
        Long chatId = 1234567L;
        LinkResponse link1 = new LinkResponse(1L, new URI("https://github.com/sanyarnd/tinkoff-java-course-2022/"));
        LinkResponse link2 = new LinkResponse(2L, new URI("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"));
        ListLinksResponse response = new ListLinksResponse(List.of(link1, link2), 2);
        String messageText = "<b>Список отслеживаемых ссылок:</b>\n"
                + "1. " + link1.getUrl().toString() + "\n"
                + "2. " + link2.getUrl().toString() + "\n";

        when(update.message()).thenReturn(message);
        when(message.text()).thenReturn("/list");
        when(message.chat()).thenReturn(chat);
        when(chat.id()).thenReturn(chatId);
        when(client.getListLinks(anyLong())).thenReturn(response);

        SendMessage message = botProcessor.processCommand(update);

        Assertions.assertEquals(messageText, message.getParameters().get("text"));
        Assertions.assertEquals(chatId, message.getParameters().get("chat_id"));
    }
}
