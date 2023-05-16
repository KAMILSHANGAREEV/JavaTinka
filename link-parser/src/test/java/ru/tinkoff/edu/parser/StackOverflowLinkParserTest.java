package ru.tinkoff.edu.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.record.StackOverflowRecord;

public class StackOverflowLinkParserTest {
    private final StackOverflowLinkParser stackOverflowLinkParser = new StackOverflowLinkParser(null);

    @Test
    public void validLinkParse() {
        long questionId = 1642028L;
        String link = "https://stackoverflow.com/questions/" + questionId + "/what-is-the-operator-in-c";

        Record record = stackOverflowLinkParser.parseLink(link);

        Assertions.assertNotNull(record);
        Assertions.assertTrue(record instanceof StackOverflowRecord);
        Assertions.assertEquals(questionId, ((StackOverflowRecord)record).questionId());
    }
}
