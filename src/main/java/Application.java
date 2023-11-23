public class Application {
    public static void main(String[] args) {

        FileLoggerConfiguration config = new FileLoggerConfiguration("log.txt", LoggingLevel.DEBUG, 1024, "yyyy-MM-dd HH:mm:ss");
        FileLogger logger = new FileLogger(config);

        // Виклики методів для логування
        logger.debug("Debug message");
        logger.info("Info message");

        // виклик методу, що викидає виняток
        for (int i = 0; i < 20; i++) {
            logger.debug("Debug message " + i);
        }
    }
}
