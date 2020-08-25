import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import com.michaelbaranov.microba.calendar.DatePicker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddingTasks extends JFrame implements ActionListener {

    //Varibales for various fonts used through the GUI
    private Font font = new Font("Sylfaen", Font.BOLD, 40);
    private Font font2 = new Font("Sylfaen", Font.PLAIN, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    //Label for the title of the screen
    private JLabel screenTitle = new JLabel("Task Input");

    //All the labels to describe the data entry points
    private JLabel[] informationLabels = new JLabel[4];

    /*
     *These button icons that are made into hoverable buttons do not have nay function, but
     * will provide the user with a message upon hivering on what type of data should be
     * entered.
     */
    private BufferedImage buttonIcon1 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button1 = new JButton(new ImageIcon(buttonIcon1));

    private BufferedImage buttonIcon2 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button2 = new JButton(new ImageIcon(buttonIcon2));

    private BufferedImage buttonIcon3 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button3 = new JButton(new ImageIcon(buttonIcon3));

    private BufferedImage buttonIcon4 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button4 = new JButton(new ImageIcon(buttonIcon4));

    //Panel created to store the date picker (allowing proper organization)
    private JPanel panel = new JPanel();
    private final DatePicker datepicker = new DatePicker(new Date(), new SimpleDateFormat("dd/M/yyyy"));

    //All inputs points for all the labels defined earlier
    private JTextField[] inputs = new JTextField[2];

    //Array of buttons to hold the save/close buttons
    private JButton[] operations = new JButton[2];

    //Variable to help add radio buttons to the screen
    private JRadioButton[] intensity = new JRadioButton[5];

    //Variable to store all radio buttons in a group
    private ButtonGroup intensityGroup = new ButtonGroup();

    //Variable to store all th eradio buttons on a single panel for greater organization
    private JPanel intensityPanel = new JPanel(new GridLayout(1, 5));

    //Array for store tasks that the user enters into the program
    public static ArrayList<CalanderEvents> events = new ArrayList<>();

    //Constructor method used to develop the GUI
    public AddingTasks() throws IOException {

        //Sets the inital frame layout
        setLayout(null);

        //Sets bounds of the frame
        setBounds(0, 0, 500, 450);

        //Gives the frame a title
        this.setTitle("MindSpace: Task Input");

        //Gives the frame a background image
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/backgrounds/addingTasksBackground.png")))));

        //Prevents the frame from being resizeable and lets you close the program from the 'x' button
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(500, 450);
        repaint();

        /*
         *All of the GUI variables initalized in the scope of the class are now placed
         * onto the frame. All labels were given a specific font, given a location on the screen,
         * and were made visible. All the input fields were given a specific space for entry and made
         * visible onto the screen. All panels and buttons were placed in there correct places.
         */

        intensityPanel.setBounds(200, 292, 250, 50);
        intensityPanel.setOpaque(false);

        for(int x = 0; x < intensity.length; x++){

            intensity[x] = new JRadioButton(String.valueOf(x + 1));
            intensityGroup.add(intensity[x]);
            intensityPanel.add(intensity[x]);

        }

        intensity[0].setSelected(true);
        add(intensityPanel);

        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(125, 10, 250, 50);
        add(screenTitle);

        button1.setBorder(BorderFactory.createEmptyBorder());
        button1.setContentAreaFilled(false);
        button1.setBounds(60, 90, 30, 30);
        add(button1);
        button1.addActionListener(this);
        button1.setToolTipText("Enter the name of your task.");

        informationLabels[0] = new JLabel("Name:");
        informationLabels[0].setForeground(Color.BLACK);
        informationLabels[0].setFont(font3);
        informationLabels[0].setBounds(110, 80, 250, 50);
        add(informationLabels[0]);

        inputs[0] = new JTextField(20);
        inputs[0].setBounds(180, 85, 250, 40);
        add(inputs[0]);
        TextFieldListener tf1Listener = new TextFieldListener();
        inputs[0].addActionListener(tf1Listener);

        button2.setBorder(BorderFactory.createEmptyBorder());
        button2.setContentAreaFilled(false);
        button2.setBounds(60, 160, 30, 30);
        add(button2);
        button2.addActionListener(this);
        button2.setToolTipText("Enter the hours required to complete task.");

        informationLabels[1] = new JLabel("Hours Required:");
        informationLabels[1].setForeground(Color.BLACK);
        informationLabels[1].setFont(font3);
        informationLabels[1].setBounds(110, 150, 300, 50);
        add(informationLabels[1]);

        inputs[1] = new JTextField(20);
        inputs[1].setBounds(275, 155, 155, 40);
        add(inputs[1]);
        TextFieldListener tf2Listener = new TextFieldListener();
        inputs[1].addActionListener(tf2Listener);

        //A block of code to prevent any non numeric values from being entered
        inputs[1].addKeyListener(new KeyAdapter() {
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

        button3.setBorder(BorderFactory.createEmptyBorder());
        button3.setContentAreaFilled(false);
        button3.setBounds(60, 230, 30, 30);
        add(button3);
        button3.addActionListener(this);
        button3.setToolTipText("Enter the add date of the task.");

        informationLabels[2] = new JLabel("Add Date:");
        informationLabels[2].setForeground(Color.BLACK);
        informationLabels[2].setFont(font3);
        informationLabels[2].setBounds(110, 220, 200, 50);
        add(informationLabels[2]);

        panel.setOpaque(false);
        datepicker.setFieldEditable(false);
        datepicker.addActionListener(this);
        panel.setBounds(170, 227, 200, 50);
        panel.add(datepicker);
        add(panel);

        button4.setBorder(BorderFactory.createEmptyBorder());
        button4.setContentAreaFilled(false);
        button4.setBounds(60, 300, 30, 30);
        add(button4);
        button4.addActionListener(this);
        button4.setToolTipText("Task difficulty (1 - easy, 5 - hard)..");

        informationLabels[3] = new JLabel("Intensity:");
        informationLabels[3].setForeground(Color.BLACK);
        informationLabels[3].setFont(font3);
        informationLabels[3].setBounds(110, 290, 200, 50);
        add(informationLabels[3]);

        operations[0] = new JButton("Save");
        operations[0].setFont(font2);
        operations[0].setBounds(120, 355, 100, 45);
        add(operations[0]);
        operations[0].addActionListener(this);

        operations[1] = new JButton("Close");
        operations[1].setFont(font2);
        operations[1].setBounds(260, 355, 100, 45);
        add(operations[1]);
        operations[1].addActionListener(this);

        //Sets all the components added onto the screen visible
        setVisible(true);
        repaint();

    }

    //A method to present the user with an error message when needed
    public void saveError(String description){
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize( new Dimension( 200, 50 ) );
        JOptionPane.showMessageDialog(textArea, scrollPane, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    //A method used to save all the details on a new task entery into a specified test file
    private void save() {

        try {

            /* This logic is to create the file if the
             * file is not already present
             */
            if (!MindSpace_Test.individualDataFile.exists()) {
                MindSpace_Test.individualDataFile.createNewFile();
            }

            //Here true is to append the content to file
            FileWriter fWriter = new FileWriter(MindSpace_Test.individualDataFile, true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            //Variable to hold the current day of the month
            String tempDay = String.valueOf(datepicker.getDateFormat().getCalendar().get(Calendar.DAY_OF_MONTH));

            //Statements to write the data from all entry points into a text file
            pWriter.write(inputs[0].getText() + ",");
            pWriter.write(inputs[1].getText() + ",");
            pWriter.write((datepicker.getDate().getYear() + 1900) + "_" + (datepicker.getDate().getMonth() + 1) + "_" + tempDay + ",");

            //Fetches the intensity of each task and writes it to the text file
            for(int x = 0; x < intensity.length; x++){

                if(intensity[x].isSelected()){

                    pWriter.write(intensity[x].getText() + ",");

                }
            }

            //Prints a new line at the end of the text file
            pWriter.println();

            //Closing PrintWriter Stream
            pWriter.close();

        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }

    //Another save method for saving the name of a task inputed using this GUI into the calendar
    public static void save2() throws IOException {

        //Scans through the whole array of tasks that the user inputed
        for(int x = 0; x < events.size(); x++){

            //Creates a temporary file name to base the saving off of
            String tempFileName = ("res/ToDoFiles/" + events.get(x).getDuedatae() + ".txt");

            Files.write(Paths.get(tempFileName), ("\n" + events.get(x).getName()).getBytes(), StandardOpenOption.APPEND);

        }
    }

    //A method to load all the data into an array list of Calander Events objects
    private void load(String fileName) throws IOException {

        //Variables to help load in the data
        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);
        String[] tempArr;

        //Until there is no next line in the text file
        while(scanner.hasNextLine()){

            //A variable to hold the current line of String
            String line = scanner.nextLine();

            //Array that holds the line of String split where there is a comma
            tempArr = line.split(",");

            //Adds each piece of information into an object in the object array
            events.add(new CalanderEvents(tempArr[0], Integer.parseInt(tempArr[1]), tempArr[2], Integer.parseInt(tempArr[3])));

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //When the close button is pressed
        if(e.getSource() == operations[1]) {

            //Close the current screen
            dispose();

            //Open up the scheduling screen
            try {
                new Scheduling();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        //When the save button is presed
        if(e.getSource() == operations[0]){

            //Checks if all the fieds have a vaild input that exists in the fields
            if(inputs[0].getText().equals("") || inputs[1].getText().equals("")){

                //If they are empty, gives them this error messade
                saveError("Please check all fields for correct input. Hover over 'i' to find out more information.");

            } else{

                //Otherwise save the event
                save();

                //New intance of the comparator class
                CMP cmp = new CMP();

                //Sorts the events array list by their intensities
                Collections.sort(events,cmp);

                try {

                    //Loads in all the data in the user's personal files
                    load(MindSpace_Test.individualDataFile.getAbsolutePath());

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //Resets all the fields (for new entries)
                inputs[0].setText("");
                inputs[1].setText("");
                intensity[0].setSelected(true);

            }
        }
    }

    public static class TextFieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

//A comparator class to help organize the events in my object array list by their intensities
class CMP implements Comparator<CalanderEvents> {

    @Override
    public int compare(CalanderEvents o1, CalanderEvents o2) {
        return Integer.compare(o2.getIntensityOfTask(), o1.getIntensityOfTask());
    }
}
