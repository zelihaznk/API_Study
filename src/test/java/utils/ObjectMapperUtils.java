package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {


    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    //Json'u Java'ya çevirecek methodu yapmalıyız
    public static <T> T convertJsonToJava(String json, Class<T> cls) {

        T javaResult = null;

        try {
            mapper.readValue(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return javaResult;
    }


}
