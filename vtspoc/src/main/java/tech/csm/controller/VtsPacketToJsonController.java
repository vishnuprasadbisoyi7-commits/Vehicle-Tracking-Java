package tech.csm.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.web.bind.annotation.*;

import tech.csm.bean.VtsPacketResponseBean;
import tech.csm.util.VtsPacketConverter;

@RestController
@RequestMapping("/api/vts-converter")
public class VtsPacketToJsonController {

    // 1. Convert a single packet string via GET query param
    @GetMapping("/single")
    public VtsPacketResponseBean convertSinglePacket(@RequestParam("packet") String packet) {
        return VtsPacketConverter.convertPacketToJson(packet);
    }

    // 2. Convert multiple packet strings sent in a list via POST
    @PostMapping("/batch")
    public List<VtsPacketResponseBean> convertBatchPackets(@RequestBody List<String> rawPackets) {
        return rawPackets.stream()
                .map(VtsPacketConverter::convertPacketToJson)
                .filter(Objects::nonNull)
                .toList();
    }
}