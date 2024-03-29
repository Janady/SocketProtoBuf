package logger;

public interface Logger {
    void debug(String log);

    void info(String log);

    void warn(String log);

    void error(String log);

    void error(String log, Throwable throwable);
}
