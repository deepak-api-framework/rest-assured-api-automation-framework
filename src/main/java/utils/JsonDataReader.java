package utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {

    public static List<Map<String, String>> getJsonData(String filePath)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                new File(filePath),
                new TypeReference<List<Map<String, String>>>() {});
    }
}