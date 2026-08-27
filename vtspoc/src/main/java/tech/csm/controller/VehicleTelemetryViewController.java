package tech.csm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.csm.bean.VehicleTelemetryViewDto;
import tech.csm.service.VehicleTelemetryViewService;

@RestController
@RequestMapping("/api/telemetry-view")
@CrossOrigin(origins = "*")
public class VehicleTelemetryViewController {

    @Autowired
    private VehicleTelemetryViewService telemetryViewService;

    // 1. Live Marker Position
    @GetMapping("/latest")
    public ResponseEntity<VehicleTelemetryViewDto> getLatestLocation(
            @RequestParam(name = "vehicleNo", defaultValue = "RJ14GL6797") String vehicleNo) {
        VehicleTelemetryViewDto latest = telemetryViewService.getLatestVehiclePosition(vehicleNo);
        if (latest == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(latest);
    }

    // 2. Polyline Path History
    @GetMapping("/history")
    public ResponseEntity<List<VehicleTelemetryViewDto>> getRouteHistory(
            @RequestParam(name = "vehicleNo", defaultValue = "RJ14GL6797") String vehicleNo,
            @RequestParam(name = "limit", defaultValue = "200") Integer limit) {
        List<VehicleTelemetryViewDto> history = telemetryViewService.getVehicleRouteHistory(vehicleNo, limit);
        return ResponseEntity.ok(history);
    }

    // 3. View Recent Telemetry Records Across Vehicles
    @GetMapping("/recent-all")
    public ResponseEntity<List<VehicleTelemetryViewDto>> getAllRecent(
            @RequestParam(name = "limit", defaultValue = "50") Integer limit) {
        List<VehicleTelemetryViewDto> recent = telemetryViewService.getAllRecentTelemetry(limit);
        return ResponseEntity.ok(recent);
    }
}