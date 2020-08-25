/*
 *This is an object class that is used to create new events/ tasks for the user to input on the
 * calender. It consists of the nae of the task, day that they want to input the task, the hours
 * that are required to complete the task, and the intensity of that task. This class includes a
 * constructor, getters and setters for all variables, and a toString method.
 */

public class CalanderEvents {

    private String name;
    private String duedatae;
    private int hoursRequired;
    private int intensityOfTask;

    public CalanderEvents(String name, int hoursRequired, String duedatae, int intensityOfTask) {
        this.name = name;
        this.duedatae = duedatae;
        this.hoursRequired = hoursRequired;
        this.intensityOfTask = intensityOfTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuedatae() {
        return duedatae;
    }

    public void setDuedatae(String duedatae) {
        this.duedatae = duedatae;
    }

    public int getHoursRequired() {
        return hoursRequired;
    }

    public void setHoursRequired(int hoursRequired) {
        this.hoursRequired = hoursRequired;
    }

    public int getIntensityOfTask() {
        return intensityOfTask;
    }

    public void setIntensityOfTask(int intensityOfTask) {
        this.intensityOfTask = intensityOfTask;
    }

    @Override
    public String toString() {
        return "CalanderEvents{" +
                "name='" + name + '\'' +
                ", duedatae='" + duedatae + '\'' +
                ", hoursRequired=" + hoursRequired +
                ", intensityOfTask=" + intensityOfTask +
                '}';
    }
}
