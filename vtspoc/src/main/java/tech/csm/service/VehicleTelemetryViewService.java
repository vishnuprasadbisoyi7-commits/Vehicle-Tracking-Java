package tech.csm.service;

import java.util.List;
import tech.csm.bean.VehicleTelemetryViewDto;

public interface VehicleTelemetryViewService {
    VehicleTelemetryViewDto getLatestVehiclePosition(String vehicleNo);
    List<VehicleTelemetryViewDto> getVehicleRouteHistory(String vehicleNo, Integer limit);
    List<VehicleTelemetryViewDto> getAllRecentTelemetry(Integer limit);
}