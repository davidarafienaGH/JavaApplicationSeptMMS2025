import java.time.LocalDateTime;

public class Emergency {
    public enum EmergencyType { POLICE, AMBULANCE, FIRE, MULTI_AGENCY }
    public enum ResponseStatus { REPORTED, DISPATCHED, ON_SCENE, RESOLVED, CANCELLED }

    private final String incidentId;
    private String callerPhone;
    private String locationAddress;
    private double latitude;
    private double longitude;
    private LocalDateTime reportTime;
    private EmergencyType type;
    private ResponseStatus status;

    public Emergency(String incidentId, String callerPhone, String locationAddress, EmergencyType type) {
        this.incidentId = incidentId;
        this.callerPhone = callerPhone;
        this.locationAddress = locationAddress;
        this.type = type;
        this.reportTime = LocalDateTime.now();
        this.status = ResponseStatus.REPORTED;
    }

    public void updateCoordinates(double lat, double lon) {
        this.latitude = lat;
        this.longitude = lon;
    }

    public void setStatus(ResponseStatus newStatus) {
        this.status = newStatus;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getIncidentId() {
        return incidentId;
    }
}
