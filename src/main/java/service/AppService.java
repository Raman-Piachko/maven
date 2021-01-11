package service;

import java.io.File;
import java.util.Properties;

import static constants.AppConstants.REGEX;
import static util.AppUtils.getJsonFileStream;
import static util.AppUtils.getProperties;

public class AppService {

    public void renameFilesFromFolder(String propertiesPath, String suffix, String filePath, String fileType) {
        Properties properties = getProperties(propertiesPath);

        getJsonFileStream(propertiesPath, filePath, fileType)
                .forEach(file -> file.renameTo(new File(file.getAbsolutePath()
                        .replaceFirst(REGEX, "")
                        .concat(properties.getProperty(suffix))
                        .concat(properties.getProperty(fileType)))));
    }
}