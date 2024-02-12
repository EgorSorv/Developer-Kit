package hw2.server;

import hw2.client.ClientGUI;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final String fileLogs = "src/main/java/hw1/server/log.txt";

    List<ClientGUI> usersList;

    JButton btnStart, btnStop;
    JTextArea log;
    boolean isServerWorking;

    public ServerWindow() {
        usersList = new ArrayList<>();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }

    public boolean connectUser(ClientGUI user){
        if (!isServerWorking)
            return false;

        usersList.add(user);

        return true;
    }

    public String getLog() {
        return readLogsFromFile();
    }

    public void disconnectUser(ClientGUI user) {
        usersList.remove(user);

        if (user != null)
            user.disconnectFromServer();
    }

    public void message(String text) {
        if (!isServerWorking) return;

        text += "";
        appendLog(text);
        serverAnswerAllUsers(text);
        saveLogsInFile(text);
    }

    private void serverAnswerAllUsers(String text) {
        for (ClientGUI user: usersList)
            user.serverAnswerUser(text);
    }

    private void saveLogsInFile(String text) {
        try (FileWriter writer = new FileWriter(fileLogs, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String readLogsFromFile() {
        try {
            return FileUtils.readFileToString(new File("src/main/java/hw1/server/log.txt"),
                    String.valueOf(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = createBtnStart();
        btnStop = createBtnStop();

        btnStop.addActionListener(e -> {
            if (!isServerWorking){
                appendLog("The server has already been stopped");
            } else {
                isServerWorking = false;
                while (!usersList.isEmpty()){
                    disconnectUser(usersList.getLast());
                }
                appendLog("The server is stopped");
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    private JButton createBtnStart() {
        JButton btnStart = new JButton("Start");

        btnStart.addActionListener(e -> {
            if (isServerWorking)
                log.append("The server has already been started\n");
            else {
                isServerWorking = true;
                log.append("The server is started\n");
            }
        });

        return btnStart;
    }

    private JButton createBtnStop() {
        JButton btnStop = new JButton("Stop");

        btnStop.addActionListener(e -> {
            if (!isServerWorking)
                log.append("The server has already been stopped\n");
            else {
                isServerWorking = false;
                while (!usersList.isEmpty()) disconnectUser(usersList.getLast());
                log.append("The server is stopped\n");
            }
        });

        return btnStop;
    }
}
