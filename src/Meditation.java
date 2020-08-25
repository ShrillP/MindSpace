import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Meditation extends JFrame implements ActionListener {

    private Font font = new Font("Sylfaen", Font.BOLD, 80);
    private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    private BufferedImage playButtonIcon = ImageIO.read(new File("res/Images/playButton.png"));
    private JButton playButton = new JButton(new ImageIcon(playButtonIcon));

    private BufferedImage pauseButtonIcon = ImageIO.read(new File("res/Images/pauseButton.png"));
    private JButton pauseButton = new JButton(new ImageIcon(pauseButtonIcon));

    private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
    private JButton back = new JButton(new ImageIcon(backIcon));

    private BufferedImage infoIcon = ImageIO.read(new File("res/Images/info.png"));
    private JButton info = new JButton(new ImageIcon(infoIcon));

    private JLabel screenTitle = new JLabel("Meditation");
    private JLabel subSignUpMessage = new JLabel("What would you like help with today, " + "Benjamin?");

    private JButton anxiety = new JButton("Anxiety");
    private JButton stress = new JButton("Stress");
    private JButton sadness = new JButton("Sadness");
    private JButton focus = new JButton("Focus");
    private JButton anger = new JButton("Anger");
    private JButton other = new JButton("Other");

    private Clip clip;
    private long clipTime;
    private boolean isPlaying = false;

    public Meditation() throws IOException {

        setLayout(null);
        setBounds(0, 0, 1100, 700);
        this.setTitle("MindSpace: Meditation");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/backgrounds/meditationBackground.jpg")))));

        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1100, 700);
        repaint();

        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(330, 10, 440, 85);
        add(screenTitle);

        playButton.setBorder(BorderFactory.createEmptyBorder());
        playButton.setContentAreaFilled(false);
        playButton.setBounds(485, 250, 130, 130);
        add(playButton);
        playButton.addActionListener(this);
        playButton.setToolTipText("Press to play the selected track.");

        pauseButton.setBorder(BorderFactory.createEmptyBorder());
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBounds(485, 400, 130, 130);
        add(pauseButton);
        pauseButton.addActionListener(this);
        pauseButton.setToolTipText("Press to pause the selected track.");

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
        info.setToolTipText("Click for further information.");

        subSignUpMessage.setForeground(Color.BLACK);
        subSignUpMessage.setFont(font3);
        subSignUpMessage.setBounds(328, 75, 550, 90);
        add(subSignUpMessage);

        anxiety.setFont(new Font("Segoe Script", Font.BOLD, 60));
        anxiety.setForeground(Color.BLACK);
        anxiety.setBounds(50, 200, 300, 90);
        add(anxiety);
        anxiety.addActionListener(this);

        stress.setFont(new Font("Segoe Script", Font.BOLD, 60));
        stress.setForeground(Color.BLACK);
        stress.setBounds(50, 350, 300, 90);
        add(stress);
        stress.addActionListener(this);

        sadness.setFont(new Font("Segoe Script", Font.BOLD, 60));
        sadness.setForeground(Color.BLACK);
        sadness.setBounds(50, 500, 300, 90);
        add(sadness);
        sadness.addActionListener(this);

        focus.setFont(new Font("Segoe Script", Font.BOLD, 60));
        focus.setForeground(Color.BLACK);
        focus.setBounds(750, 200, 300, 90);
        add(focus);
        focus.addActionListener(this);

        anger.setFont(new Font("Segoe Script", Font.BOLD, 60));
        anger.setForeground(Color.BLACK);
        anger.setBounds(750, 350, 300, 90);
        add(anger);
        anger.addActionListener(this);

        other.setFont(new Font("Segoe Script", Font.BOLD, 60));
        other.setForeground(Color.BLACK);
        other.setBounds(750, 500, 300, 90);
        add(other);
        other.addActionListener(this);

        setVisible(true);
        repaint();

    }

    public void playMeditationMusic () {

        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/Meditation/"+ MindSpace_Test.songName + ".wav").getAbsoluteFile());		//imports the sound file
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setMicrosecondPosition(clipTime);
            clip.start();

        } catch(Exception  ex) {
            System.out.println("Error with playing sound");

        }
    }

    public void moreInformation(String description) {
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        JOptionPane.showMessageDialog(textArea, scrollPane, "More Information", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == anxiety){

            MindSpace_Test.songName = "anxiety";

        }

        if(e.getSource() == stress){

            MindSpace_Test.songName = "stress";

        }

        if(e.getSource() == sadness){

            MindSpace_Test.songName = "sadness";

        }

        if(e.getSource() == focus){

            MindSpace_Test.songName = "focus";

        }

        if(e.getSource() == anger){

            MindSpace_Test.songName = "anger";

        }

        if(e.getSource() == other){

            MindSpace_Test.songName = "other";

        }

        if(e.getSource() == playButton){

            playMeditationMusic();
            isPlaying = true;

        }

        if(isPlaying == true && (e.getSource() == anxiety || e.getSource() == stress || e.getSource() == sadness || e.getSource()  == focus || e.getSource() == anger || e.getSource() == other)) {
            clip.stop();
        }

        if(e.getSource() == pauseButton) {

            clipTime = clip.getMicrosecondPosition();
            clip.stop();

        }

        if(e.getSource() == info){
            moreInformation("To get started, click an option to meditate for. Once you have selected, press the play button to begin" +
                    "your meditation. Feel free to pause anytime to take a break (you will start where you left off). To change options, click on" +
                    "any other meditation option and press the play button.");
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
}