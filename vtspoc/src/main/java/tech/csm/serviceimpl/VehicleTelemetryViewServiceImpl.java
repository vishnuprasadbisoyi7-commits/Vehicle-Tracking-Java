package tech.csm.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.csm.bean.VehicleTelemetryViewDto;
import tech.csm.repository.VehicleTelemetryProcedureRepository;
import tech.csm.service.VehicleTelemetryViewService;

@Service
public class VehicleTelemetryViewServiceImpl implements VehicleTelemetryViewService {

    @Autowired
    private VehicleTelemetryProcedureRepository procedureRepository;

    @Override
    @Transactional(readOnly = true)
    public VehicleTelemetryViewDto getLatestVehiclePosition(String vehicleNo) {
        List<VehicleTelemetryViewDto> list = procedureRepository.executeTelemetryProcedure("LATEST", vehicleNo, 1);
        return (list != null && !list.isEmpty()) ? list.get(0) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleTelemetryViewDto> getVehicleRouteHistory(String vehicleNo, Integer limit) {
        int max = (limit != null && limit > 0) ? limit : 200;
        return procedureRepository.executeTelemetryProcedure("HISTORY", vehicleNo, max);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleTelemetryViewDto> getAllRecentTelemetry(Integer limit) {
        int max = (limit != null && limit > 0) ? limit : 50;
        return procedureRepository.executeTelemetryProcedure("ALL", null, max);
    }
}