package tech.csm.serviceimpl;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.csm.bean.VtsTelemetryResponseDto;
import tech.csm.model.VehicleRouteData;
import tech.csm.repository.VehicleRouteDataRepository;
import tech.csm.service.VtsPacketService;

@Service
public class VtsPacketServiceImpl implements VtsPacketService {

    @Autowired
    private VehicleRouteDataRepository repository;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HHmmss");

    @Override
    public List<VtsTelemetryResponseDto> generateTelemetryJson(String imei, String vehicleNo) {
        List<VehicleRouteData> routePoints = repository.findAllRouteData();
        List<VtsTelemetryResponseDto> jsonList = new ArrayList<>();

        String targetImei = (imei != null && !imei.isEmpty()) ? imei : "861819083751561";
        String targetVehicle = (vehicleNo != null && !vehicleNo.isEmpty()) ? vehicleNo : "RJ14GL6797";

        Double prevLat = null;
        Double prevLon = null;
        int seq = 1;

        for (VehicleRouteData point : routePoints) {
            ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
            String date = nowUtc.format(DATE_FMT);
            String timeVal = nowUtc.format(TIME_FMT);

            double lat = point.getLatitude();
            double lon = point.getLongitude();
            double speed = point.getSpeed() != null ? point.getSpeed() : 40.0;

            double heading = 0.0;
            if (prevLat != null && prevLon != null) {
                heading = calculateBearing(prevLat, prevLon, lat, lon);
            }
            prevLat = lat;
            prevLon = lon;

            String rawPacket = String.format(
                "$PVT,RA10,SW-V3.1,NR,1,L,%s,%s,1,%s,%s,%.6f,N,%.6f,E,%.1f,%.2f,24,239.653,1.04,0.8,airtel,1,0,0,4.1,0,C,31,404,10,96,426e,96,3652,24,96,3653,20,96,426f,20,96,6fd3,19,0,1,130,C*",
                targetImei, targetVehicle, date, timeVal, lat, lon, speed, heading
            );

            VtsTelemetryResponseDto dto = VtsTelemetryResponseDto.builder()
                    .sequence(seq++)
                    .imei(targetImei)
                    .vehicleNo(targetVehicle)
                    .packetDate(date)
                    .packetTime(timeVal)
                    .latitude(lat)
                    .longitude(lon)
                    .speed(speed)
                    .heading(Math.round(heading * 100.0) / 100.0)
                    .rawPacket(rawPacket)
                    .build();

            jsonList.add(dto);
        }
        return jsonList;
    }

    private double calculateBearing(double lat1, double lon1, double lat2, double lon2) {
        double rLat1 = Math.toRadians(lat1);
        double rLat2 = Math.toRadians(lat2);
        double diffLong = Math.toRadians(lon2 - lon1);

        double x = Math.sin(diffLong) * Math.cos(rLat2);
        double y = Math.cos(rLat1) * Math.sin(rLat2) - (Math.sin(rLat1) * Math.cos(rLat2) * Math.cos(diffLong));

        double bearing = Math.toDegrees(Math.atan2(x, y));
        return (bearing + 360.0) % 360.0;
    }
}