package dev.slimtom.an_traein.model;

public class TrainStatus {
    private String origin;
    private String destination;
    private int delayMinutes;

    public TrainStatus(String origin, String destination, int delayMinutes) {
        this.origin = origin;
        this.destination = destination;
        this.delayMinutes = delayMinutes;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }
    
}
