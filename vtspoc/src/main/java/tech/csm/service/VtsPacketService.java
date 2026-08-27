package tech.csm.service;

import java.util.List;
import tech.csm.bean.VtsTelemetryResponseDto;

public interface VtsPacketService {
    List<VtsTelemetryResponseDto> generateTelemetryJson(String imei, String vehicleNo);
}