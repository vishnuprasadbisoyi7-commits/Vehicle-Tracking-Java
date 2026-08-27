package tech.csm.model;



import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicle_route_data")
public class VehicleRouteData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vehicle_no", nullable = false)
    private String vehicleNo;

    @Column(name = "imei", nullable = false)
    private String imei;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "sequence_order", nullable = false)
    private Integer sequenceOrder;

    @Column(name = "is_active")
    private Integer isActive;
}