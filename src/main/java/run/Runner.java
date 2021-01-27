package run;

import entity.Result;
import entity.ResultWriter;
import entity.impl.JsonWriterImpl;
import entity.impl.XmlWriterImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.FileService;

import java.util.List;

import static util.FileUtils.getListOfFileName;
import static util.FileUtils.getNameFromFullPath;
import static util.FileUtils.getProperties;

public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);
    private static FileService fileService = new FileService();
    private static final String PROPERTIES_PATH = "src/main/resources/config.xml";
    private static final String SUFFIX = "suffix";
    private static final String FILE_TYPE = "fileType";
    private static final String FILE_PATH = "path";
    private static final String JSON_TYPE = "json";
    private static final String XML_TYPE = "xml";
    private static final String RESULT_FILE = "ResultFile";


    public static void runApp() {
        LOGGER.info("Application start");
        long startTime = System.nanoTime();
        ResultWriter resultWriter;
        List<String> fileNames = getListOfFileName(PROPERTIES_PATH, FILE_PATH, FILE_TYPE);
        fileService.renameFilesFromFolder(PROPERTIES_PATH, SUFFIX, FILE_PATH, FILE_TYPE);
        fileNames.addAll(getListOfFileName(PROPERTIES_PATH, FILE_PATH, FILE_TYPE));

        if (getProperties(PROPERTIES_PATH).getProperty(RESULT_FILE).equalsIgnoreCase(XML_TYPE)) {
            resultWriter = new XmlWriterImpl();
            resultWriter.writeResult(new Result(getNameFromFullPath(PROPERTIES_PATH), fileNames, System.nanoTime() - startTime));
        } else if (getProperties(PROPERTIES_PATH).getProperty(RESULT_FILE).equalsIgnoreCase(JSON_TYPE)) {
            resultWriter = new JsonWriterImpl();
            resultWriter.writeResult(new Result(getNameFromFullPath(PROPERTIES_PATH), fileNames, System.nanoTime() - startTime));
        }

        LOGGER.info("Application finished");
    }
}