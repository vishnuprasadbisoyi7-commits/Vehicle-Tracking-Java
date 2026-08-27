package tech.csm.repository;




import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tech.csm.model.VehicleRouteData;


@Repository
public interface VehicleRouteDataRepository extends JpaRepository<VehicleRouteData, Long> {

    @Query("SELECT v FROM VehicleRouteData v WHERE v.vehicleNo = :vehicleNo AND v.isActive = 0 ORDER BY v.sequenceOrder ASC")
    List<VehicleRouteData> findRouteByVehicleNo(@Param("vehicleNo") String vehicleNo);

    @Query("SELECT v FROM VehicleRouteData v WHERE v.vehicleNo = :vehicleNo AND v.imei = :imei AND v.isActive = 0 ORDER BY v.sequenceOrder ASC")
    List<VehicleRouteData> findRouteByVehicleNoAndImei(@Param("vehicleNo") String vehicleNo, @Param("imei") String imei);

    @Query("SELECT COUNT(v) FROM VehicleRouteData v WHERE v.vehicleNo = :vehicleNo AND v.isActive = 0")
    Long countRoutePoints(@Param("vehicleNo") String vehicleNo);
    
    @Query(value = "SELECT * FROM public.vehicle_route_data ORDER BY time_val ASC", nativeQuery = true)
    List<VehicleRouteData> findAllRouteData();

    @Query(value = "SELECT * FROM public.vehicle_route_data WHERE date_val = :dateVal ORDER BY time_val ASC", nativeQuery = true)
    List<VehicleRouteData> findByDateVal(@Param("dateVal") String dateVal);

    @Query(value = "SELECT COUNT(*) FROM public.vehicle_route_data", nativeQuery = true)
    Long countTotalWaypoints();
}
