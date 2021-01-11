import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Stream;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final String PROPERTIES_PATH = "src/main/resources/config.properties";
    private static final String SUFFIX = "suffix";
    private static final String FILE_TYPE = "fileType";
    private static final String FILE_PATH = "path";

    public static void main(String[] args) {
        renameFilesFromFolder();
    }

    private static void renameFilesFromFolder() {
        ArrayList<String> propertiesList = getProperties();
        printFileName(propertiesList);
        try {
            getJsonFileStream(propertiesList)
                    .forEach(file -> file.renameTo(new File(file.getAbsolutePath()
                            .replaceFirst("[.][^.]+$", "")
                            .concat(propertiesList.get(2))
                            .concat(propertiesList.get(1)))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        printFileName(propertiesList);
    }

    private static void printFileName(ArrayList<String> propertiesList) {
        try {
            getJsonFileStream(propertiesList)
                    .forEach(file -> LOGGER.info(file.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Stream<File> getJsonFileStream(ArrayList<String> propertiesList) throws IOException {
        return Files.walk(Paths.get(propertiesList.get(0)))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .filter(file -> file.getName().contains(propertiesList.get(1)));
    }

    private static ArrayList<String> getProperties() {
        Properties properties = new Properties();
        ArrayList<String> propertiesList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_PATH);
            properties.load(fileInputStream);
            String suffix = properties.getProperty(SUFFIX);
            String fileType = properties.getProperty(FILE_TYPE);
            String path = properties.getProperty(FILE_PATH);

            propertiesList.add(path);
            propertiesList.add(fileType);
            propertiesList.add(suffix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertiesList;
    }
}