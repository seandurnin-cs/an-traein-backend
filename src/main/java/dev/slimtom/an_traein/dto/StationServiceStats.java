package dev.slimtom.an_traein.dto;

public class StationServiceStats {

    private final String stationName;
    private final String stationCode;
    private final long serviceCount;
    private final long delayedServiceCount;
    private final double delayedServicePercentage;
    private final double averageMaxLateMinutes;
    private final int maxLateMinutes;

    private final long onTimeServiceCount;
    private final long minorDelayServiceCount;
    private final long moderateDelayServiceCount;
    private final long signifficantDelayServiceCount;
    private final long majorDelayServiceCount;

    private final double onTimePercentage;
    private final double minorDelayPercentage;
    private final double moderateDelayPercentage;
    private final double signifficantDelayPercentage;
    private final double majorDelayPercentage;

    public StationServiceStats(
            String stationName,
            String stationCode,
            long serviceCount,
            long delayedServiceCount,
            double delayedServicePercentage,
            double averageMaxLateMinutes,
            int maxLateMinutes,
            long onTimeServiceCount,
            long minorDelayServiceCount,
            long moderateDelayServiceCount,
            long significantDelayServiceCount,
            long majorDelayServiceCount,
            double onTimePercentage,
            double minorDelayPercentage,
            double moderateDelayPercentage,
            double significantDelayPercentage,
            double majorDelayPercentage) {
        this.stationName = stationName;
        this.stationCode = stationCode;
        this.serviceCount = serviceCount;
        this.delayedServiceCount = delayedServiceCount;
        this.delayedServicePercentage = delayedServicePercentage;
        this.averageMaxLateMinutes = averageMaxLateMinutes;
        this.maxLateMinutes = maxLateMinutes;
        this.onTimeServiceCount = onTimeServiceCount;
        this.minorDelayServiceCount = minorDelayServiceCount;
        this.moderateDelayServiceCount = moderateDelayServiceCount;
        this.significantDelayServiceCount = significantDelayServiceCount;
        this.majorDelayServiceCount = majorDelayServiceCount;
        this.onTimePercentage = onTimePercentage;
        this.minorDelayPercentage = minorDelayPercentage;
        this.moderateDelayPercentage = moderateDelayPercentage;
        this.significantDelayPercentage = significantDelayPercentage;
        this.majorDelayPercentage = majorDelayPercentage;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public long getServiceCount() {
        return serviceCount;
    }

    public long getDelayedServiceCount() {
        return delayedServiceCount;
    }

    public double getDelayedServicePercentage() {
        return delayedServicePercentage;
    }

    public double getAverageMaxLateMinutes() {
        return averageMaxLateMinutes;
    }

    public int getMaxLateMinutes() {
        return maxLateMinutes;
    }

    public long getOnTimeServiceCount() {
        return onTimeServiceCount;
    }

    public long getMinorDelayServiceCount() {
        return minorDelayServiceCount;
    }

    public long getModerateDelayServiceCount() {
        return moderateDelayServiceCount;
    }

    public long getSignificantDelayServiceCount() {
        return significantDelayServiceCount;
    }

    public long getMajorDelayServiceCount() {
        return majorDelayServiceCount;
    }

    public double getOnTimePercentage() {
        return onTimePercentage;
    }

    public double getMinorDelayPercentage() {
        return minorDelayPercentage;
    }

    public double getModerateDelayPercentage() {
        return moderateDelayPercentage;
    }

    public double getSignificantDelayPercentage() {
        return significantDelayPercentage;
    }

    public double getMajorDelayPercentage() {
        return majorDelayPercentage;
    }
}
