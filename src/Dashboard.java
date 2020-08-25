import javax.imageio.ImageIO;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Dashboard extends JFrame implements ActionListener {

    //Varibales for various fonts used through the GUI
    private Font font = new Font("Sylfaen", Font.BOLD, 80);
    private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    //Label used to place an image on the GUI
    private JLabel img1 = new JLabel(new ImageIcon("res/Images/Headspace.png"));

    //Variable to store the title of the screen
    private JLabel screenTitle = new JLabel("Dashboard");

    //A variable that store a specialized string (customized for each user)
    private JLabel subSignUpMessage = new JLabel("Welcome " + SignUpScreen.users.get(LoginScreen.currentUser).getFirstName() + "! How are you doing today?");

//   SignUpScreen.users.get(LoginScreen.currentUser).getFirstName()

    //Array of buttons (all button used on the screen)
    private JButton[] buttons = new JButton[3];

    //Constructor method used to develop the GUI
    public Dashboard() throws IOException {

        //Sets the inital frame layout
        setLayout(null);

        //Sets bounds of the frame and gives it a title
        setBounds(0,0,1100,700);
        this.setTitle("MindSpace: Dashboard");

        //Gives the GUI a background
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/backgrounds/dashboardBackground.jpg")))));

        //Prevents the frame from being resizeable and lets you close the program from the 'x' button
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1100,700);
        repaint();


        /*
         *All of the GUI variables initalized in the scope of the class are now placed
         * onto the frame. All labels were given a specific font, given a location on the screen,
         * and were made visible. All buttons were placed in their correct places.
         */

        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(325, 10, 450, 85);
        add(screenTitle);

        subSignUpMessage.setForeground(Color.BLACK);
        subSignUpMessage.setFont(font3);
        subSignUpMessage.setBounds(350, 75, 550, 90);
        add(subSignUpMessage);

        buttons[0] = new JButton("Tips");
        buttons[0].setFont(new Font("Segoe Script", Font.BOLD, 60));
        buttons[0].setForeground(Color.BLACK);
        buttons[0].setBounds(140, 375, 200, 90);
        add(buttons[0]);
        buttons[0].addActionListener(this);

        buttons[1] = new JButton("Scheduling");
        buttons[1].setFont(new Font("Segoe Script", Font.BOLD, 60));
        buttons[1].setForeground(Color.BLACK);
        buttons[1].setBounds(50, 200, 400, 90);
        add(buttons[1]);
        buttons[1].addActionListener(this);

        buttons[2] = new JButton("Meditation");
        buttons[2].setFont(new Font("Segoe Script", Font.BOLD, 60));
        buttons[2].setForeground(Color.BLACK);
        buttons[2].setBounds(50, 550, 400, 90);
        add(buttons[2]);
        buttons[2].addActionListener(this);

        img1.setBounds(650, 280, 275, 274);
        add(img1);

        setVisible(true);
        repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //When the meditation button is clicked
        if(e.getSource() == buttons[2]){

            //Close this frame
            dispose();

            //Open the new meditation screen
            try {
                new Meditation();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        //When the suggestions button is clicked
        if(e.getSource() == buttons[0]){

            //Close this frame
            dispose();

            //Open the new suggestions screen
            try {
                new Suggestions();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        //When the scheduling button is clicked
        if(e.getSource() == buttons[1]){

            //Close this frame
            dispose();

            //Open the new scheduling screen
            try {
                new Scheduling();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}