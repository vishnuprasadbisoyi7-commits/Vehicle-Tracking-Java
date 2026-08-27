package tech.csm.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.web.bind.annotation.*;

import tech.csm.bean.VtsPacketDto;
import tech.csm.util.PacketParserUtil;

@RestController
@RequestMapping("/api/packets")
public class PacketConverterController {

    // POST: Send raw packet strings in request body, get clean JSON list
    @PostMapping("/to-json")
    public List<VtsPacketDto> convertPacketsToJson(@RequestBody List<String> rawPackets) {
        return rawPackets.stream()
                .map(PacketParserUtil::parse)
                .filter(Objects::nonNull)
                .toList();
    }
}