package dev.slimtom.an_traein.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.slimtom.an_traein.model.TrainObservation;

public interface TrainObservationRepository extends JpaRepository<TrainObservation, Long> {
    
}
