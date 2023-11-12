import java.io.FileWriter;
import java.util.Date;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

public class FileLogger {
    private FileLoggerConfiguration config;

    public FileLogger(FileLoggerConfiguration config) {
        this.config = config;
    }

    private void writeToFile(String message) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(config.filePath, true))) {
            writer.println(message);
        }
    }

    private String getLogHeader(LoggingLevel level) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        return "[" + currentTime + "][" + level + "]";
    }

    private void checkFileSize() throws FileMaxSizeReachedException {
        long fileSize = 0;
        try {
            fileSize = java.nio.file.Files.size(java.nio.file.Paths.get(config.filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileSize >= config.maxFileSize) {
            throw new FileMaxSizeReachedException("File size limit reached. Max size: " + config.maxFileSize +
                    " bytes, Current size: " + fileSize + " bytes, File path: " + config.filePath);
        }
    }

    public void debug(String message) {
        if (config.loggingLevel == LoggingLevel.DEBUG || config.loggingLevel == LoggingLevel.INFO) {
            String logHeader = getLogHeader(LoggingLevel.DEBUG);
            String logMessage = logHeader + " Message: " + message;
            try {
                checkFileSize();
                writeToFile(logMessage);
            } catch (IOException | FileMaxSizeReachedException e) {
                e.printStackTrace();
            }
        }
    }

    public void info(String message) {
        if (config.loggingLevel == LoggingLevel.INFO) {
            String logHeader = getLogHeader(LoggingLevel.INFO);
            String logMessage = logHeader + " Message: " + message;
            try {
                checkFileSize();
                writeToFile(logMessage);
            } catch (IOException | FileMaxSizeReachedException e) {
                e.printStackTrace();
            }
        }
    }
}
