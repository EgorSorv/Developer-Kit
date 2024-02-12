package hw2.server;

import hw2.client.Client;
import hw2.repository.FileHandler;

import java.util.List;

public class Server {
    private final ServerView serverView;
    private final FileHandler fileHandler;
    private boolean isServerWorking;
    public List<Client> usersList;

    public Server(ServerView serverView, FileHandler fileHandler, List<Client> usersList) {
        this.serverView = serverView;
        this.fileHandler = fileHandler;
        this.usersList = usersList;
    }

    public void serverStart() {
        if (isServerWorking)
            showMessage("The server has already been started\n");
        else {
            isServerWorking = true;
            showMessage("The server is started\n");
        }
    }

    public void serverStop() {
        if (!isServerWorking)
            showMessage("The server has already been stopped\n");
        else {
            isServerWorking = false;
            while (!usersList.isEmpty()) disconnectUser(usersList.getLast());
            showMessage("The server is stopped\n");
        }
    }

    public boolean connectUser(Client user) {
        if (!isServerWorking)
            return false;

        usersList.add(user);

        return true;
    }

    public void disconnectUser(Client user) {
        usersList.remove(user);

        if (user != null)
            user.disconnectFromServer();
    }

    private void serverAnswerAllUsers(String text) {
        for (Client user: usersList)
            user.serverAnswerUser(text);
    }

    public void message(String text) {
        if (!isServerWorking) return;

        text += "";
        showMessage(text);
        serverAnswerAllUsers(text);
        fileHandler.saveInfoInRepo(text);
    }

    private void showMessage(String text) {
        serverView.appendLog(text + "\n");
    }

    public String getLog() {
        return fileHandler.loadInfoFromRepo();
    }
}
