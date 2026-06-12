package dev.slimtom.an_traein.model;

public class TrainObservation {

    private String trainCode;
    private String origin;
    private String destination;
    private String station;
    private String boardType;
    private String scheduledTime;
    private String expectedTime;
    private int delayMinutes;
    private String observedAt;

    public TrainObservation(String trainCode, String origin, String destination, String station, String boardType, String scheduledTime, String expectedTime, int delayMinutes, String observedAt) {
        this.trainCode = trainCode;
        this.origin = origin;
        this.destination = destination;
        this.station = station;
        this.boardType = boardType;
        this.scheduledTime = scheduledTime;
        this.expectedTime = expectedTime;
        this.delayMinutes = delayMinutes;
        this.observedAt = observedAt;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getStation() {
        return station;
    }

    public String getBoardType() {
        return boardType;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public int getDelayMinutes() {
        return delayMinutes;
    }

    public String getObservedAt() {
        return observedAt;
    }
}
