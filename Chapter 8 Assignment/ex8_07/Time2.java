public class Time2 {
    private int hour;
    private int minute;
    private int second;

    public Time2() {
        this(0, 0, 0);
    }

    public Time2(int hour, int minute, int second) {
        setTime(hour, minute, second);
    }

    public void setTime(int hour, int minute, int second) {
        if (hour < 0 || hour >= 24) throw new IllegalArgumentException("hour must be 0-23");
        if (minute < 0 || minute >= 60) throw new IllegalArgumentException("minute must be 0-59");
        if (second < 0 || second >= 60) throw new IllegalArgumentException("second must be 0-59");
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public void tick() {
        second++;
        if (second >= 60) {
            second = 0;
            incrementMinute();
        }
    }

    public void incrementMinute() {
        minute++;
        if (minute >= 60) {
            minute = 0;
            incrementHour();
        }
    }

    public void incrementHour() {
        hour = (hour + 1) % 24;
    }

    public int getHour() { return hour; }
    public int getMinute() { return minute; }
    public int getSecond() { return second; }

    public String toUniversalString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public String toString() {
        return String.format("%d:%02d:%02d %s",
            ((hour == 0 || hour == 12) ? 12 : hour % 12),
            minute, second, (hour < 12 ? "AM" : "PM"));
    }
}
