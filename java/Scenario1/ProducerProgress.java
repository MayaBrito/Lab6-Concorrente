import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProducerProgress {
    ConcurrentLinkedQueue<Task> executadas = new ConcurrentLinkedQueue<>();
    long producerId;

    public ProducerProgress(long producerId) {
        this.producerId = producerId;
    }

    public long getTempoMedio() {
        if (executadas.size() == 0) {
            return 0;
        }


        Iterator<Task> i = executadas.iterator();
        long total = 0;
        
        while (i.hasNext()) {
            Task t = i.next();
            total += t.elapsed;
        }
        
        return total / executadas.size();
    }
}
