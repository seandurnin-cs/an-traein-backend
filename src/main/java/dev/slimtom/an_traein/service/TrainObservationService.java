package dev.slimtom.an_traein.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Service;

import dev.slimtom.an_traein.client.IrishRailClient;
import dev.slimtom.an_traein.model.TrainObservation;
import dev.slimtom.an_traein.parser.IrishRailXmlParser;
import dev.slimtom.an_traein.repository.TrainObservationRepository;


@Service
public class TrainObservationService {
    private final IrishRailClient irishRailClient;
    private final IrishRailXmlParser irishRailXmlParser;
    private final TrainObservationRepository trainObservationRepository;

    public TrainObservationService(IrishRailClient irishRailClient, IrishRailXmlParser irishRailXmlParser, TrainObservationRepository trainObservationRepository) {
        this.irishRailClient = irishRailClient;
        this.irishRailXmlParser = irishRailXmlParser;
        this.trainObservationRepository = trainObservationRepository;
    }

    @GetMapping("/irishrail/observations")
    public List<TrainObservation> getIrishRailObservations(
            @RequestParam(defaultValue = "Mullingar") String station) throws Exception {
        String rawXml = irishRailClient.getRawStationData(station);
        return irishRailXmlParser.parseStationData(rawXml);
    }

    public List<TrainObservation> fetchAndSaveObservations(String station) throws Exception {
        String rawXml = irishRailClient.getRawStationData(station);

        List<TrainObservation> observations = irishRailXmlParser.parseStationData(rawXml);

        return trainObservationRepository.saveAll(observations);
    }
}
