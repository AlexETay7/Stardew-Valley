/**
 * MyPriorityQueue class represents a priority queue implemented using a max heap.
 * It extends the MaxHeap class and implements the PriorityQueueInterface.
 * Priority is determined by the priority value associated with each task.
 *
 * @author Alex Taylor
 * @version CS321 Data Structures Sp-24
 */

public class MyPriorityQueue extends MaxHeap implements PriorityQueueInterface {

    /**
     * Initializes a new priority queue.
     */
    public MyPriorityQueue() {
        super();
    }

    @Override
    public void enqueue(Task task) {
        heapInsert(task);
    }

    @Override
    public Task dequeue() {
        return extractMax();
    }

    @Override
    public boolean isEmpty() {
        if(super.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void update(int timeToIncrementPriority, int maxPriority) {
        for (int i = 0; i < size; i++) {
            Task theTask = heap[i];
            theTask.incrementWaitingTime();
            if (theTask.getWaitingTime() >= timeToIncrementPriority) {
                theTask.resetWaitingTime();
                if (theTask.getPriority() < maxPriority) {
                    theTask.setPriority(theTask.getPriority() + 1);
                    heapIncreaseKey(theTask, i);
                }
            }
        }
    }
    
}
