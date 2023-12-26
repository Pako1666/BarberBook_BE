package app.bb.utilspackage.jsonparse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONBusiness<T extends Object> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static <T> String stringify(T o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }

    public static <T> T parse(String json,Class cls) throws IOException {
        return (T) objectMapper.readValue(json.getBytes(),cls);
    }
}
