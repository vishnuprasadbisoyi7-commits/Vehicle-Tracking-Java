package tech.csm.util;

import java.time.LocalDateTime;
import tech.csm.bean.VtsPacketDto;
import tech.csm.model.VehicleTelemetry;

public class VtsPacketParser {

    // Method used by Kafka Consumer to save directly to PostgreSQL
    public static VehicleTelemetry parseToEntity(String rawLine) {
        if (rawLine == null || !rawLine.contains("$PVT")) {
            return null;
        }

        String clean = rawLine.trim().replace("$", "").replace("*", "");
        String[] parts = clean.split(",");

        if (parts.length < 22) {
            return null;
        }

        return VehicleTelemetry.builder()
                .imei(parts[6])
                .vehicleNo(parts[7])
                .packetDate(parts[9])
                .packetTime(parts[10])
                .latitude(parseDouble(parts[11]))
                .longitude(parseDouble(parts[13]))
                .speed(parseDouble(parts[15]))
                .heading(parseDouble(parts[16]))
                .satellites(parseInt(parts[17]))
                .altitude(parseDouble(parts[18]))
                .operator(parts[21])
                .rawPacket(rawLine.trim())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // Method used if you need DTO representation
    public static VtsPacketDto parseRawPacket(String rawLine) {
        if (rawLine == null || !rawLine.contains("$PVT")) {
            return null;
        }

        String clean = rawLine.trim().replace("$", "").replace("*", "");
        String[] parts = clean.split(",");

        if (parts.length < 22) {
            return null;
        }

        return VtsPacketDto.builder()
                .imei(parts[6])
                .vehicleNo(parts[7])
                .date(parts[9])
                .time(parts[10])
                .latitude(parseDouble(parts[11]))
                .longitude(parseDouble(parts[13]))
                .speed(parseDouble(parts[15]))
                .heading(parseDouble(parts[16]))
                .satellites(parseInt(parts[17]))
                .altitude(parseDouble(parts[18]))
                .operator(parts[21])
                .build();
    }

    private static Double parseDouble(String val) {
        try {
            return Double.valueOf(val);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private static Integer parseInt(String val) {
        try {
            return Integer.valueOf(val);
        } catch (Exception e) {
            return 0;
        }
    }
}