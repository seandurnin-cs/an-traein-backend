package dev.slimtom.an_traein.controller;

import dev.slimtom.an_traein.model.TrainObservation;
import dev.slimtom.an_traein.service.TrainObservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IrishRailController {

    private final TrainObservationService trainObservationService;

    public IrishRailController(TrainObservationService trainObservationService) {
        this.trainObservationService = trainObservationService;
    }

    @GetMapping("/irishrail/observations/save")
    public List<TrainObservation> getIrishRailObservations(
            @RequestParam(defaultValue = "Mullingar") String station) throws Exception {
        return trainObservationService.fetchAndSaveObservations(station);
    }
}
