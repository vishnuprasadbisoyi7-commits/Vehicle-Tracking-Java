package tech.csm.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tech.csm.model.VehicleTelemetry;
import tech.csm.repository.VehicleTelemetryRepository;
import tech.csm.util.VtsPacketParser;

@Service
public class VtsKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(VtsKafkaConsumer.class);

    @Autowired
    private VehicleTelemetryRepository telemetryRepo;

    @KafkaListener(topics = "vts-raw-packets", groupId = "vts-telemetry-group")
    public void consumeAndSave(String rawPacket) {
        try {
            // 1. Convert raw packet string to VehicleTelemetry entity
            VehicleTelemetry telemetry = VtsPacketParser.parseToEntity(rawPacket);

            // 2. Persist in PostgreSQL
            if (telemetry != null) {
                VehicleTelemetry saved = telemetryRepo.save(telemetry);
                logger.info("Saved telemetry to DB - Vehicle: {}, Lat: {}, Lon: {}", 
                            saved.getVehicleNo(), saved.getLatitude(), saved.getLongitude());
            } else {
                logger.warn("Received invalid/unparseable packet: {}", rawPacket);
            }
        } catch (Exception e) {
            logger.error("Error processing packet from Kafka: {}", e.getMessage(), e);
        }
    }
}