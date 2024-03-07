# Project #2: Stardew Valley

* Author: Alex Taylor
* Class: CS321 Section #002
* Semester: Sp24

## Overview

In this project I implemented and used a Priority Queue abstract data type 
(by extending a MaxHeap data structure represented as an array)  
to simulate a priority-based task management algorithm that simulates a part
of a popular video game called Stardew Valley. 

## Reflection

This project was a lot of fun. I think that overall the most difficult
part was (like Dr. Yeh said) the MaxHeap class. The MaxHeap class as a whole
was not too bad, but it was the heapIncreaseKey() and heapIncrease() methods
that took me a while to figure out. I initially was reading into the pseudo
code TOO much by copying it exactly into my code, without thinking through
the code to see if it actually made sense. 

The other issue I had was with the compareTo() method. While coding up
the compareTo() method I absentmindedly said that if two tasks have the 
same priority, that when comparing the hour created, the task with
the GREATER hour created was the one to have higher priority. This was
an error that caused me to generate extra tasks and run the simulation for
an extra hour in a lot of the tests. I finally realized the issue, that
the task with the LESSER hour created value should have higher priority,
and as soon as I fixed that everything worked. 

I really enjoyed this project as I felt it was a culmination of what we 
learned in CS221 and I was able to pull on lots of different knowledge
I have built up over all my CS classes. Really enjoyed putting this
together.

## Compiling and Using

MyLifeInStardew is the main class that simulates the task prioritization algorithm within the Stardew Valley Simulation.

In the terminal directory where you have all these folders: 

Compile MyLifeInStardew by doing:
<pre>
javac MyLifeInStardew.java
</pre>
Run the simulation by doing:
<pre>
java MyLifeInStardew <"max-priority-level"> <"time-to-increment-priority"> <"total simulation-time in days"> <"task-generation-probability"> [<"seed">]
</pre>

Where:

`<max-priority-level>`: Highest possible priority in this simulation. Specifically, a Task can have a priority, ranging from 0 to maxPriorityLevel. This value though should be at least 1 for the simulation to work.

`<time-to-increment-priority>`: The duration after which the Task’s priority will be increased by one if the Task didn’t get done.

`<total simulation-time in days>`: The total time in number of days for the simulation.

`<task-generation-probability>`: The probability between 0 and 1 used to decide whether to generate a new Task during each hour.

`<seed>`: A seed for a random number generator for testing to ensure the simulation can be repeated (for testing).

Example: 
<pre>
java MyLifeInStardew 5 1 1 0.5 123
</pre>
The above command begins a simulation with:
- a max priority level for each task of 5 
- we will increment the priority after 1 hour 
- the simulation will last 1 day 
- a task generation probability (or luck of the day) of 0.5 (50%)
- a testing seed of 123

## Results 

Time related goals were not an objective of this project, only correct output.

To run test cases do:
<pre>
./run-tests.sh
</pre>

If this does not work, make sure you give the shell script 
executable permissions to run on your machine by doing:
<pre>
chmod +x run-tests.sh
</pre>

## Sources used

- Lecture notes
- Algorithms textbook

----------
