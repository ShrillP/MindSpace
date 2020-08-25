import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HomeScreen extends JFrame implements ActionListener {

    private Font font = new Font("Sylfaen", Font.BOLD, 80);
    private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    private JLabel screenTitle = new JLabel("MindSpace");
    private JLabel signUpMessage = new JLabel("Need an account? Sign up here!");

    private JButton login = new JButton("Login");
    private JButton signUp = new JButton("Sign-Up");

    private JLabel img1 = new JLabel(new ImageIcon("res/Images/img1.png"));

    public HomeScreen() throws IOException {

        setLayout(null);

        setBounds(0,0,1100,700);
        this.setTitle("MindSpace");

        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/backgrounds/mainScreenBackground.png")))));

        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1100,700);
        repaint();

        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(328, 10, 445, 90);
        add(screenTitle);

        login.setFont(new Font("Segoe Script", Font.PLAIN, 40));
        login.setForeground(Color.BLACK);
        login.setBounds(625, 200, 250, 75);
        add(login);
        login.addActionListener(this);

        signUp.setFont(new Font("Segoe Script", Font.PLAIN, 40));
        signUp.setForeground(Color.BLACK);
        signUp.setBounds(625, 400, 250, 75);
        add(signUp);
        signUp.addActionListener(this);

        signUpMessage.setForeground(Color.BLACK);
        signUpMessage.setFont(font3);
        signUpMessage.setBounds(600, 320, 300, 35);
        add(signUpMessage);

        img1.setBounds(-50, -75, 800, 753);
        add(img1);

        setVisible(true);
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == login){

            dispose();

            try {
                new LoginScreen();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        if(e.getSource() == signUp){

            dispose();
            try {
                new SignUpScreen();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}