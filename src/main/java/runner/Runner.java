package runner;

import entity.Result;
import entity.ResultWriter;
import entity.impl.JsonWriterImpl;
import entity.impl.XmlWriterImpl;
import exception.WrongTypeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.FileService;

import java.util.List;

import static service.PropertiesConstants.*;
import static util.FileUtils.*;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    private static FileService fileService = new FileService();


    public static void runApp() {
        LOGGER.info("Application start");

        long startTime = System.nanoTime();

        List<String> fileNames = getListOfFileNames(PROPERTIES_PATH, FILE_PATH, FILE_TYPE);
        fileService.renameFilesFromFolder(PROPERTIES_PATH, SUFFIX, FILE_PATH, FILE_TYPE);
        fileNames.addAll(getListOfFileNames(PROPERTIES_PATH, FILE_PATH, FILE_TYPE));

        ResultWriter resultWriter;
        if (isValidFileType(XML_TYPE)) {
            resultWriter = new XmlWriterImpl();
        } else if (isValidFileType(JSON_TYPE)) {
            resultWriter = new JsonWriterImpl();
        } else {
            throw new WrongTypeException("Type of result file is wrong!!!");
        }

        resultWriter.writeResult(new Result(getNameFromFullPath(PROPERTIES_PATH), fileNames, System.nanoTime() - startTime));

        LOGGER.info("Application finished");
    }

    private static boolean isValidFileType(String fileType) {
        return getProperties(PROPERTIES_PATH).getProperty(RESULT_FILE).equalsIgnoreCase(fileType);
    }
}