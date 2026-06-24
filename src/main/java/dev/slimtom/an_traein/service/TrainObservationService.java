package dev.slimtom.an_traein.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Value;

import dev.slimtom.an_traein.client.IrishRailClient;
import dev.slimtom.an_traein.model.TrainObservation;
import dev.slimtom.an_traein.parser.IrishRailXmlParser;
import dev.slimtom.an_traein.repository.TrainObservationRepository;
import dev.slimtom.an_traein.dto.StationStats;

@Service
public class TrainObservationService {
    private final IrishRailClient irishRailClient;
    private final IrishRailXmlParser irishRailXmlParser;
    private final TrainObservationRepository trainObservationRepository;
    @Value("${antraein.stats.min-observation-id:1}")
    private Long minObservationIdForStats;

    public TrainObservationService(IrishRailClient irishRailClient, IrishRailXmlParser irishRailXmlParser,
            TrainObservationRepository trainObservationRepository) {
        this.irishRailClient = irishRailClient;
        this.irishRailXmlParser = irishRailXmlParser;
        this.trainObservationRepository = trainObservationRepository;
    }

    public List<TrainObservation> getIrishRailObservations(
            @RequestParam(defaultValue = "Mullingar") String station) throws Exception {
        String rawXml = irishRailClient.getRawStationData(station);
        return irishRailXmlParser.parseStationData(rawXml);
    }

    public List<TrainObservation> fetchAndSaveObservations(String station) throws Exception {
        String rawXml = irishRailClient.getRawStationData(station);

        List<TrainObservation> observations = irishRailXmlParser.parseStationData(rawXml);

        List<TrainObservation> newObservations = new ArrayList<>();

        for (TrainObservation observation : observations) {
            boolean alreadyExists = trainObservationRepository.existsByStationCodeAndTrainCodeAndTrainDateAndQueryTime(
                    observation.getStationCode(),
                    observation.getTrainCode(),
                    observation.getTrainDate(),
                    observation.getQueryTime());

            if (!alreadyExists) {
                newObservations.add(observation);
            }
        }

        System.out.println("Fetched " + observations.size() + " observations for " + station);
        System.out.println("Saving " + newObservations.size() + " new observations for " + station);

        return trainObservationRepository.saveAll(newObservations);
    }

    public List<TrainObservation> getStoredObservations() {
        return trainObservationRepository.findAll();
    }

    public List<StationStats> getStationStats() {
        List<TrainObservation> observations =
            trainObservationRepository.findByIdGreaterThanEqual(minObservationIdForStats);

        Map<String, List<TrainObservation>> observationsByStation =
             observations.stream()
                .collect(Collectors.groupingBy(
                    observation -> observation.getStationCode() + "|" + observation.getStationFullName()

                    ));
        return observationsByStation.values().stream()
                    .map(stationObservations -> {
                        TrainObservation firstObservation = stationObservations.get(0);
                        long observationCount = stationObservations.size();

                        long delayedObservationCount = stationObservations.stream()
                            .filter(observation -> observation.getLate() > 0)
                            .count();

                        double averageLateMinutes = stationObservations.stream()
                            .mapToInt(TrainObservation::getLate)
                            .average()
                            .orElse(0.0);

                        int maxLateMinutes = stationObservations.stream()
                            .mapToInt(TrainObservation::getLate)
                            .max()
                            .orElse(0);

                        return new StationStats(
                            firstObservation.getStationFullName(),
                            firstObservation.getStationCode(),
                            observationCount,
                            delayedObservationCount,
                            roundToOneDecimalPlace(averageLateMinutes),
                            maxLateMinutes
                        );
                    })
                    .sorted(Comparator.comparing(StationStats::getStationName))
                    .toList();
                        
    }

    private double roundToOneDecimalPlace(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

}
