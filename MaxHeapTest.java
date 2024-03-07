import java.util.Arrays;
import java.util.Random;

/**
 * Simple unit testing class for the MaxHeap class.
 * 
 * @author Alex Taylor CS321 Data Structures Sp-24
 * @version CS321 Data Structures Sp-24
 */
public class MaxHeapTest {

    // Helper function to check if the array is sorted
    private static boolean checkIfSorted(Task[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) == -1) {
                return false;
            }
        }
        return true;
    }

    // Helper function to check if the heap array satisfies max-heap property
    private static boolean checkIfMaxHeap(MaxHeap heap) {
        Task[] array = heap.heap; // Accessing the heap array from the MaxHeap object
        for (int i = 0; i < heap.size / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < heap.size && array[left].compareTo(array[i]) > 0) {
                return false;
            }

            if (right < heap.size && array[right].compareTo(array[i]) > 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void main (String[] args) {

        // Initialize new empty heap
        MaxHeap heap = new MaxHeap();

        ////////////////////////////
	    // BEGIN isEmpty() TESTS
	    ////////////////////////////

        // Test empty heap
        System.out.println("\n-------------------------------- BEGIN isEmpty() TESTS --------------------------------\n");
        if (heap.isEmpty() == true) {
            System.out.println("\nisEmpty test empty (should return true): " + heap.isEmpty() + " =>                    PASS");
        } else {
            System.out.println("\nisEmpty test empty (should return true): " + heap.isEmpty() + " =>                    FAIL");
        }
        
        // Add a task to the heap
        Task task1 = new Task(10, TaskInterface.TaskType.FARM_MAINTENANCE, 1, 1, "Maintenence!");
        heap.heapInsert(task1);

        // Test non-empty heap
        if (heap.isEmpty() == false) {
            System.out.println("isEmpty test non-empty (should return false): " + heap.isEmpty() + " =>                     PASS");
        } else {
            System.out.println("isEmpty test non-empty (should return false): " + heap.isEmpty() + " =>                     FAIL");
        }
        
        ///////////////////////////////
        // BEGIN heapInsert() TESTS
        ///////////////////////////////
        System.out.println("\n-------------------------------- BEGIN heapInsert() TESTS --------------------------------\n");
        Task task2 = new Task(5, TaskInterface.TaskType.MINING, 2, 2, "Mining!");
        heap.heapInsert(task2);

        // Test size
        if (heap.size == 2) {
            System.out.println("\nheapInsert test size (should return 2): " + heap.size + " =>                                              PASS");
        } else {
            System.out.println("\nheapInsert test size (should return 2): " + heap.size + " =>                                              FAIL");
        }

        // Test if array sorted
        if (heap.toString().equals("[FARM_MAINTENANCE, MINING]")) {
            System.out.println("heapInsert test (heap array should be [Task 1, Task 2]): " + heap.toString() + " =>               PASS");
        } else {
            System.out.println("heapInsert test (heap array should be [Task 1, Task 2]): " + heap.toString() + " =>               FAIL");
        }

        //////////////////////////////
        // BEGIN extractMax() TESTS
        //////////////////////////////
        System.out.println("\n-------------------------------- BEGIN extractMax() TESTS --------------------------------\n");
        System.out.println("\nextractMax test 1: \n");
        System.out.println(heap.extractMax() + " =>             PASS"); 
        System.out.println(heap.extractMax() + " =>                     PASS"); 

        System.out.println("\nextractMax test 2: \n");
        Task task3 = new Task(10, TaskInterface.TaskType.FEEDING, 3, 3, "Feeding!");
        
        // Insert 3 tasks
        heap.heapInsert(task1);
        heap.heapInsert(task2);
        heap.heapInsert(task3);
        
        // Check to see if tasks are extracted in order, even when priority is same
        System.out.println("extractMax test order (should be farm maintenence, feeding, mining): \n");
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
        System.out.println(" =>                                                                            PASS");

        ///////////////////////////////////
        // BEGIN heapIncreaseKey() TESTS
        ///////////////////////////////////

        // Test heapIncreaseKey on a 2 task heap
        System.out.println("\n-------------------------------- BEGIN heapIncreaseKey() TESTS --------------------------------\n");
        Task task4 = new Task(20, TaskInterface.TaskType.FISHING, 2, 3, "Fishing!");
        heap.heapInsert(task3);
        heap.heapInsert(task2);
        System.out.println("test #1: ");
        System.out.println("\nHeap with two elements, before heapIncreaseKey: \n" + heap.toString());
        heap.heapIncreaseKey(task4, 0);
        System.out.println("\nHeap with two elements, after heapIncreaseKey: \n" + heap.toString());
        System.out.println("\nFishing has a higher priority than feeding, hence it was able to be pushed to the top.\n");
        System.out.println(" =>                                                                                PASS");

        // Same test but with a 3 task heap
        System.out.println("\ntest #2: ");
        heap.extractMax();
        heap.extractMax();
        heap.heapInsert(task1);
        heap.heapInsert(task2);
        heap.heapInsert(task3);

        System.out.println("\nHeap with three elements, before heapIncreaseKey:\n" + heap.toString());
        heap.heapIncreaseKey(task4, 0);
        System.out.println("\nHeap with three elements, after heapIncreaseKey:\n" + heap.toString());
        System.out.println("\nFishing has a higher priority than feeding, hence it was able to be pushed to the top.\n");
        System.out.println(" =>                                                                                PASS");

        /////////////////////////////////
        // BEGIN checkIfMaxHeap() TESTS
        /////////////////////////////////
        System.out.println("\n-------------------------------- BEGIN checkIfMaxHeap() TESTS --------------------------------\n");
        if (checkIfMaxHeap(heap)) {
            System.out.println("\nCall checkIfMaxHeap() method should return true: " + checkIfMaxHeap(heap) + " =>              PASS");
        } else {
            System.out.println("\nCall checkIfMaxHeap() method should return true: " + checkIfMaxHeap(heap) + " =>              FAIL");
        }

        ///////////////////////////////
        // BEGIN checkIfSorted() TESTS
        ///////////////////////////////
        System.out.println("\n-------------------------------- BEGIN checkIfSorted() TESTS --------------------------------\n");
        Task[] array = new Task[3];
        for (int i = 0; i < 3; i++) {
            array[i] = heap.extractMax();
        }
        if (checkIfSorted(array)) {
            System.out.println("\nCall checkIfSorted() method should return true: " + checkIfSorted(array) + " =>              PASS");
        } else {
            System.out.println("\nCall checkIfSorted() method should return true: " + checkIfSorted(array) + " =>              FAIL");
        }

        /////////////////////////////////
        // BEGIN insertAscending TESTS
        /////////////////////////////////
        System.out.println("\n-------------------------------- BEGIN insertAscending TESTS --------------------------------\n");

        // Insert 4 tasks in ascending order and see if they are a max heap
        Task task5 = new Task(1, TaskInterface.TaskType.SOCIALIZING, 0, 0, "Fishing!");
        Task task6 = new Task(2, TaskInterface.TaskType.FORAGING, 0, 0, "Foraging!");
        Task task7 = new Task(3, TaskInterface.TaskType.FEEDING, 0, 0, "Feeding!");
        Task task8 = new Task(4, TaskInterface.TaskType.FISHING, 0, 0, "Fishing!");
        heap.heapInsert(task5);
        heap.heapInsert(task6);
        heap.heapInsert(task7);
        heap.heapInsert(task8);

        // Check if max heap
        if (checkIfMaxHeap(heap)) {
            System.out.println("\nCall checkIfMaxHeap() method should return true: " + checkIfMaxHeap(heap) + " =>              PASS");
        } else {
            System.out.println("\nCall checkIfMaxHeap() method should return true: " + checkIfMaxHeap(heap) + " =>              FAIL");
        }

        // Check that when they are extracted they are sorted
        Task[] array1 = new Task[4];
        for (int i = 0; i < 4; i++) {
            array1[i] = heap.extractMax();
        }
        if (checkIfSorted(array1)) {
            System.out.println("\nCall checkIfSorted() method should return true: " + checkIfSorted(array1) + " =>              PASS");
        } else {
            System.out.println("\nCall checkIfSorted() method should return true: " + checkIfSorted(array1) + " =>              FAIL");
        }

        /////////////////////////////////
        // BEGIN insertDescending TESTS
        /////////////////////////////////
        System.out.println("\n-------------------------------- BEGIN insertDescending TESTS --------------------------------\n");

        // Insert 4 tasks in descending order and see if they are a max heap
        Task task9 = new Task(8, TaskInterface.TaskType.SOCIALIZING, 0, 0, "Fishing!");
        Task task10 = new Task(7, TaskInterface.TaskType.FORAGING, 0, 0, "Foraging!");
        Task task11 = new Task(6, TaskInterface.TaskType.FEEDING, 0, 0, "Feeding!");
        Task task12 = new Task(5, TaskInterface.TaskType.FISHING, 0, 0, "Fishing!");
        heap.heapInsert(task9);
        heap.heapInsert(task10);
        heap.heapInsert(task11);
        heap.heapInsert(task12);

        // Check if max heap
        if (checkIfMaxHeap(heap)) {
            System.out.println("\nCall checkIfMaxHeap() method should return true: " + checkIfMaxHeap(heap) + " =>              PASS");
        } else {
            System.out.println("\nCall checkIfMaxHeap() method should return true: " + checkIfMaxHeap(heap) + " =>              FAIL");
        }

        // Check that when they are extracted they are sorted
        Task[] array2 = new Task[4];
        for (int i = 0; i < 4; i++) {
            array2[i] = heap.extractMax();
        }
        if (checkIfSorted(array2)) {
            System.out.println("\nCall checkIfSorted() method should return true: " + checkIfSorted(array2) + " =>              PASS");
        } else {
            System.out.println("\nCall checkIfSorted() method should return true: " + checkIfSorted(array2) + " =>              FAIL");
        }

        //////////////////////////////
        // BEGIN insertRandom TESTS
        //////////////////////////////
        System.out.println("\n-------------------------------- BEGIN insertRandom TESTS --------------------------------\n");

        Random rand = new Random();
        Task[] tasksToInsert = {
            new Task(rand.nextInt(40), TaskInterface.TaskType.SOCIALIZING, 0, 0, "Fishing!"),
            new Task(rand.nextInt(40), TaskInterface.TaskType.FORAGING, 0, 0, "Foraging!"),
            new Task(rand.nextInt(40), TaskInterface.TaskType.FEEDING, 0, 0, "Feeding!"),
            new Task(rand.nextInt(40), TaskInterface.TaskType.FISHING, 0, 0, "Fishing!"),
            new Task(rand.nextInt(40), TaskInterface.TaskType.SOCIALIZING, 0, 0, "Fishing!"),
            new Task(rand.nextInt(40), TaskInterface.TaskType.FORAGING, 0, 0, "Foraging!"),
            new Task(rand.nextInt(40), TaskInterface.TaskType.FEEDING, 0, 0, "Feeding!"),
            new Task(rand.nextInt(40), TaskInterface.TaskType.FISHING, 0, 0, "Fishing!")
        };
        for (Task task : tasksToInsert) {
            heap.heapInsert(task);
        }

        // Check if max heap
        if (checkIfMaxHeap(heap)) {
            System.out.println("\nCall checkIfMaxHeap() method should return true: " + checkIfMaxHeap(heap) + " =>              PASS");
        } else {
            System.out.println("\nCall checkIfMaxHeap() method should return true: " + checkIfMaxHeap(heap) + " =>              FAIL");
        }

        // Check that when they are extracted they are sorted
        Task[] array3 = new Task[8];
        for (int i = 0; i < 8; i++) {
            array3[i] = heap.extractMax();
        }
        if (checkIfSorted(array3)) {
            System.out.println("\nCall checkIfSorted() method should return true: " + checkIfSorted(array3) + " =>              PASS");
        } else {
            System.out.println("\nCall checkIfSorted() method should return true: " + checkIfSorted(array3) + " =>              FAIL");
        }

        System.out.println("\n------------------------------------ END TESTER ------------------------------------\n");

    }
}
