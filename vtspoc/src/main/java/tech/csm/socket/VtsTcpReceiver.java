package tech.csm.socket;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class VtsTcpReceiver {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostConstruct
    public void startSocketServer() {
        CompletableFuture.runAsync(() -> {
            try (ServerSocket server = new ServerSocket(7001)) {
                System.out.println("Java TCP Listener active on port 7001...");
                while (true) {
                    Socket socket = server.accept();
                    CompletableFuture.runAsync(() -> readClientPackets(socket));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void readClientPackets(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String rawLine;
            while ((rawLine = reader.readLine()) != null) {
                if (rawLine.contains("$PVT")) {
                    // Java receives the raw line from Python and pushes to Kafka
                    kafkaTemplate.send("vts-raw-packets", rawLine.trim());
                }
            }
        } catch (Exception ignored) {}
    }
}