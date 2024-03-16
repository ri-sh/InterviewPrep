import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

class Task implements Comparable<Task> {
    private final Runnable runnable;
    private final long executionTime;

    Task(Runnable runnable, long delayMillis) {
        this.runnable = runnable;
        this.executionTime = System.currentTimeMillis() + delayMillis;
    }

    public void run() {
        runnable.run();
    }

    @Override
    public int compareTo(Task other) {
        return Long.compare(this.executionTime, other.executionTime);
    }
}

class TaskExecutor {
    private final PriorityBlockingQueue<Task> queue;
    private final ExecutorService executorService;
    private Thread executorThread;

    TaskExecutor() {
        queue = new PriorityBlockingQueue<>();
        executorService = Executors.newCachedThreadPool();
        executorThread = new Thread(this::executeTasks);
        executorThread.start();
    }

    void schedule(Runnable task, long delayMillis) {
        queue.offer(new Task(task, delayMillis));
    }

    private void executeTasks() {
        while (true) {
            long currentTime = System.currentTimeMillis();
            Task task = queue.peek();

            if (task != null && task.executionTime <= currentTime) {
                queue.poll(); // Remove the task from the queue
                executorService.submit(task::run); // Execute the task in a separate thread
            } else {
                try {
                    Thread.sleep(1); // Avoid busy-waiting
                } catch (InterruptedException e) {
                    // Handle thread interruption
                    break;
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TaskExecutor executor = new TaskExecutor();

        executor.schedule(() -> System.out.println("Task 1 executed!"), 5000);
        executor.schedule(() -> System.out.println("Task 2 executed!"), 10000);
        executor.schedule(() -> System.out.println("Task 3 executed!"), 3000);
    }
}
