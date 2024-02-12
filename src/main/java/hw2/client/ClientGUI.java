package hw2.client;

import hw2.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class ClientGUI extends JFrame implements ClientView {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    JTextArea log;
    JTextField tfIPAddress, tfPort, tfLogin, tfMessage;
    JPasswordField tfPassword;
    JButton btnLogin, btnSend;
    JPanel panelTop;

    private Client client;

    public ClientGUI(ServerWindow server) {
        setting(server);

        createPanel();

        setVisible(true);
    }

    public void setting(ServerWindow server) {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setLocationRelativeTo(this);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        client = new Client(this, server.getConnection);
    }

    public void appendLog(String text) {
        log.append(text);
    }

    public void disconnectFromServer() {
        hidePanelTop(true);
        client.disconnectFromServer();
    }

    public void hidePanelTop(boolean visible) {
        panelTop.setVisible(visible);
    }
    public void connection() {
        if (client.connectionWithServer(tfLogin.getText()))
            panelTop.setVisible(false);
    }

    public void login() {
        if (client.connectionWithServer(tfLogin.getText()))
            panelTop.setVisible(false);
    }

    public void message() {
        client.message(tfMessage.getText());
        tfMessage.setText("");
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
        btnLogin.addActionListener(e -> login());

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
                if (e.getKeyChar() == '\n')
                    message();
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
