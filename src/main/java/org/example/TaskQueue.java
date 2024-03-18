package org.example;
import java.util.LinkedList;
import java.util.Queue;

class TaskQueue {
    private Queue<Task> queue = new LinkedList<>();

    public synchronized void add(Task task) {
        queue.add(task);
        notifyAll();
    }

    public synchronized Task get() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.remove();
    }
}
