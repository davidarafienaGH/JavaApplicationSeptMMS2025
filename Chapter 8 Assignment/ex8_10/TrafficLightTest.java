public class TrafficLightTest {
    public static void main(String[] args) {
        System.out.println("Traffic Light Durations:");
        for (TrafficLight light : TrafficLight.values()) {
            System.out.printf("%-6s : %d seconds%n", light, light.getDurationSeconds());
        }
    }
}
