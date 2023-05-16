package ru.tinkoff.edu.scrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegrationTest extends IntegrationEnvironment {

    @Test
    public void integrationTest() {
        Assertions.assertTrue(POSTGRE_SQL_CONTAINER.isCreated());
    }
}
