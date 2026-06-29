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
import dev.slimtom.an_traein.dto.StationServiceStats;

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
                        List<Integer> cleanLateValues = stationObservations.stream()
                            .map(observation -> cleanLateValue(observation.getLate()))
                            .filter(cleanLate -> cleanLate != null)
                            .toList();
                        long observationCount = cleanLateValues.size();

                        long delayedObservationCount = cleanLateValues.stream()
                            .filter(late -> late > 0)
                            .count();

                        double averageLateMinutes = cleanLateValues.stream()
                            .mapToInt(Integer::intValue)
                            .average()
                            .orElse(0.0);

                        int maxLateMinutes = cleanLateValues.stream()
                            .mapToInt(Integer::intValue)
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

    public List<StationServiceStats> getStationServiceStats() {
        List<TrainObservation> observations =
            trainObservationRepository.findByIdGreaterThanEqual(minObservationIdForStats);

        Map<String, List<TrainObservation>> observationsByService =
            observations.stream()
                .collect(Collectors.groupingBy(
                    observation ->
                        observation.getTrainDate() + "|" +
                        observation.getTrainCode() + "|" +
                        observation.getOrigin() + "|" +
                        observation.getDestination() + "|" +
                        observation.getOriginTime() + "|" +
                        observation.getStationCode()
                ));

        List<StationServiceEventSummary> stationServiceEvents =
                observationsByService.values().stream()
                    .map(serviceObservations -> {
                        TrainObservation firstObservation = serviceObservations.get(0);

                        List<Integer> cleanLateValues = serviceObservations.stream()
                            .map(observation -> cleanLateValue(observation.getLate()))
                            .filter(cleanLate -> cleanLate != null)
                            .toList();

                        if(cleanLateValues.isEmpty()) {
                            return null;
                        }

                        int maxLateMinutes = cleanLateValues.stream()
                            .mapToInt(Integer::intValue)
                            .max()
                            .orElse(0);

                        return new StationServiceEventSummary(
                            firstObservation.getStationFullName(),
                            firstObservation.getStationCode(),
                            maxLateMinutes
                        );
                    })
                    .filter(event -> event != null)
                    .toList();

        Map<String, List<StationServiceEventSummary>> eventsByStation =
            stationServiceEvents.stream()
                .collect(Collectors.groupingBy(
                    event -> event.stationCode() + "|" + event.stationName()
                ));

        return eventsByStation.values().stream()
            .map(stationEvents -> {
                StationServiceEventSummary firstEvent = stationEvents.get(0);

                long serviceCount = stationEvents.size();

                long delayedServiceCount = stationEvents.stream()
                    .filter(event -> event.maxLateMinutes() > 1)
                    .count();

                double delayedServicePercentage =
                    serviceCount == 0
                        ? 0.0
                        : ((double) delayedServiceCount / serviceCount) * 100.0;

                double averageMaxLateMinutes = stationEvents.stream()
                    .mapToInt(StationServiceEventSummary::maxLateMinutes)
                    .average()
                    .orElse(0.0);

                int maxLateMinutes = stationEvents.stream()
                    .mapToInt(StationServiceEventSummary::maxLateMinutes)
                    .max()
                    .orElse(0);

                return new StationServiceStats(
                    firstEvent.stationName(),
                    firstEvent.stationCode(),
                    serviceCount,
                    delayedServiceCount,
                    roundToOneDecimalPlace(delayedServicePercentage),
                    roundToOneDecimalPlace(averageMaxLateMinutes),
                    maxLateMinutes
                );
            })
            .sorted(Comparator.comparing(StationServiceStats::getStationName))
            .toList();
    }

    private record StationServiceEventSummary(
        String stationName,
        String stationCode,
        int maxLateMinutes
    ) {

    }

    private double roundToOneDecimalPlace(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    private Integer cleanLateValue(int rawLate) {
        if(rawLate >= 1380 && rawLate <= 1500) {
            return rawLate - 1440;
        }

        if(rawLate < -30) {
            return null;
        }

        return rawLate;
    }

}
