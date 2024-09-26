import java.util.Random;
import java.util.UUID;

public class Task {
    UUID id;
    long producerId;
    long start;
    long elapsed;

    public Task(UUID id, long producerId) {
        this.id = id;
        this.producerId = producerId;
        start = System.currentTimeMillis();
    }

    public void execute() {
        try {
            // generating a number between 1000 and 15000
            long execDuration = 1000 + (long) (new Random().nextFloat() * (15000 - 1000));
            Thread.sleep(execDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        elapsed = System.currentTimeMillis() - start;
    }
}
