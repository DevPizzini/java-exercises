package it.iol.ws.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Giuseppe Cannella
 * Helper interface for Json
 */

public interface JsonHelper {

    ObjectMapper objectMapper = new ObjectMapper();

    static <T> JsonNode objectToJson(T o) {
        return objectMapper.valueToTree(o);
    }

    static <T> T jsonToObject(JsonNode json, Class<T> o) {
        return objectMapper.convertValue(json, o);
    }

    static <T> T stringToObject(String jsonString, Class<T> o) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, o);
    }

    static <T>JsonNode stringToObjectJson (String jsonString) throws JsonProcessingException {
        return objectMapper.readTree(jsonString);
    }

    static String objectToString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    static String objectToPrettyString(Object o) {
        return objectToJson(o).toPrettyString();
    }

}