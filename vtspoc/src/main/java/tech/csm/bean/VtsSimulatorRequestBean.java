package tech.csm.bean;


import lombok.Data;

@Data
public class VtsSimulatorRequestBean {
    private String vehicleNo;
    private String imei;
    private Integer intervalMillis; // optional override for delay
}