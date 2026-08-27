package tech.csm.util;

import tech.csm.bean.VtsPacketDto;

public class PacketParserUtil {

    public static VtsPacketDto parse(String rawPacket) {
        if (rawPacket == null || !rawPacket.contains("$PVT")) {
            return null;
        }

        String clean = rawPacket.trim().replace("$", "").replace("*", "");
        String[] p = clean.split(",");

        if (p.length < 22) {
            return null;
        }

        return VtsPacketDto.builder()
                .imei(p[6])
                .vehicleNo(p[7])
                .date(p[9])
                .time(p[10])
                .latitude(Double.parseDouble(p[11]))
                .longitude(Double.parseDouble(p[13]))
                .speed(Double.parseDouble(p[15]))
                .heading(Double.parseDouble(p[16]))
                .satellites(Integer.parseInt(p[17]))
                .altitude(Double.parseDouble(p[18]))
                .operator(p[21])
                .build();
    }
}