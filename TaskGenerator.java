import java.util.Random;

/**
 * TaskGenerator class generates tasks based on given probabilities and energy constraints.
 * It provides methods to create new tasks, decrement energy storage, reset energy storage,
 * get current energy storage, set current energy storage, generate tasks randomly,
 * and handle unlucky events.
 * Implements TaskGeneratorInterface.
 * 
 * @author Alex Taylor
 * @version CS321 Data Structures Sp-24
 */

public class TaskGenerator implements TaskGeneratorInterface {

    private int totalEnergy;
    private double probability;
    private Random random;

    /**
     * Creates a task generator with default energy,
     * user provides random seed and probability.
     * @param probability
     * @param chance
     */
    public TaskGenerator(double probability, long chance) {
        totalEnergy = DEFAULT_ENERGY;
        random = new Random(chance);
        this.probability = probability;
    }

    /**
     * Creates a task generator with default energy and
     * random number, and user provides probability.
     * @param probability
     */
    public TaskGenerator(double probability) {
        totalEnergy = DEFAULT_ENERGY;
        random = new Random();
        this.probability = probability;
    }

    @Override
    public Task getNewTask(int hourCreated, TaskInterface.TaskType taskType, String taskDescription) {
        return new Task(0, taskType, 0, hourCreated, taskDescription);
        
    }

    @Override
    public void decrementEnergyStorage(TaskInterface.TaskType taskType) {
        totalEnergy -= taskType.getEnergyPerHour();
    }

    @Override
    public void resetCurrentEnergyStorage() {
        totalEnergy = DEFAULT_ENERGY;
    }

    @Override
    public int getCurrentEnergyStorage() {
        return totalEnergy;
    }

    @Override
    public void setCurrentEnergyStorage(int newEnergyNum) {
        totalEnergy = newEnergyNum;
    }

    @Override
    public boolean generateTask() {
        double chance = random.nextDouble();
        if(chance < probability) {
            return true;
        }
        return false;
    }

    @Override
    public int getUnlucky(Task task, double unluckyProbability) {
        if (unluckyProbability <= task.getTaskType().getPassingOutProbability()) {
            if ((unluckyProbability <= task.getTaskType().getDyingProbability()) && (task.getTaskType() == Task.TaskType.MINING)) {
                totalEnergy = totalEnergy - (3 * totalEnergy) / 4;
                task.setPriority(0);
                return DEATH;
            } else {
                totalEnergy /= 2;
                return PASSED_OUT;
            }
        } else {
            return SURVIVED;
        }

    }

    /**
     * Create a String containing the Task's information.
     *
     * @param task - the Task
     * @param taskType - the Task's type
     */
    @Override
    public String toString(Task task, Task.TaskType taskType) {
            if(taskType == Task.TaskType.MINING) {
                return "     Mining " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")";
            }
            if(taskType == Task.TaskType.FISHING) {
                return "     Fishing " + task.getTaskDescription() + " at " + getCurrentEnergyStorage()+ " energy points (Priority:" + task.getPriority() +")" ;
            }
            if(taskType == Task.TaskType.FARM_MAINTENANCE) {
                return "     Farm Maintenance " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")";
            }
            if(taskType == Task.TaskType.FORAGING) {
                return "     Foraging " + task.getTaskDescription() + " at " + getCurrentEnergyStorage()+ " energy points (Priority:" + task.getPriority() +")" ;
            }
            if(taskType == Task.TaskType.FEEDING) {
                return "     Feeding " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")";
            }
            if(taskType == Task.TaskType.SOCIALIZING) {
                return "     Socializing " + task.getTaskDescription() + " at " + getCurrentEnergyStorage() + " energy points (Priority:" + task.getPriority() +")";
            }
            else { return "nothing to see here..."; }
    }

    
}
