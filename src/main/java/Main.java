import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.FileService;

import static util.FileUtils.printFileName;

public class Main {
    private static FileService fileService = new FileService();
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static final String PROPERTIES_PATH = "src/main/resources/config.xml";
    public static final String SUFFIX = "suffix";
    public static final String FILE_TYPE = "fileType";
    public static final String FILE_PATH = "path";

    public static void main(String[] args) {
        LOGGER.info("Application start");
        runApp();
        LOGGER.info("Application finished");
    }

    private static void runApp() {
        printFileName(PROPERTIES_PATH, FILE_PATH, FILE_TYPE);
        fileService.renameFilesFromFolder(PROPERTIES_PATH, SUFFIX, FILE_PATH, FILE_TYPE);
        printFileName(PROPERTIES_PATH, FILE_PATH, FILE_TYPE);
    }
}