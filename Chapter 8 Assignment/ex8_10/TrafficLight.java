public enum TrafficLight {
    RED(30),
    GREEN(45),
    YELLOW(5);

    private final int durationSeconds;

    TrafficLight(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }
}
