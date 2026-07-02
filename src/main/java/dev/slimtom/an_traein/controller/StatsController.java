package dev.slimtom.an_traein.controller;

import dev.slimtom.an_traein.dto.StationStats;
import dev.slimtom.an_traein.dto.StationServiceStats;
import dev.slimtom.an_traein.dto.ServiceRunStats;
import dev.slimtom.an_traein.service.TrainObservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatsController {

    private final TrainObservationService trainObservationService;

    public StatsController(TrainObservationService trainObservationService) {
        this.trainObservationService = trainObservationService;
    }

    @GetMapping("/stats/stations/observations")
    public List<StationStats> getStationStats() {
        return trainObservationService.getStationStats();
    }

    @GetMapping("/stats/stations/services")
    public List<StationServiceStats> getStationServiceStats() {
        return trainObservationService.getStationServiceStats();
    }

    @GetMapping("/stats/services/runs")
    public List<ServiceRunStats> getServiceStats() {
        return trainObservationService.getServiceStats();
    }

}