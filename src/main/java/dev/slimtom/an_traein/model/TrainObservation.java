package dev.slimtom.an_traein.model;

public class TrainObservation {

    private String serverTime;
    private String trainCode;
    private String stationFullName;
    private String stationCode;
    private String queryTime;
    private String trainDate;
    private String origin;
    private String destination;
    private String originTime;
    private String destinationTime;
    private String status;
    private String lastLocation;
    private int dueIn;
    private int late;
    private String expArrival;
    private String expDepart;
    private String schArrival;
    private String schDepart;
    private String direction;
    private String trainType;
    private String locationType;

    public TrainObservation(String serverTime, String trainCode, String stationFullName, String stationCode,
            String queryTime, String trainDate, String origin, String destination, String originTime,
            String destinationTime, String status,
            String lastLocation, int dueIn, int late,
            String expArrival, String expDepart, String schArrival, String schDepart, String direction, String trainType,
            String locationType) {
        this.serverTime = serverTime;
        this.trainCode = trainCode;
        this.stationFullName = stationFullName;
        this.stationCode = stationCode;
        this.queryTime = queryTime;
        this.trainDate = trainDate;
        this.origin = origin;
        this.destination = destination;
        this.originTime = originTime;
        this.destinationTime = destinationTime;
        this.status = status;
        this.lastLocation = lastLocation;
        this.dueIn = dueIn;
        this.late = late;
        this.expArrival = expArrival;
        this.expDepart = expDepart;
        this.schArrival = schArrival;
        this.schDepart = schDepart;
        this.direction = direction;
        this.trainType = trainType;
        this.locationType = locationType;
    }

    public String getServerTime() {
        return serverTime;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public String getStationFullName() {
        return stationFullName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public String getQueryTme() {
        return queryTime;
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

    public String getDestinationTime() {
        return destinationTime;
    }

    public String getStatus() {
        return status;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public int getDueIn() {
        return dueIn;
    }

    public int getLate() {
        return late;
    }

    public String getExpArrival() {
        return expArrival;
    }

    public String getExpDepart() {
        return expDepart;
    }

    public String getSchArrival() {
        return schArrival;
    }

    public String getSchDepart() {
        return schDepart;
    }

    public String getDirection() {
        return direction;
    }

    public String getTrainType() {
        return trainType;
    }

    public String getObservedAt() {
        return locationType;
    }
}
