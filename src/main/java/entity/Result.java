package entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Result {
    private String configFileName;
    private Long executionTime;
    private List<String> fileNames;

    public Result() {
    }

    public Result(String configFileName, List<String> fileNames, Long executionTime) {
        this.configFileName = configFileName;
        this.fileNames = fileNames;
        this.executionTime = executionTime;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    @XmlElement
    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    @XmlElement
    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    @XmlElement
    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    @Override
    public String toString() {
        return "Result{" +
                "configFileName='" + configFileName + '\'' +
                ", executionTime=" + executionTime +
                ", fileNames=" + fileNames +
                '}';
    }
}