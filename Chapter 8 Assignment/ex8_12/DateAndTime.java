public class DateAndTime {
    private Date date;
    private Time2 time;

    public DateAndTime(int month, int day, int year, int hour, int minute, int second) {
        this.date = new Date(month, day, year);
        this.time = new Time2(hour, minute, second);
    }

    public void incrementHour() {
        int oldHour = time.getHour();
        time.incrementHour();
        if (time.getHour() < oldHour) { 
            date.nextDay();
        }
    }

    public void tick() {
        int oldHour = time.getHour();
        time.tick();
        if (time.getHour() < oldHour) {
            date.nextDay();
        }
    }

    public String toUniversalString() {
        return date.toString() + " " + time.toUniversalString();
    }

    public String toString() {
        return date.toString() + " " + time.toString();
    }
}
