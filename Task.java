/**
 * Task class represents a task with associated priority, type, waiting time,
 * hour created, and description.
 * Tasks are comparable based on their priority and hour created.
 * Implements TaskInterface and Comparable<Task>.
 *
 * @author Alex Taylor
 * @version CS321 Data Structures Sp-24
 */
public class Task implements TaskInterface, Comparable<Task> {

    private int priority;
    private TaskType taskType;
    private int waitTime;
    private int hourCreated;
    private String description;

    /**
     * Initializes a task object.
     * @param priority
     * @param taskType
     * @param waitTime
     * @param hourCreated
     * @param description
     */
    public Task(int priority, TaskType taskType, int waitTime, int hourCreated, String description) {
        this.priority = priority;
        this.taskType = taskType;
        this.waitTime = waitTime;
        this.hourCreated = hourCreated;
        this.description = description;
    }

    @Override
    public int compareTo(Task other) {
        if (this.getPriority() > other.getPriority()) {
            return 1;
        } else if (this.getPriority() < other.getPriority()) {
            return -1;
        } else {
            if (this.hourCreated < other.hourCreated) {
                return 1;
            } else if (this.hourCreated > other.hourCreated) {
                return -1;
            } else {
                return 0;
            }
        }
        
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int newPriority) {
        this.priority = newPriority;
    }

    @Override
    public TaskInterface.TaskType getTaskType() {
        return taskType;
    }

    @Override
    public String getTaskDescription() {
        return description;
    }

    @Override
    public void incrementWaitingTime() {
        waitTime++;
    }

    @Override
    public void resetWaitingTime() {
        waitTime = 0;
    }

    @Override
    public int getWaitingTime() {
        return waitTime;
    }

    @Override
    public String toString(){
        return taskType + " " + description + " at Hour: " + hourCreated + ":00 ";
    }

}
