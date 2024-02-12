package hw1.client;

import hw1.server.ServerWindow;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private final ServerWindow server;
    private boolean logIn;
    private String name;

    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField tfPassword;
    JButton btnLogin, btnSend;
    JPanel panelTop;

    public ClientGUI(ServerWindow server) {
        this.server = server;

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocationRelativeTo(this);

        createPanel();

        setVisible(true);
    }

    public void serverAnswerUser(String text) {
        appendLog(text);
    }

    private void connectionWithServer() {
        if (server.connectUser(this)) {
            appendLog("You have successfully connected\n");
            panelTop.setVisible(false);
            logIn = true;
            name = tfLogin.getText();
            String log = server.getLog();

            if (log != null) {
                appendLog(log);
            }
        } else {
            appendLog("Connection failed");
        }
    }

    public void disconnectFromServer() {
        if (logIn) {
            panelTop.setVisible(true);
            logIn = false;
            server.disconnectUser(this);
            appendLog("You have been disconnected from the server");
        }
    }

    public void message() {
        if (logIn) {
            String text = tfMessage.getText();

            if (!text.isEmpty()) {
                server.message(name + ": " + text);
                tfMessage.setText("");
            }
        } else {
            appendLog("No connection to server");
        }

    }

    private void appendLog(String text) {
        log.append(text + "\n");
    }

    private void createPanel() {
        add(createPanelTop(), BorderLayout.NORTH);
        add(createLog());
        add(createPanelBottom(), BorderLayout.SOUTH);
    }

    private Component createPanelTop() {
        panelTop = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        tfPassword = new JPasswordField("123456");

        btnLogin = new JButton("login");
        btnLogin.addActionListener(e -> connectionWithServer());

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(new JPanel());
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);

        return panelTop;
    }

    private Component createLog() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createPanelBottom() {
        JPanel panelBottom = new JPanel(new BorderLayout());

        tfMessage = new JTextField();
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    message();
                }
            }
        });

        btnSend = new JButton("send");
        btnSend.addActionListener(e -> message());

        panelBottom.add(tfMessage);
        panelBottom.add(btnSend, BorderLayout.EAST);

        return panelBottom;
    }

    @Override
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            disconnectFromServer();
        }

        super.processWindowEvent(e);
    }
}
