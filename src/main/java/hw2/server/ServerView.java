package hw2.server;

public interface ServerView {
    void serverStart();
    void serverStop();
    void appendLog(String message);
}
