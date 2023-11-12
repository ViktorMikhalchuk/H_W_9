public class FileLoggerConfiguration {
    String filePath;
    LoggingLevel loggingLevel;
    long maxFileSize;
    String logFormat;

    public FileLoggerConfiguration(String filePath, LoggingLevel loggingLevel, long maxFileSize, String logFormat) {
        this.filePath = filePath;
        this.loggingLevel = loggingLevel;
        this.maxFileSize = maxFileSize;
        this.logFormat = logFormat;
    }
}
