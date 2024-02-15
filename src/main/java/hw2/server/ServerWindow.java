package hw2.server;

import hw2.repository.FileHandler;
import hw2.repository.Repository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ServerWindow extends JFrame implements Repository, ServerView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    JButton btnStart, btnStop;
    JTextArea log;

    public FileHandler fileHandler;
    public Server server;

    public ServerWindow() {
        settings();

        createPanel();

        setVisible(true);
    }

    private void settings() {
        server.usersList = new ArrayList<>();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
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

        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }

    private JButton createBtnStart() {
        JButton btnStart = new JButton("Start");

        btnStart.addActionListener(e -> serverStart());

        return btnStart;
    }

    private JButton createBtnStop() {
        JButton btnStop = new JButton("Stop");

        btnStop.addActionListener(e -> serverStop());

        return btnStop;
    }


    public void saveInfoInRepo(String text) {
        fileHandler.saveInfoInRepo(text);
    }

    public void loadInfoFromRepo() {
        fileHandler.loadInfoFromRepo();
    }

    public void serverStart() {
        server.serverStart();
    }

    public void serverStop() {
        server.serverStop();
    }

    public void appendLog(String text) {
        log.append(text);
    }
}
