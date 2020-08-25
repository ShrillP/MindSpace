import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/*
 *  Name:
 *  - Shrill Patel
 *
 *  Date:
 *  - January 18, 2019
 *
 *  Course Code and Teacher:
 *  -ISC4U1
 *  -Mr.Fernandes
 *
 *  Title:
 *  - MnindSpace
 *
 *  Description
 *  - This is an application that will help its users reduce whatever type of mental stress that
 *    they might have. The application does this by having various ways to help the user with
 *    their mental stress (e.g. meditation, a scheduler for their tasks, and a suggestion place
 *    where they can take a look at reputable sources and get information). It helps the user
 *    meditate to the pre-loaded files that are on the program which are controlable. The program
 *    also allow the user to seek for suggestions if they need help on various ways to decrease the
 *    effects of their mental stress. The program also has a task manager that will keep track of
 *    what they need to do. The piece of software is able to have multiple users at teh same time.
 *
 *  Features:
 *  - The calendar that is on the suggestions screen
 *      - it starts on the correct day of the week and has the correct amount
 *        of days in that month
 *      - also has a block that is highlighted to incdicate the current day
 *
 *  - Google searching
 *      - the program has the ability to search for ways to reduce the users mental
 *        stress based on what button they clicked.
 *
 *  - Task management
 *      - how the program allows the user to write their own personal things, but also
 *        allows them to enter a specified task for that day and saves all of it
 *
 *  - Login Screen
 *      - allows the use of multiple users
 *
 *  - Sign-up Screen
 *      - allows for the program to have multiple working users
 *
 *  - Personalized messages based on the name of the current user
 *
 *  Major Skills:
 *  - Control structures
 *  - Loops and other conditional statements
 *  - Methods and Classes
 *  - Comparators
 *  - Data structures (various types)
 *  - Used a lot of other Java classes
 *
 *  Areas of Concern:
 *  - The program will add the tasks to the appropriate day on the calendar, organize all
 *    the takss they enter based on their intensities, but might not be able to suggest
 *    to the users when to do the tasks and how much time to give it.
 *
 *  - The personalized message on the dashboard screen will sometimes not be centered as it
 *    takes teh name of the current user and then makes the string.
 */

public class MindSpace_Test {

    public static File userDataFile = new File("res/Data Files/User_Data_File.txt");
    public static File individualDataFile = new File("res/Data Files/User_" + String.valueOf(LoginScreen.currentUser) + ".txt");
    public static String songName = "anxiety";
    public static String suggestionFileName = "";

    public static void main(String[] args) throws IOException {

//        new HomeScreen();
//        new LoginScreen();
//        new SignUpScreen();
//        new Meditation();
        new Dashboard();
//        new Suggestions();
//        new Scheduling();
//        new AddingTasks();

    }
}