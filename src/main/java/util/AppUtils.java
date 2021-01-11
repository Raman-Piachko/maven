package util;

import exception.EmptyPathException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

public class AppUtils {
    private static final Logger LOGGER = Logger.getLogger(AppUtils.class);

    public static Properties getProperties(String propertiesPath) {
        Properties properties = new Properties();
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream(propertiesPath);
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error("Files not found", e);
            throw new EmptyPathException("Empty directory");
        }

        return properties;
    }

    public static void printFileName(String propertiesPath, String filePath, String fileType) {
        getJsonFileStream(propertiesPath, filePath, fileType)
                .forEach(file -> LOGGER.info(file.getName()));
    }

    public static Stream<File> getJsonFileStream(String propertiesPath, String filePath, String fileType) {
        Properties properties = getProperties(propertiesPath);
        try {
            return Files.walk(Paths.get(properties.getProperty(filePath)))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(file -> file.getName().contains(properties.getProperty(fileType)));
        } catch (IOException e) {
            LOGGER.error("bad path or files cannot be found", e);
            throw new EmptyPathException("Illegal path or file was not found");
        }
    }
}