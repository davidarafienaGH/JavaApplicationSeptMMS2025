public class EmergencyTest {
    public static void main(String[] args) {
        Emergency em = new Emergency("INC-911-001", "555-0199", "123 Main St", Emergency.EmergencyType.FIRE);
        em.updateCoordinates(40.7128, -74.0060);
        System.out.println("Created Emergency Incident: " + em.getIncidentId());
        System.out.println("Initial Status: " + em.getStatus());
        
        em.setStatus(Emergency.ResponseStatus.DISPATCHED);
        System.out.println("Updated Status: " + em.getStatus());
    }
}
