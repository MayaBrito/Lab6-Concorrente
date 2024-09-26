import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TaskProducer implements Runnable {

    private static BlockingQueue<Task> queue;
    private ConcurrentHashMap<Long, ProducerProgress> taskMap;
    private long id;

    public TaskProducer (BlockingQueue<Task> q, ConcurrentHashMap<Long, ProducerProgress> taskMap, long id) {
        queue = q;
        this.id = id;
        this.taskMap = taskMap;
        taskMap.put(id, new ProducerProgress(id));
    };

    @Override
    public void run() {
        try {
            Task t = produce();
            queue.put(t);
          } catch (InterruptedException ex) {}
        }

    private Task produce (){
        UUID taskId = UUID.randomUUID();
        return new Task(taskId, this.id);
    }

}