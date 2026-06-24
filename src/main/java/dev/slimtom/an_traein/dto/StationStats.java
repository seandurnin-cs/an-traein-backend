package dev.slimtom.an_traein.dto;

public class StationStats {
    private final String stationName;
    private final String stationCode;
    private final long observationCount;
    private final long delayedObservationCount;
    private final double averageLateMinutes;
    private final int maxLateMinutes;

    public StationStats(
            String stationName,
            String stationCode,
            long observationCount,
            long delayedObservationCount,
            double averageLateMinutes,
            int maxLateMinutes) {
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.observationCount = observationCount;
        this.delayedObservationCount = delayedObservationCount;
        this.averageLateMinutes = averageLateMinutes;
        this.maxLateMinutes = maxLateMinutes;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public long getObservationCount() {
        return observationCount;
    }

    public long getDelayedObservationCount() {
        return delayedObservationCount;
    }

    public double getAverageLateMinutes() {
        return averageLateMinutes;
    }

    public int getMaxLateMinutes() {
        return maxLateMinutes;
    }
}
