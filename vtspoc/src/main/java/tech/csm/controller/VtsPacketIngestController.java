package tech.csm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/telemetry")
public class VtsPacketIngestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/packet")
    public String receivePacket(@RequestBody String rawPacket) {
        // Pushes string into Kafka
        kafkaTemplate.send("vts-raw-packets", rawPacket.trim());
        return "QUEUED";
    }
}