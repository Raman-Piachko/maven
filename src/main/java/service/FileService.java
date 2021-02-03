package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Properties;

import static service.PropertiesConstants.EMPTY_STRING;
import static service.PropertiesConstants.PATH_REGEX;
import static util.FileUtils.getJsonFiles;
import static util.FileUtils.getProperties;

public class FileService {
    private static final Logger LOGGER = LogManager.getLogger(FileService.class);


    public void renameFilesFromFolder(String propertiesPath, String suffix, String filePath, String fileType) {
        Properties properties = getProperties(propertiesPath);

        getJsonFiles(propertiesPath, filePath, fileType)
                .forEach(file -> file.renameTo(new File(file.getAbsolutePath()
                        .replaceFirst(PATH_REGEX, EMPTY_STRING)
                        .concat(properties.getProperty(suffix))
                        .concat(properties.getProperty(fileType)))));
        LOGGER.info("Files nave been renamed");
    }
}