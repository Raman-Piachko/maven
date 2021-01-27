package entity.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Result;
import entity.ResultWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JsonWriterImpl implements ResultWriter {
    private static final Logger LOGGER = LogManager.getLogger(JsonWriterImpl.class);
    private static final String TARGET_PATH = "target/result.json";

    @Override
    public void writeResult(Result result) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(TARGET_PATH), result);
        } catch (IOException e) {
            LOGGER.error("Something is wrong at JSONWriter!!!", e);
        }
    }
}