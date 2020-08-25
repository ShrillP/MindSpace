import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class SignUpScreen extends JFrame implements ActionListener {

    public static int index;

    public static ArrayList<User> users = new ArrayList<>();
    private static String[] tempArray;

    private Font font = new Font("Sylfaen", Font.BOLD, 80);
    private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    private JLabel screenTitle = new JLabel("MindSpace");
    private JLabel subSignUpMessage = new JLabel("Create an Account");
    private JLabel credentialLabel = new JLabel("Credentials");
    private JLabel time = new JLabel("How Much Time Spent...");

    private JButton signUp = new JButton("Sign-Up");

    private JLabel firstName = new JLabel("First Name:");
    private JLabel lastName = new JLabel("Last Name:");
    private JLabel username = new JLabel("Username:");
    private JLabel password = new JLabel("Password:");
    private JLabel working = new JLabel("Working:");
    private JLabel relaxing = new JLabel("Relaxing:");

    private JCheckBox checkBox = new JCheckBox("Show Password");

    private BufferedImage buttonIcon1 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button1 = new JButton(new ImageIcon(buttonIcon1));

    private BufferedImage buttonIcon2 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button2 = new JButton(new ImageIcon(buttonIcon2));

    private BufferedImage buttonIcon3 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button3 = new JButton(new ImageIcon(buttonIcon3));

    private BufferedImage buttonIcon4 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button4 = new JButton(new ImageIcon(buttonIcon4));

    private BufferedImage buttonIcon5 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button5 = new JButton(new ImageIcon(buttonIcon5));

    private BufferedImage buttonIcon6 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button6 = new JButton(new ImageIcon(buttonIcon6));

    private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
    private JButton back = new JButton(new ImageIcon(backIcon));

    private JTextField firstNameInput = new JTextField();
    private JTextField lastNameInput = new JTextField();
    private JTextField usernameInput = new JTextField();
    private JPasswordField passwordInput = new JPasswordField();
    private JTextField workingInput = new JTextField();
    private JTextField relaxingInput = new JTextField();

    public SignUpScreen() throws IOException {

        setLayout(null);

        setBounds(0,0,1100,700);
        this.setTitle("MindSpace: Sign-Up");

        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/backgrounds/signupBackground.png")))));

        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1100,700);
        repaint();

        back.setBorder(BorderFactory.createEmptyBorder());
        back.setContentAreaFilled(false);
        back.setBounds(10, 10, 100, 76);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Go back to the home screen.");

        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(328, 10, 445, 90);
        add(screenTitle);

        subSignUpMessage.setForeground(Color.BLACK);
        subSignUpMessage.setFont(font3);
        subSignUpMessage.setBounds(465, 75, 445, 90);
        add(subSignUpMessage);

        credentialLabel.setForeground(Color.BLACK);
        credentialLabel.setFont(font2);
        credentialLabel.setBounds(10, 150, 445, 90);
        add(credentialLabel);

        button1.setBorder(BorderFactory.createEmptyBorder());
        button1.setContentAreaFilled(false);
        button1.setBounds(10, 250, 30, 30);
        add(button1);
        button1.addActionListener(this);
        button1.setToolTipText("Enter your first name.");

        firstName.setForeground(Color.BLACK);
        firstName.setFont(font3);
        firstName.setBounds(50, 220, 150, 90);
        add(firstName);

        button2.setBorder(BorderFactory.createEmptyBorder());
        button2.setContentAreaFilled(false);
        button2.setBounds(10, 310, 30, 30);
        add(button2);
        button2.addActionListener(this);
        button2.setToolTipText("Enter your last name.");

        lastName.setForeground(Color.BLACK);
        lastName.setFont(font3);
        lastName.setBounds(50, 280, 150, 90);
        add(lastName);

        button3.setBorder(BorderFactory.createEmptyBorder());
        button3.setContentAreaFilled(false);
        button3.setBounds(10, 370, 30, 30);
        add(button3);
        button3.addActionListener(this);
        button3.setToolTipText("Enter your username name (name that will show up in the application).");

        username.setForeground(Color.BLACK);
        username.setFont(font3);
        username.setBounds(50, 340, 150, 90);
        add(username);

        button4.setBorder(BorderFactory.createEmptyBorder());
        button4.setContentAreaFilled(false);
        button4.setBounds(10, 430, 30, 30);
        add(button4);
        button4.addActionListener(this);
        button4.setToolTipText("Enter your password (try and have a combination of numbers, letter, and symbols).");

        password.setForeground(Color.BLACK);
        password.setFont(font3);
        password.setBounds(50, 400, 150, 90);
        add(password);

        button5.setBorder(BorderFactory.createEmptyBorder());
        button5.setContentAreaFilled(false);
        button5.setBounds(650, 250, 30, 30);
        add(button5);
        button5.addActionListener(this);
        button5.setToolTipText("Enter the number of hours spent working (integer value only).");

        button6.setBorder(BorderFactory.createEmptyBorder());
        button6.setContentAreaFilled(false);
        button6.setBounds(650, 310, 30, 30);
        add(button6);
        button6.addActionListener(this);
        button6.setToolTipText("Enter the number of hours spent relaxing (integer value only).");

        firstNameInput.setForeground(Color.BLACK);
        firstNameInput.setBounds(170, 245, 250, 40);
        TextFieldListener tfListener = new TextFieldListener();
        firstNameInput.addActionListener(tfListener);
        firstNameInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((Character.isAlphabetic(c)) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        add(firstNameInput);

        lastNameInput.setForeground(Color.BLACK);
        lastNameInput.setBounds(170, 305, 250, 40);
        TextFieldListener tf1Listener = new TextFieldListener();
        lastNameInput.addActionListener(tf1Listener);
        lastNameInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((Character.isAlphabetic(c)) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        add(lastNameInput);

        usernameInput.setForeground(Color.BLACK);
        usernameInput.setBounds(170, 365, 250, 40);
        add(usernameInput);
        TextFieldListener tf2Listener = new TextFieldListener();
        usernameInput.addActionListener(tf2Listener);

        passwordInput.setForeground(Color.BLACK);
        passwordInput.setEchoChar('*');
        passwordInput.setBounds(170, 425, 250, 40);
        add(passwordInput);
        TextFieldListener tf3Listener = new TextFieldListener();
        passwordInput.addActionListener(tf3Listener);

        checkBox.setFont(font3);
        checkBox.setBounds(180, 470, 200, 25);
        checkBox.addActionListener(this);
        add(checkBox);

        time.setForeground(Color.BLACK);
        time.setFont(font2);
        time.setBounds(650, 150, 500, 90);
        add(time);

        working.setForeground(Color.BLACK);
        working.setFont(font3);
        working.setBounds(690, 220, 150, 90);
        add(working);

        relaxing.setForeground(Color.BLACK);
        relaxing.setFont(font3);
        relaxing.setBounds(690, 280, 150, 90);
        add(relaxing);

        workingInput.setForeground(Color.BLACK);
        workingInput.setBounds(785, 245, 250, 40);
        add(workingInput);
        TextFieldListener tf4Listener = new TextFieldListener();
        workingInput.addActionListener(tf4Listener);
        workingInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        relaxingInput.setForeground(Color.BLACK);
        relaxingInput.setBounds(785, 305, 250, 40);
        add(relaxingInput);
        TextFieldListener tf5Listener = new TextFieldListener();
        relaxingInput.addActionListener(tf5Listener);
        relaxingInput.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)) ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE)) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        signUp.setFont(new Font("Segoe Script", Font.PLAIN, 40));
        signUp.setForeground(Color.BLACK);
        signUp.setBounds(820, 575, 250, 75);
        add(signUp);
        signUp.addActionListener(this);

        setVisible(true);
        repaint();

    }

    private boolean isGreaterThan24 (JTextField tf){

        if(Integer.parseInt(tf.getText()) > 24)
            return true;
        return false;

    }

    private boolean isGreaterThanDay (JTextField tf1, JTextField tf2){

        if(Integer.parseInt(tf1.getText()) + Integer.parseInt(tf2.getText()) > 24)
            return true;
        return false;

    }

    public void saveError(String description){
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize( new Dimension( 200, 50 ) );
        JOptionPane.showMessageDialog(textArea, scrollPane, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    private void save() {

        try {

            /* This logic is to create the file if the
             * file is not already present
             */
            if (!MindSpace_Test.userDataFile.exists()) {
                MindSpace_Test.userDataFile.createNewFile();
            }

            //Here true is to append the content to file
            FileWriter fWriter = new FileWriter(MindSpace_Test.userDataFile, true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            pWriter.write(firstNameInput.getText() + ",");
            pWriter.write(lastNameInput.getText() + ",");
            pWriter.write(usernameInput.getText() + ",");
            pWriter.write(passwordInput.getText() + ",");
            pWriter.write(workingInput.getText() + ",");
            pWriter.write(relaxingInput.getText() + ",");

            pWriter.println();

            //Closing PrintWriter Stream
            pWriter.close();

        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }

    public static void load(String fileName) throws IOException{

        index = 0;

        users.clear();

        Path path = Paths.get(fileName);

        Scanner scanner = new Scanner(path);

        while(scanner.hasNextLine()){

            String[] tempArray = scanner.nextLine().split(",");
            users.add(new User(tempArray[0], tempArray[1], tempArray[2], tempArray[3], Integer.parseInt(tempArray[4]), Integer.parseInt(tempArray[5])));

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == signUp){

            if(firstNameInput.getText().equals("") || lastNameInput.getText().
                    equals("") || usernameInput.getText().equals("") || passwordInput.
                    getText().equals("") || workingInput.getText().equals("") ||
                    relaxingInput.getText().equals("") || isGreaterThan24(workingInput)
                    || isGreaterThan24(relaxingInput) || isGreaterThanDay(workingInput, relaxingInput)) {

                saveError("Please check all fields for correct input. Hover over 'i' to find out more information.");

            }else{
                save();
                dispose();
                try {
                    load("res/Data Files/User_Data_File.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    new HomeScreen();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                System.out.println(users.get(0).toString());

            }
        }

        if(e.getSource() == checkBox){

            if(checkBox.isSelected() == true)
                passwordInput.setEchoChar((char)0);
            else
                passwordInput.setEchoChar('*');
        }

        if(e.getSource() == back){
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