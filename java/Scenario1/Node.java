import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Node implements Runnable {

  private static BlockingQueue<Task> queue;
  private ConcurrentHashMap<Long, ProducerProgress> taskMap;
        
  public Node(BlockingQueue<Task> q, ConcurrentHashMap<Long, ProducerProgress> taskMap) {
    queue = q;
    this.taskMap = taskMap;
  }
  
  @Override
  public void run() {
    try {
      while (true) { consume(queue.take()); }
    } catch (InterruptedException ex) { }
  }
  private void consume(Task t) {
    t.execute();
    ProducerProgress pg = taskMap.get(t.producerId);
    pg.executadas.add(t);
  }

}