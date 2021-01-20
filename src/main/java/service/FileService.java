package service;

import java.io.File;
import java.util.Properties;

import static util.FileUtils.getJsonFileStream;
import static util.FileUtils.getProperties;

public class FileService {
    public static final String REGEX = "[.][^.]+$";

    public void renameFilesFromFolder(String propertiesPath, String suffix, String filePath, String fileType) {
        Properties properties = getProperties(propertiesPath);

        getJsonFileStream(propertiesPath, filePath, fileType)
                .forEach(file -> file.renameTo(new File(file.getAbsolutePath()
                        .replaceFirst(REGEX, "")
                        .concat(properties.getProperty(suffix))
                        .concat(properties.getProperty(fileType)))));
    }
}