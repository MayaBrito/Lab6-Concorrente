import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scenario1 {

    public static void main(String[] args) {
        System.out.println("Scenario 1 of Lab06!");

        BlockingQueue<Task> queue = new LinkedBlockingQueue<>();
        ConcurrentHashMap<Long, ProducerProgress> taskMap = new ConcurrentHashMap<>();

        ExecutorService consumerPool = Executors.newFixedThreadPool(3);
        ScheduledExecutorService p = Executors.newScheduledThreadPool(5);
       
        for (int i = 0; i < 5; i++) {
            p.scheduleAtFixedRate(new TaskProducer(queue, taskMap, i), 0, 5, TimeUnit.SECONDS);
        }
        for (int i = 0; i < 3; i++) {
            consumerPool.execute(new Node(queue, taskMap));
        }

        ScheduledExecutorService pgPool = Executors.newScheduledThreadPool(1);
        
        pgPool.scheduleAtFixedRate(
            () -> {
                Iterator<ProducerProgress> it = taskMap.values().iterator();

            while (it.hasNext()) {
                ProducerProgress pg = it.next();
                System.out.printf("Para Producer %d\n", pg.producerId);
                System.out.println("Tasks executadas");
                System.out.println(pg.executadas.toString());
                System.out.printf("Tempo médio: %d\n", pg.getTempoMedio());
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
