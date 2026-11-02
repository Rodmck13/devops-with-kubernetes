
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

public class LogOutput {

    public static void main(String[] args)
            throws IOException, InterruptedException {

        String randomId = UUID.randomUUID().toString();
        int port = Integer.parseInt(
            System.getenv().getOrDefault("PORT", "3000")
        );

        HttpServer server = HttpServer.create(
            new InetSocketAddress("0.0.0.0", port), 0
        );

        server.createContext("/", exchange -> {
            String response = Instant.now() + ": " + randomId;

            byte[] body = response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().set(
                "Content-Type", "text/plain; charset=UTF-8"
            );
            exchange.sendResponseHeaders(200, body.length);

            try (var output = exchange.getResponseBody()) {
                output.write(body);
            }
        });

        server.start();

        System.out.println("HTTP server started on port " + port);

        while (true) {
            System.out.println(Instant.now() + ": " + randomId);
            Thread.sleep(5000);
        }
    }
}
