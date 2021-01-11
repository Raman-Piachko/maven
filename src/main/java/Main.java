import service.AppService;

import static constants.AppConstants.FILE_PATH;
import static constants.AppConstants.FILE_TYPE;
import static constants.AppConstants.PROPERTIES_PATH;
import static constants.AppConstants.SUFFIX;
import static util.AppUtils.printFileName;

public class Main {
    private static AppService appService = new AppService();

    public static void main(String[] args) {
        runApp();
    }

    private static void runApp() {
        printFileName(PROPERTIES_PATH, FILE_PATH, FILE_TYPE);
        appService.renameFilesFromFolder(PROPERTIES_PATH, SUFFIX, FILE_PATH, FILE_TYPE);
        printFileName(PROPERTIES_PATH, FILE_PATH, FILE_TYPE);
    }
}