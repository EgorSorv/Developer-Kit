package hw2.client;

import hw2.server.Server;

public class Client {
    private final ClientView clientView;
    private final Server server;
    private boolean logIn;
    private String name;

    public Client(ClientView clientView, Server server) {
        this.clientView = clientView;
        this.server = server;
    }

    public boolean connectionWithServer(String name) {
        this.name = name;

        if (server.connectUser(this)) {
            showMessage("You have successfully connected\n");
            logIn = true;
            String log = server.getLog();

            if (log != null)
                showMessage(log);

            return true;
        } else {
            showMessage("Connection failed");
            return false;
        }
    }

    public void disconnectFromServer() {
        if (logIn) {
            logIn = false;
            clientView.disconnectFromServer();
            server.disconnectUser(this);
            showMessage("You have been disconnected from the server");
        }
    }

    public void serverAnswerUser(String text) {
        showMessage(text);
    }

    public void message(String text) {
        if (logIn) {
            if (!text.isEmpty())
                server.message(name + ": " + text);
        } else
            showMessage("No connection to server");
    }

    private void showMessage(String text) {
        clientView.appendLog(text + "\n");
    }
}
