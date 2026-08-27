package tech.csm.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VtsPacketResponseBean {
    private String header;
    private String deviceModel;
    private String firmwareVersion;
    private String packetType;
    private String gpsFix;
    private String imei;
    private String vehicleNo;
    private String date;
    private String time;
    private Double latitude;
    private String latDirection;
    private Double longitude;
    private String lonDirection;
    private Double speed;
    private Double heading;
    private Integer satellites;
    private Double altitude;
    private String operator;
    private String rawPacket;
}