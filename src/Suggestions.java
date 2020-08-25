import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Suggestions extends JFrame implements ActionListener, HyperlinkListener {

    private Font font = new Font("Sylfaen", Font.BOLD, 80);
    private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    private JLabel screenTitle = new JLabel("Suggestions");
    private JLabel subSignUpMessage = new JLabel("Find quick and easy tips on how to relieve your mental stress!");

    private JButton anxiety = new JButton("Anxiety");
    private JButton stress = new JButton("Stress");
    private JButton sadness = new JButton("Sadness");
    private JButton focus = new JButton("Focus");
    private JButton anger = new JButton("Anger");
    private JButton other = new JButton("Other");
    private JButton[] links = new JButton[5];

    private JTextArea textArea = new JTextArea(0,100);
    private JScrollPane scrollPane;

    private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
    private JButton back = new JButton(new ImageIcon(backIcon));

    private BufferedImage infoIcon = ImageIO.read(new File("res/Images/info.png"));
    private JButton info = new JButton(new ImageIcon(infoIcon));

    private JLabel moreInfo = new JLabel("<html><div style='text-align:center;'>" + "To find more info, click any if these links:" + "</div></html>");

    public Suggestions () throws IOException {

        setLayout(null);

        setBounds(0,0,1100,700);
        this.setTitle("MindSpace: Suggestions");

        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/backgrounds/suggestionsBackground.jpg")))));

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

        info.setBorder(BorderFactory.createEmptyBorder());
        info.setContentAreaFilled(false);
        info.setBounds(1060, 10, 30, 30);
        add(info);
        info.addActionListener(this);
        info.setToolTipText("Click to learn how to use the screen.");

        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(297, 10, 505, 95);
        add(screenTitle);

        moreInfo.setForeground(Color.BLACK);
        moreInfo.setHorizontalAlignment(JLabel.CENTER);
        moreInfo.setVerticalAlignment(JLabel.CENTER);
        moreInfo.setFont(new Font("Sylfaen", Font.BOLD, 20));
        moreInfo.setBounds(885, 150, 200, 100);
        add(moreInfo);

        subSignUpMessage.setForeground(Color.BLACK);
        subSignUpMessage.setFont(font3);
        subSignUpMessage.setBounds(255, 75, 590, 90);
        add(subSignUpMessage);

        anxiety.setFont(new Font("Segoe Script", Font.BOLD, 40));
        anxiety.setForeground(Color.BLACK);
        anxiety.setBounds(50, 170, 200, 60);
        add(anxiety);
        anxiety.addActionListener(this);

        stress.setFont(new Font("Segoe Script", Font.BOLD, 40));
        stress.setForeground(Color.BLACK);
        stress.setBounds(50, 245, 200, 60);
        add(stress);
        stress.addActionListener(this);

        sadness.setFont(new Font("Segoe Script", Font.BOLD, 40));
        sadness.setForeground(Color.BLACK);
        sadness.setBounds(50, 320, 200, 60);
        add(sadness);
        sadness.addActionListener(this);

        focus.setFont(new Font("Segoe Script", Font.BOLD, 40));
        focus.setForeground(Color.BLACK);
        focus.setBounds(50, 395, 200, 60);
        add(focus);
        focus.addActionListener(this);

        anger.setFont(new Font("Segoe Script", Font.BOLD, 40));
        anger.setForeground(Color.BLACK);
        anger.setBounds(50, 470, 200, 60);
        add(anger);
        anger.addActionListener(this);

        other.setFont(new Font("Segoe Script", Font.BOLD, 40));
        other.setForeground(Color.BLACK);
        other.setBounds(50, 545, 200, 60);
        add(other);
        other.addActionListener(this);

        links[0] = new JButton("1");
        links[0].setFont(new Font("Segoe Script", Font.BOLD, 40));
        links[0].setForeground(Color.BLACK);
        links[0].setBounds(960, 250, 50, 50);
        add(links[0]);
        links[0].addActionListener(this);

        links[1] = new JButton("2");
        links[1].setFont(new Font("Segoe Script", Font.BOLD, 40));
        links[1].setForeground(Color.BLACK);
        links[1].setBounds(960, 310, 50, 50);
        add(links[1]);
        links[1].addActionListener(this);

        links[2] = new JButton("3");
        links[2].setFont(new Font("Segoe Script", Font.BOLD, 40));
        links[2].setForeground(Color.BLACK);
        links[2].setBounds(960, 370, 50, 50);
        add(links[2]);
        links[2].addActionListener(this);

        links[3] = new JButton("4");
        links[3].setFont(new Font("Segoe Script", Font.BOLD, 40));
        links[3].setForeground(Color.BLACK);
        links[3].setBounds(960, 430, 50, 50);
        add(links[3]);
        links[3].addActionListener(this);

        links[4] = new JButton("5");
        links[4].setFont(new Font("Segoe Script", Font.BOLD, 40));
        links[4].setForeground(Color.BLACK);
        links[4].setBounds(960, 490, 50, 50);
        add(links[4]);
        links[4].addActionListener(this);

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        scrollPane.setBounds(275,170,600,435);
        add(scrollPane);

        setVisible(true);
        repaint();
    }

    public void learnHowToUseScreen(String description){
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize( new Dimension( 200, 100 ) );
        JOptionPane.showMessageDialog(textArea, scrollPane, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    private void writeToTextArea (String filePath){

        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new FileReader(filePath));
            String str;
            while ((str = buff.readLine()) != null) {
                textArea.append(str + "\n");
            }
        } catch (IOException e) {
        } finally {
            try { buff.close(); } catch (Exception ex) { }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == anxiety){

            textArea.setText("");

            MindSpace_Test.suggestionFileName = "Anxiety";

            writeToTextArea("res/Suggestion_Files/" + MindSpace_Test.suggestionFileName + ".txt");

        }

        if(e.getSource() == stress){

            textArea.setText("");

            MindSpace_Test.suggestionFileName = "Stress";

            writeToTextArea("res/Suggestion_Files/" + MindSpace_Test.suggestionFileName + ".txt");

        }

        if(e.getSource() == sadness){

            textArea.setText("");

            MindSpace_Test.suggestionFileName = "Sadness";

            writeToTextArea("res/Suggestion_Files/" + MindSpace_Test.suggestionFileName + ".txt");

        }

        if(e.getSource() == focus){

            textArea.setText("");

            MindSpace_Test.suggestionFileName = "Focus";

            writeToTextArea("res/Suggestion_Files/" + MindSpace_Test.suggestionFileName + ".txt");

        }

        if(e.getSource() == anger){

            textArea.setText("");

            MindSpace_Test.suggestionFileName = "Anger";

            writeToTextArea("res/Suggestion_Files/" + MindSpace_Test.suggestionFileName + ".txt");

        }

        if(e.getSource() == other){

            textArea.setText("");

            MindSpace_Test.suggestionFileName = "Other";

            writeToTextArea("res/Suggestion_Files/" + MindSpace_Test.suggestionFileName + ".txt");

        }

        if(e.getSource() == links[0]){

//            System.out.println(MindSpace_Test.suggestionFileName);

            GoogleSearch search;
            String url;

            try {

                search = new GoogleSearch();

                url = search.filteredUrls.get(0);

                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));

                search.urls.clear();
                search.filteredUrls.clear();

            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource() == links[1]){

//            System.out.println(MindSpace_Test.suggestionFileName);

            GoogleSearch search;
            String url;

            try {

                search = new GoogleSearch();

                url = search.filteredUrls.get(1);

                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));

                search.urls.clear();
                search.filteredUrls.clear();

            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource() == links[2]){

//            System.out.println(MindSpace_Test.suggestionFileName);

            GoogleSearch search;
            String url;

            try {

                search = new GoogleSearch();

                url = search.filteredUrls.get(2);

                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));

                search.urls.clear();
                search.filteredUrls.clear();

            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource() == links[3]){

//            System.out.println(MindSpace_Test.suggestionFileName);

            GoogleSearch search;
            String url;

            try {

                search = new GoogleSearch();

                url = search.filteredUrls.get(3);

                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));

                search.urls.clear();
                search.filteredUrls.clear();

            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource() == links[4]){

//            System.out.println(MindSpace_Test.suggestionFileName);

            GoogleSearch search;
            String url;

            try {

                search = new GoogleSearch();

                url = search.filteredUrls.get(4);

                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));

                search.urls.clear();
                search.filteredUrls.clear();

            } catch (IOException | URISyntaxException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource() == info){

            learnHowToUseScreen("This screen provides preliminary information on how to handle " +
                    "common mental health issues. To figure out ways onto how to alleviate these problems " +
                    "click on the button on the side and read the respective passage on the right. To find " +
                    "out more information for your selected mental health issue, click the 'Find More Information" +
                    " Button'.");

        }

        if(e.getSource() == back){

            dispose();

            try {
                new Dashboard();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent e) {

    }
}