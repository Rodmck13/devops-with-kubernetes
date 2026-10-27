import java.time.Instant;
import java.util.UUID;

public class LogOutput {

    public static void main(String[] args)
            throws InterruptedException {

        String randomId = UUID.randomUUID().toString();

        while (true) {
            System.out.println(
                Instant.now() + ": " + randomId
            );

            Thread.sleep(5000);
        }
    }
}