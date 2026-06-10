package dev.slimtom.an_traein.controller;

import dev.slimtom.an_traein.model.TrainStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainController {
    
    @GetMapping("/trains")
    public List<TrainStatus> getTrains() {
        return List.of(
            new TrainStatus("Mullingar", "Maynooth", 4),
            new TrainStatus("Mullingar", "Maynooth", 8),
            new TrainStatus("Maynooth", "Mullingar", 0)
        );
    }
}
