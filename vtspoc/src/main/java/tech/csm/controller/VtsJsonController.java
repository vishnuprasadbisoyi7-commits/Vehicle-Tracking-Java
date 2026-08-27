package tech.csm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tech.csm.bean.VtsPacketDto;
import tech.csm.service.VtsJsonService;

@RestController
@RequestMapping("/api/vts")
public class VtsJsonController {

    @Autowired
    private VtsJsonService vtsJsonService;

    // 1. Convert live database points into JSON packets
    @GetMapping("/packets/json")
    public List<VtsPacketDto> getJsonPackets(
            @RequestParam(value = "imei", defaultValue = "861819083751561") String imei,
            @RequestParam(value = "vehicleNo", defaultValue = "RJ14GL6797") String vehicleNo) {
        return vtsJsonService.getPacketsFromDatabase(imei, vehicleNo);
    }

    // 2. Parse raw packet strings sent in request body into JSON
    @PostMapping("/packets/parse")
    public List<VtsPacketDto> parseRawPackets(@RequestBody List<String> rawPackets) {
        return vtsJsonService.parsePacketList(rawPackets);
    }
}