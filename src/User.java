/*
 *This is an object class that is used to create new users for the program. It consists of the name
 * of the person (first and last), username, passowrd, hours spent working and relaxing. This class includes a
 * constructor, getters and setters for all variables, and a toString method.
 */

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private int hoursWorking;
    private int hoursRelaxing;

    public User(String firstName, String lastName, String username, String password, int hoursWorking, int hoursRelaxing) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.hoursWorking = hoursWorking;
        this.hoursRelaxing = hoursRelaxing;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHoursWorking() {
        return hoursWorking;
    }

    public void setHoursWorking(int hoursWorking) {
        this.hoursWorking = hoursWorking;
    }

    public int getHoursRelaxing() {
        return hoursRelaxing;
    }

    public void setHoursRelaxing(int hoursRelaxing) {
        this.hoursRelaxing = hoursRelaxing;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hoursWorking=" + hoursWorking +
                ", hoursRelaxing=" + hoursRelaxing +
                '}';
    }
}