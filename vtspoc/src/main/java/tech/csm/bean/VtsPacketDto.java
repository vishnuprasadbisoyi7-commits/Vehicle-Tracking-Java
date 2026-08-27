package tech.csm.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VtsPacketDto {
    private String imei;
    private String vehicleNo;
    private String date;
    private String time;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private Double heading;
    private Integer satellites;
    private Double altitude;
    private String operator;
}