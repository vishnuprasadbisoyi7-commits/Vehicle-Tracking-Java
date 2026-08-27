package tech.csm.util;

import tech.csm.bean.VtsPacketResponseBean;

public class VtsPacketConverter {

    public static VtsPacketResponseBean convertPacketToJson(String rawPacket) {
        if (rawPacket == null || !rawPacket.contains("$PVT")) {
            return null;
        }

        // Clean out outer '$' and '*'
        String clean = rawPacket.trim().replace("$", "").replace("*", "");
        String[] tokens = clean.split(",");

        if (tokens.length < 22) {
            return null;
        }

        return VtsPacketResponseBean.builder()
                .header(tokens[0])
                .deviceModel(tokens[1])
                .firmwareVersion(tokens[2])
                .packetType(tokens[3])
                .gpsFix(tokens[5])
                .imei(tokens[6])
                .vehicleNo(tokens[7])
                .date(tokens[9])
                .time(tokens[10])
                .latitude(parseDouble(tokens[11]))
                .latDirection(tokens[12])
                .longitude(parseDouble(tokens[13]))
                .lonDirection(tokens[14])
                .speed(parseDouble(tokens[15]))
                .heading(parseDouble(tokens[16]))
                .satellites(parseInt(tokens[17]))
                .altitude(parseDouble(tokens[18]))
                .operator(tokens[21])
                .rawPacket(rawPacket.trim())
                .build();
    }

    private static Double parseDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (Exception e) {
            return 0.0;
        }
    }

    private static Integer parseInt(String str) {
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            return 0;
        }
    }
}