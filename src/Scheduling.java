import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

public class Scheduling extends JFrame implements ActionListener {

    private int currentDay = 1;

    private JLabel screenTitle = new JLabel("Scheduling");
    private JLabel calanderTitle;

    private JLabel textAreaTitle = new JLabel("Personal To-Do List:");
    public static JTextArea textArea;
    private JScrollPane scrollPane;
    private JButton save = new JButton("Save");
    private JButton load = new JButton("Load");
    private JButton addNewEvent = new JButton("Add Tasks");

    private JPanel calanderDaysPanel = new JPanel(new GridLayout(6, 7));
    private JPanel calanderWeekDaysPanel = new JPanel(new GridLayout(1, 7));
    private String[] weekdays = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private Date currentDate = new Date();
    private int tempMonth = currentDate.getMonth() + 1;
    private int tempYear = currentDate.getYear() + 1900;

    private JButton[] changeMonth = new JButton[2];
    private JButton[] daysInMonth;

    private Font font = new Font("Sylfaen", Font.BOLD, 80);
    private Font font2 = new Font("Sylfaen", Font.PLAIN, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
    private JButton back = new JButton(new ImageIcon(backIcon));

    private BufferedImage infoIcon = ImageIO.read(new File("res/Images/info.png"));
    private JButton info = new JButton(new ImageIcon(infoIcon));

    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    private String[] daysTemp;

    private String[] toDoListFiles;

    public Scheduling() throws IOException {

        System.out.println(day);

        setLayout(null);
        setBounds(0, 0, 1100, 700);
        this.setTitle("MindSpace: Scheduling");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/backgrounds/schedulingBackground.png")))));

        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1100, 700);
        repaint();

        back.setBorder(BorderFactory.createEmptyBorder());
        back.setContentAreaFilled(false);
        back.setBounds(10, 10, 100, 76);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Go back to the home screen.");

        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(323, 10, 455, 95);
        add(screenTitle);

        info.setBorder(BorderFactory.createEmptyBorder());
        info.setContentAreaFilled(false);
        info.setBounds(1060, 10, 30, 30);
        add(info);
        info.addActionListener(this);
        info.setToolTipText("Click to learn how to use the screen.");

        textAreaTitle.setForeground(Color.BLACK);
        textAreaTitle.setFont(new Font("Sylfaen", Font.BOLD, 20));
        textAreaTitle.setBounds(20, 240, 250, 40);
        add(textAreaTitle);

        save.setFont(font2);
        save.setBounds(105, 600, 100, 45);
        add(save);
        save.addActionListener(this);
        save.setToolTipText("Save your changes!");

        load.setFont(font2);
        load.setBounds(225, 600, 100, 45);
        add(load);
        load.addActionListener(this);
        load.setToolTipText("Load previous entries!");

        addNewEvent.setFont(font2);
        addNewEvent.setBounds(90, 190, 250, 45);
        add(addNewEvent);
        addNewEvent.addActionListener(this);
        addNewEvent.setToolTipText("Save your changes!");

        changeMonth[0] = new JButton("<<<");
        changeMonth[0].setBounds(490, 160, 70, 30);
        add(changeMonth[0]);
        changeMonth[0].addActionListener(this);
        changeMonth[0].setToolTipText("Previous Month");

        changeMonth[1] = new JButton(">>>");
        changeMonth[1].setBounds(1000, 160, 70, 30);
        add(changeMonth[1]);
        changeMonth[1].addActionListener(this);
        changeMonth[1].setToolTipText("Next Month");

        makeCalander(tempYear, tempMonth);
        makeCalanderTitle(monthName(tempMonth), String.valueOf(tempYear));
        makeEditableTextArea();

        setVisible(true);
        repaint();

    }

    private void makeCalanderTitle(String month, String year) {

        calanderTitle = new JLabel(month + " " + year);
        calanderTitle.setForeground(Color.BLACK);
        calanderTitle.setFont(new Font("Sylfaen", Font.BOLD, 30));

        JPanel calanderTitleStorage = new JPanel();
        calanderTitleStorage.setBounds(566, 140, 428, 50);
        calanderTitleStorage.setOpaque(false);
        add(calanderTitleStorage);
        calanderTitleStorage.add(calanderTitle, SwingConstants.CENTER);

    }

    private void makeEditableTextArea() {

        textArea = new JTextArea();
        textArea.setEditable(true);

        scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        scrollPane.setBounds(20, 280, 400, 300);
        add(scrollPane);

    }

    private void makeCalander(int year, int month) {

        daysGenerator();

        YearMonth ym = YearMonth.of(year, month);

        int firstDay = ym.atDay(1).getDayOfWeek().getValue();

        calanderDaysPanel.setBounds(480, 250, 600, 400);
        calanderDaysPanel.setOpaque(false);
//        calanderDaysPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        if (firstDay == 7) {

            firstDay = 0;
            toDoListFiles = new String[daysTemp.length];
            daysInMonth = new JButton[daysTemp.length];

            for (int x = 0; x < daysTemp.length; x++) {

                daysInMonth[x] = new JButton(String.valueOf(x + 1));

                daysInMonth[x].addActionListener(this);

                calanderDaysPanel.add(daysInMonth[x]);
                calanderDaysPanel.setOpaque(false);

                toDoListFiles[x] = "res/ToDoFiles/" + String.valueOf(tempYear) + "_" + String.valueOf(tempMonth) + "_" + daysInMonth[x].getText() + ".txt";

            }

            for (int x = 0; x < 42 - daysTemp.length - firstDay; x++) {
                calanderDaysPanel.add(new JLabel(""));
                calanderDaysPanel.setOpaque(false);
            }

        } else {

            toDoListFiles = new String[daysTemp.length];
            daysInMonth = new JButton[daysTemp.length];

            for (int y = 0; y < firstDay; y++) {
                calanderDaysPanel.add(new JLabel(""));
            }

            for (int x = 0; x < daysTemp.length; x++) {

                daysInMonth[x] = new JButton(String.valueOf(x + 1));

                daysInMonth[x].addActionListener(this);

                if(daysInMonth[x].getText().equals(String.valueOf(day)) && currentDate.getMonth() + 1 == tempMonth){

                    daysInMonth[x].setBackground(Color.yellow);
                    daysInMonth[x].setOpaque(true);
                    daysInMonth[x].setBorderPainted(false);
                    daysInMonth[x].addActionListener(this);

                }

                calanderDaysPanel.add(daysInMonth[x]);
                calanderDaysPanel.setOpaque(false);

                toDoListFiles[x] = "res/ToDoFiles/" + String.valueOf(tempYear) + "_" + String.valueOf(tempMonth) + "_" + daysInMonth[x].getText() + ".txt";

                fileGeneration(new File(toDoListFiles[x]));

            }

            for (int x = 0; x < 42 - daysTemp.length - firstDay; x++) {
                calanderDaysPanel.add(new JLabel(""));
                calanderDaysPanel.setOpaque(false);
            }
        }

        add(calanderDaysPanel);

        calanderWeekDaysPanel.setBounds(480, 190, 600, 55);
        calanderWeekDaysPanel.setOpaque(false);

        for (int i = 0; i < 7; i++) {

            if (i == 0 || i == 6) {
                JLabel temp = new JLabel(weekdays[i], SwingConstants.CENTER);
                temp.setBackground(Color.PINK);
                temp.setOpaque(true);
                calanderWeekDaysPanel.add(temp);
            } else {
                JLabel temp = new JLabel(weekdays[i], SwingConstants.CENTER);
                temp.setBackground(Color.LIGHT_GRAY);
                temp.setOpaque(true);
                calanderWeekDaysPanel.add(temp);
            }
        }

//        JButton temp = (JButton) calanderDaysPanel.getComponent(day_of_month + 1);
//        temp.addActionListener(this);
//        temp.setOpaque(true);
//        temp.setBorderPainted(false);
//        temp.setBackground(Color.orange);

        add(calanderWeekDaysPanel);
    }

    private void fileWrite(String fileName) throws IOException {

        System.out.println(fileName);

        FileWriter write=null;
        try{
            write = new FileWriter (fileName);
            textArea.write(write);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            if(write != null)
                write.close();
        }
    }

    //Writes the related data to the associated file
    private void fileGeneration(File file) {

        try {

            /* This logic is to create the file if the
             * file is not already present
             */
            if (!file.exists()) {
                file.createNewFile();
            }

        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }


    private String monthName(int month) {

        String monthString = "";

        switch (month) {

            case 1:
                monthString = "January";
                break;

            case 2:
                monthString = "February";
                break;

            case 3:
                monthString = "March";
                break;

            case 4:
                monthString = "April";
                break;

            case 5:
                monthString = "May";
                break;

            case 6:
                monthString = "June";
                break;

            case 7:
                monthString = "July";
                break;

            case 8:
                monthString = "August";
                break;

            case 9:
                monthString = "September";
                break;

            case 10:
                monthString = "October";
                break;

            case 11:
                monthString = "November";
                break;

            case 12:
                monthString = "December";
                break;
        }

        return monthString;

    }

    //generates the days that corresponds to the month
    public String[] daysGenerator() {

//        int month = Calendar.getInstance().get(Calendar.MONTH);

        if ((tempMonth - 1) == 1) {
            if (isLeapYear())
                daysTemp = new String[29];
            else
                daysTemp = new String[28];
        } else if (bigMonth(((tempMonth - 1) + 1))) {
            daysTemp = new String[31];
        } else {
            daysTemp = new String[30];
        }

        for (int i = 1; i <= daysTemp.length; i++) {
            daysTemp[i - 1] = Integer.toString(i);
        }

        return daysTemp;
    }

    //determines what months have 31/30 days
    public boolean bigMonth(int month) {
        int[] arr = {1, 3, 5, 7, 8, 10, 12};
        for (int n : arr) {
            if (month == n) {
                return true;
            }
        }
        return false;
    }

    //Checks if the current year is a lea year
    public boolean isLeapYear() {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    return true;
                return false;
            }
            return true;
        }
        return false;
    }

    //pop up message when this error occurs
    public void instructions(String description) {
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        JOptionPane.showMessageDialog(textArea, scrollPane, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void writeToTextArea(String filePath) {

        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(filePath));
            String str;
            while ((str = buff.readLine()) != null) {
                textArea.append(str + "\n");

            }

        } catch (IOException e) {
        } finally {
            try {
                buff.close();
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == changeMonth[0]) {

            tempMonth--;

            if (tempMonth == 0) {

                tempMonth = 12;
                tempYear--;

            }

            calanderWeekDaysPanel.removeAll();
            calanderDaysPanel.removeAll();
            calanderTitle.setText("");
            makeCalanderTitle(monthName(tempMonth), String.valueOf(tempYear));
            makeCalander(tempYear, tempMonth);
            calanderDaysPanel.setVisible(true);
            repaint();
            revalidate();

        }

        if (e.getSource() == changeMonth[1]) {

            tempMonth++;

            if (tempMonth > 12) {

                tempMonth = 1;
                tempYear++;

            }

            calanderWeekDaysPanel.removeAll();
            calanderDaysPanel.removeAll();
            calanderTitle.setText("");
            makeCalanderTitle(monthName(tempMonth), String.valueOf(tempYear));
            makeCalander(tempYear, tempMonth);
            calanderDaysPanel.setVisible(true);
            repaint();
            revalidate();

        }

        for(int x = 0; x < daysInMonth.length; x++){

            if(e.getSource() == daysInMonth[x]){

                textArea.setText("");

                currentDay = x + 1;

                writeToTextArea("res/ToDOFiles/" + String.valueOf(tempYear) + "_" + String.valueOf(tempMonth) + "_" + daysInMonth[x].getText() + ".txt");

            }
        }

        if(e.getSource() == save){

            System.out.println(currentDay);

            try {
                fileWrite("res/ToDOFiles/" + String.valueOf(tempYear) + "_" + String.valueOf(tempMonth) + "_" + currentDay + ".txt");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            textArea.setText("");

        }

        if(e.getSource() == addNewEvent){

            dispose();
            try {
                new AddingTasks();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        if(e.getSource() == back){

            dispose();
            try {
                new Dashboard();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        if(e.getSource() == load){

            try {
                AddingTasks.save2();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        if(e.getSource() == info){

            instructions("This screen is meant to help you see what tasks you have in store for yourself each day." +
                    "To start off, you can either press any day on the calendar to view your tasks or by clicking any day on the calendar" +
                    "and then typing your personal notes for that day. To save what you write, you must click the save button. You can " +
                    "also add your own tasks by clicking 'Add Tasks'. To see what tasks you have to do each day, please click the load " +
                    "button. On the calendar the current day will be highlighted in yellow.");

        }
    }
}