package tech.csm.bean;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleTelemetryViewDto {
    private Long id;
    private String imei;
    private String vehicleNo;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private Double heading;
    private String packetDate;
    private String packetTime;
    private Integer satellites;
    private Double altitude;
    private String operator;
    private LocalDateTime createdAt;
}