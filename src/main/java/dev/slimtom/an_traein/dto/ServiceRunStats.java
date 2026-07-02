package dev.slimtom.an_traein.dto;

public class ServiceRunStats {

    private final String trainCode;
    private final String trainDate;
    private final String origin;
    private final String destination;
    private final String originTime;
    private final long stationCount;
    private final double averageMaxLateMinutes;
    private final int maxLateMinutes;

    public ServiceRunStats(
            String trainCode,
            String trainDate,
            String origin,
            String destination,
            String originTime,
            long stationCount,
            double averageMaxLateMinutes,
            int maxLateMinutes
    ) {
        this.trainCode = trainCode;
        this.trainDate = trainDate;
        this.origin = origin;
        this.destination = destination;
        this.originTime = originTime;
        this.stationCount = stationCount;
        this.averageMaxLateMinutes = averageMaxLateMinutes;
        this.maxLateMinutes = maxLateMinutes;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getOriginTime() {
        return originTime;
    }

    public long getStationCount() {
        return stationCount;
    }

    public double getAverageMaxLateMinutes() {
        return averageMaxLateMinutes;
    }

    public int getMaxLateMinutes() {
        return maxLateMinutes;
    }
}
