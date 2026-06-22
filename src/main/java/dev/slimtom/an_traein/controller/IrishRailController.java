package dev.slimtom.an_traein.controller;

import dev.slimtom.an_traein.client.IrishRailClient;
import dev.slimtom.an_traein.parser.IrishRailXmlParser;
import dev.slimtom.an_traein.model.TrainObservation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IrishRailController {
    private final IrishRailClient irishRailClient;
    private final IrishRailXmlParser irishRailXmlParser;

    public IrishRailController(IrishRailClient irishRailClient, IrishRailXmlParser irishRailXmlParser) {
        this.irishRailClient = irishRailClient;
        this.irishRailXmlParser = irishRailXmlParser;
    }

    @GetMapping("/irishrail/observations")
    public List<TrainObservation> getIrishRailObservations(
        @RequestParam(defaultValue = "Mullingar") String station
    ) throws Exception {
        String rawXml = irishRailClient.getRawStationData(station);
        return irishRailXmlParser.parseStationData(rawXml);
    }
}
