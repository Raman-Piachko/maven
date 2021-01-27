package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Properties;

import static util.FileUtils.getJsonFiles;
import static util.FileUtils.getProperties;

public class FileService {
    private static final Logger LOGGER = LogManager.getLogger(FileService.class);
    public static final String REGEX = "[.][^.]+$";

    public void renameFilesFromFolder(String propertiesPath, String suffix, String filePath, String fileType) {
        Properties properties = getProperties(propertiesPath);

        getJsonFiles(propertiesPath, filePath, fileType)
                .forEach(file -> file.renameTo(new File(file.getAbsolutePath()
                        .replaceFirst(REGEX, "")
                        .concat(properties.getProperty(suffix))
                        .concat(properties.getProperty(fileType)))));
        LOGGER.info("Files nave been renamed");
    }
}