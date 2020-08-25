import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginScreen extends JFrame implements ActionListener {

    public static int currentUser = 1;

    //an image icon responsible for backtracking to the previous sceen
    private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
    private JButton back = new JButton(new ImageIcon(backIcon));

    //All the labels
    private JLabel screenTitle = new JLabel("MindSpace");
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JCheckBox checkBox = new JCheckBox("Show Password");

    private BufferedImage buttonIcon1 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button1 = new JButton(new ImageIcon(buttonIcon1));

    private BufferedImage buttonIcon2 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button2 = new JButton(new ImageIcon(buttonIcon2));

    private JTextField username;
    private JPasswordField password;

    private Font font = new Font("Sylfaen", Font.BOLD, 80);
    private Font font2 = new Font("Sylfaen", Font.PLAIN, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    private JButton login = new JButton("Login");

    public LoginScreen() throws IOException {

        setLayout(null);
        setBounds(0, 0, 1100, 700);
        this.setTitle("MindSpace: Login");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/backgrounds/loginBackground.jpeg")))));

        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1100, 700);
        repaint();

        button1.setBorder(BorderFactory.createEmptyBorder());
        button1.setContentAreaFilled(false);
        button1.setBounds(265, 152, 30, 30);
        add(button1);
        button1.addActionListener(this);
        button1.setToolTipText("Enter your username.");

        button2.setBorder(BorderFactory.createEmptyBorder());
        button2.setContentAreaFilled(false);
        button2.setBounds(265, 233, 30, 30);
        add(button2);
        button2.addActionListener(this);
        button2.setToolTipText("Enter your password.");

        back.setBorder(BorderFactory.createEmptyBorder());
        back.setContentAreaFilled(false);
        back.setBounds(10, 590, 100, 76);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Go back to the home screen.");

        screenTitle.setForeground(Color.LIGHT_GRAY);
        screenTitle.setFont(font);
        screenTitle.setBounds(328, 10, 450, 85);
        add(screenTitle);

        usernameLabel.setForeground(Color.LIGHT_GRAY);
        usernameLabel.setFont(font2);
        usernameLabel.setBounds(310, 150, 200, 35);
        add(usernameLabel);

        username = new JTextField(20);
        username.setBounds(480, 150, 250, 40);
        add(username);
        TextFieldListener tf1Listener = new TextFieldListener();
        username.addActionListener(tf1Listener);

        passwordLabel.setForeground(Color.LIGHT_GRAY);
        passwordLabel.setFont(font2);
        passwordLabel.setBounds(315, 230, 200, 35);
        add(passwordLabel);

        password  = new JPasswordField(20);
        password.setEchoChar('*');
        password.setBounds(480, 230, 250, 40);
        add(password);
        TextFieldListener tf2Listener = new TextFieldListener();
        password.addActionListener(tf2Listener);

        login.setOpaque(true);
        login.setBorderPainted(false);
        login.setFont(new Font("Segoe Script", Font.PLAIN, 30));
        login.setForeground(Color.BLACK);
        login.setBounds(400, 350, 200, 50);
        add(login);
        login.addActionListener(this);

        checkBox.setForeground(Color.LIGHT_GRAY);
        checkBox.setFont(font3);
        checkBox.setBounds(490, 290, 200, 25);
        checkBox.addActionListener(this);
        add(checkBox);

        setVisible(true);

    }

    private boolean validateCredentials() throws IOException {

        SignUpScreen.load(MindSpace_Test.userDataFile.toString());

        String tempUsername = username.getText();
        String tempPassword = password.getText();

        for(int x = 0; x < SignUpScreen.users.size(); x++){

            if(tempUsername.equals(SignUpScreen.users.get(x).getUsername()) && tempPassword.equals(SignUpScreen.users.get(x).getPassword())) {
                currentUser = x;
                return true;
            }

        }
        return false;
    }

    public void loginError(String description) {
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(300, 50));
        JOptionPane.showMessageDialog(textArea, scrollPane, "Login Error", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == login){

            try {

                if (validateCredentials() == true) {

                    login.setBackground(Color.green);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {

                                    login.setBackground(Color.WHITE);

                                    dispose();

                                    try {
                                        new Dashboard();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            },
                            1000
                    );

                } else {

                    login.setBackground(Color.red);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {

                                    login.setBackground(Color.WHITE);

                                }
                            },
                            1000
                    );

                    loginError("Username and/or password is incorrect!");

                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource() == checkBox){

            if(checkBox.isSelected() == true)
                password.setEchoChar((char)0);
            else
                password.setEchoChar('*');
        }

        if(e.getSource() == back) {

            dispose();

            try {
                new HomeScreen();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    public static class TextFieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}