package tech.csm.service;

import java.util.List;
import tech.csm.bean.VtsPacketDto;

public interface VtsJsonService {
    List<VtsPacketDto> parsePacketList(List<String> rawPackets);
    List<VtsPacketDto> getPacketsFromDatabase(String imei, String vehicleNo);
}