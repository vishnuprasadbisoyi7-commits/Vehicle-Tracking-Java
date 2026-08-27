package tech.csm.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle_telemetry_history", schema = "public")
public class VehicleTelemetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "imei", nullable = false)
    private String imei;

    @Column(name = "vehicle_no", nullable = false)
    private String vehicleNo;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "heading")
    private Double heading;

    @Column(name = "packet_date")
    private String packetDate;

    @Column(name = "packet_time")
    private String packetTime;

    @Column(name = "satellites")
    private Integer satellites;

    @Column(name = "altitude")
    private Double altitude;

    @Column(name = "operator")
    private String operator;

    @Column(name = "raw_packet", columnDefinition = "TEXT")
    private String rawPacket;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}