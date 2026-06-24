package dev.slimtom.an_traein.scheduler;

import dev.slimtom.an_traein.service.TrainObservationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.List;

@Component
@ConditionalOnProperty(name = "antraein.tracker.enabled", havingValue = "true")
public class TrainObservationScheduler {
    
    private final TrainObservationService trainObservationService;

    private final List<String> stationsToTrack = List.of(
        "Dublin Connolly",
        "Drumcondra",
        "Broombridge",
        "Maynooth",
        "Kilcock",
        "Enfield",
        "Mullingar",
        "Edgeworthstown",
        "Longford",
        "Dromod",
        "Carrick On Shannon",
        "Boyle",
        "Ballymote",
        "Collooney",
        "Sligo"
    );

    public TrainObservationScheduler(TrainObservationService trainObservationService) {
        this.trainObservationService = trainObservationService;
    }

    @Scheduled(fixedRate = 60000)
    public void collectTrainObservations() {
        for(String station : stationsToTrack) {
            try {
                trainObservationService.fetchAndSaveObservations(station);
                System.out.println("Saved observations for station: " + station);
            } catch (Exception e) {
                System.out.println("Failed to save observations for station: " + station);
                e.printStackTrace();
            }
        }
    }
}
