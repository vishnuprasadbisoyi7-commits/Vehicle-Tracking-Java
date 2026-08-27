package tech.csm.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.csm.model.VehicleTelemetry;

@Repository
public interface VehicleTelemetryRepository extends JpaRepository<VehicleTelemetry, Long> {

    @Query("SELECT v FROM VehicleTelemetry v WHERE v.vehicleNo = :vehicleNo ORDER BY v.createdAt DESC")
    List<VehicleTelemetry> findHistoryByVehicleNo(@Param("vehicleNo") String vehicleNo);

    @Query(value = "SELECT * FROM public.vehicle_telemetry_history WHERE vehicle_no = :vehicleNo ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    VehicleTelemetry findLatestLocation(@Param("vehicleNo") String vehicleNo);
}