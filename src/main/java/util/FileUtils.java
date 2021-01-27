package util;

import exception.EmptyPathException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class FileUtils {
    private static final Logger LOGGER = LogManager.getLogger(FileUtils.class);

    public static Properties getProperties(String propertiesPath) {
        Properties properties = new Properties();
        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream(propertiesPath);
            properties.loadFromXML(fileInputStream);
            LOGGER.info("Properties have been read");
        } catch (IOException e) {
            LOGGER.error("Files not found", e);
            throw new EmptyPathException("Empty directory");
        }

        return properties;
    }

    public static void printFileName(String propertiesPath, String filePath, String fileType) {
        getJsonFiles(propertiesPath, filePath, fileType)
                .forEach(file -> LOGGER.info(file.getName()));
    }

    public static List<File> getJsonFiles(String propertiesPath, String filePath, String fileType) {
        Properties properties = getProperties(propertiesPath);
        List<File> fileList;
        try {
            fileList = Files.walk(Paths.get(properties.getProperty(filePath)))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter(file -> file.getName().contains(properties.getProperty(fileType)))
                    .collect(Collectors.toList());
            LOGGER.info("Files received");
        } catch (IOException e) {
            LOGGER.error("Bad path or files cannot be found", e);
            throw new EmptyPathException("Illegal path or file was not found");
        }
        return fileList;
    }

    public static List<String> getListOfFileName(String propertiesPath, String filePath, String fileType) {
        return getJsonFiles(propertiesPath, filePath, fileType)
                .stream()
                .map(File::getName)
                .collect(Collectors.toList());
    }

    public static String getNameFromFullPath(String path) {
        int idx = path.replaceAll("\\\\", "/").lastIndexOf("/");
        return idx >= 0 ? path.substring(idx + 1) : path;
    }
}