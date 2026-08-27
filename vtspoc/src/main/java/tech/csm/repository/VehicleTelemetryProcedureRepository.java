package tech.csm.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tech.csm.bean.VehicleTelemetryViewDto;

@Repository
public class VehicleTelemetryProcedureRepository {

    @Autowired
    private DataSource dataSource;

    public List<VehicleTelemetryViewDto> executeTelemetryProcedure(String action, String vehicleNo, Integer limit) {
        List<VehicleTelemetryViewDto> resultList = new ArrayList<>();
        String procedureCall = "CALL public.sp_vehicle_telemetry_action(?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection()) {
            // PostgreSQL requires autoCommit to be false to read from an INOUT refcursor
            conn.setAutoCommit(false);

            try (CallableStatement cs = conn.prepareCall(procedureCall)) {
                cs.setString(1, action);
                cs.setString(2, vehicleNo != null ? vehicleNo : "");
                
                if (limit != null && limit > 0) {
                    cs.setInt(3, limit);
                } else {
                    cs.setInt(3, 100);
                }

                // Register cursor parameter
                cs.setNull(4, Types.OTHER);
                cs.registerOutParameter(4, Types.OTHER);

                cs.execute();

                // Extract and map the cursor ResultSet
                try (ResultSet rs = (ResultSet) cs.getObject(4)) {
                    if (rs != null) {
                        while (rs.next()) {
                            Timestamp ts = rs.getTimestamp("created_at");
                            resultList.add(VehicleTelemetryViewDto.builder()
                                    .id(rs.getLong("id"))
                                    .imei(rs.getString("imei"))
                                    .vehicleNo(rs.getString("vehicle_no"))
                                    .latitude(rs.getDouble("latitude"))
                                    .longitude(rs.getDouble("longitude"))
                                    .speed(rs.getDouble("speed"))
                                    .heading(rs.getDouble("heading"))
                                    .packetDate(rs.getString("packet_date"))
                                    .packetTime(rs.getString("packet_time"))
                                    .satellites(rs.getInt("satellites"))
                                    .altitude(rs.getDouble("altitude"))
                                    .operator(rs.getString("operator"))
                                    .createdAt(ts != null ? ts.toLocalDateTime() : null)
                                    .build());
                        }
                    }
                }
            }
            conn.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed executing sp_vehicle_telemetry_action: " + e.getMessage(), e);
        }

        return resultList;
    }
}