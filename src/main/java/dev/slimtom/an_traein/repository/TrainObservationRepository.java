package dev.slimtom.an_traein.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.slimtom.an_traein.model.TrainObservation;

import java.util.List;

public interface TrainObservationRepository extends JpaRepository<TrainObservation, Long> {
    boolean existsByStationCodeAndTrainCodeAndTrainDateAndQueryTime(
        String stationCode,
        String trainCode,
        String trainDate,
        String queryTime
    );

    List<TrainObservation> findByIdGreaterThanEqual(long id);
}
