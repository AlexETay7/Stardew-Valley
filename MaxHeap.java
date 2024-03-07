import java.util.Arrays;

/**
 * The MaxHeap class represents a binary max-heap data structure that stores tasks.
 * Tasks are ordered based on their priority, with higher priority tasks being at the root.
 * This class provides methods for creating, manipulating, and accessing elements in the max-heap.
 * 
 * @author Alex Taylor 
 * @version CS321 Data Structures Sp-24
 */
public class MaxHeap {
    
    protected Task[] heap;
    protected int size; 

    /**
     * Initializes an empty heap.
     */
    public MaxHeap() {
        heap = new Task[10];
        size = 0; 
    }

    /**
     * Initializes an empty heap out of a given array of tasks.
     * @param array - array to be made into a maxheap
     */
    public MaxHeap(Task[] array) {
        heap = new Task[array.length];
        size = array.length; 
        heap = Arrays.copyOf(array, size);
        buildMaxHeap();
    }

    /**
     * Maintains the max-heap property for the heap.
     * @param i - index
     */
    private void maxHeapifyDown(int i) {
        int left = left(i);
        int right = right(i);
        int largest;

        if ((left < size) && (heap[left].compareTo(heap[i])) > 0) {
            largest = left;
        } else {
            largest = i;
        }
        if ((right < size) && (heap[right].compareTo(heap[largest]) > 0)) {
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            maxHeapifyDown(largest);
        }
    }

    /**
     * Iterates over the array and applies maxHeapifyDown to each non-leaf node.
     */
    private void buildMaxHeap() {
        int s = size;
        for (int i = (s/2) - 1; i >= 0; i--) {
            maxHeapifyDown(i);
        }
    }

    /**
     * Returns the task at the root node,
     * in other words the highest priority task.
     * @return - root task 
     */
    public Task getMax() {
        if (size < 1) {
            System.out.println("Heap underflow.");
        }
        return heap[0];
    }

    /**
     * Removes and returns the root task 
     * (the one with highest priority), then 
     * re-heapifys the tree so the next
     * time we extract a task we are extracting
     * the highest priority task.
     * @return highest priority task
     */
    public Task extractMax() {
        Task max = getMax();
        heap[0] = heap[size - 1];
        size--;
        maxHeapifyDown(0);
        return max;
    }

    /**
     * "Max-heapify-up", increases the priority
     * of a task that's already in the heap, then 
     * swaps the task with it's parent task until
     * the max-heap property (parent value > child) is satisfied.
     * @param position - position in the heap where a new task (key)
     * will take the place of the old task, thus forcing us to 
     * re-heapify the heap above this point.
     * @param key - new task to be swapped into the heap
     */
    public void heapIncreaseKey(Task key, int position) {
        if (heap[position].compareTo(key) > 0) {
            System.out.println("New key priority must be larger than current key.");
        }
        heap[position] = key;
        while ((position > 0) && (heap[parent(position)].compareTo(heap[position]) < 0)) {
            swap(parent(position), position);
            position = parent(position);
        }
    }

    /**
     * Inserts a task into the heap, then calls the
     * heapIncreaseKey method to re-heapify the heap.
     * @param key - task to be added to the heap
     */
    public void heapInsert(Task key) {
        expandIfNecessary();
        size++;
        heap[size - 1] = key;
        heapIncreaseKey(key, size - 1);

    }

    /**
     * Returns true if the heap is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Swaps the positions of two tasks in the array.
     * @param index1 - The index of the first task to be swapped.
     * @param index2 - The index of the second task to be swapped.
     */
    private void swap(int index1, int index2) {
        Task temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    /**
     * Expand the heap array by twice
     * its size if it's full.
     */
    private void expandIfNecessary() {
        if(heap.length == size) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    /**
     * Returns left node index.
     * @param i - index
     * @return
     */
    private int left(int i) {
        return (2 * i) + 1;
    }

    /**
     * Returns right node index.
     * @param i - index
     * @return
     */
    private int right(int i) {
        return (2 * i) + 2;
    }

    /**
     * Returns parent node index.
     * @param i - index
     * @return
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Returns a string representation of the heap array with only the TaskType.
     * @return String representation of the heap array with only the TaskType.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i].getTaskType());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
