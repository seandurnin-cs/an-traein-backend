package dev.slimtom.an_traein.controller;

import dev.slimtom.an_traein.client.IrishRailClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IrishRailController {
    private final IrishRailClient irishRailClient;

    public IrishRailController(IrishRailClient irishRailClient) {
        this.irishRailClient = irishRailClient;
    }

    @GetMapping(value = "/irishrail/raw", produces = MediaType.APPLICATION_XML_VALUE)
    public String getRawIrishRailData(
        @RequestParam(defaultValue = "Mullingar") String station
    ) throws Exception {
        return irishRailClient.getRawStationData(station);
    }
}
