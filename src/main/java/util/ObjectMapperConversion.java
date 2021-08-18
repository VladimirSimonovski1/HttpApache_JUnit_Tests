package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperConversion {

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T JSONToObject(String json, Class clazz) throws JsonProcessingException {
        return mapper.readValue(json, mapper.constructType(clazz));
    }

    public static String objectToString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
