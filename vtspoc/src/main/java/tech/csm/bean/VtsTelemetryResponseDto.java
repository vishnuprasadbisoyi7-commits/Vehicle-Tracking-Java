package tech.csm.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VtsTelemetryResponseDto {
    private Integer sequence;
    private String imei;
    private String vehicleNo;
    private String packetDate;
    private String packetTime;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private Double heading;
    private String rawPacket;
}