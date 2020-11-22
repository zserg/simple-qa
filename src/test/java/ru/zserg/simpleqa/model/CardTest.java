package ru.zserg.simpleqa.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void simpleTest() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        String data = "" +
                "[{\"front\":\"f1\", \"back\":\"b1\"}," +
                "{\"front\":\"f2\", \"back\":\"b2\"}]";

        Card[] cards = om.readValue(data, Card[].class);
        assertTrue(true);

    }
}