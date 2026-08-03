public class Date {
    private int month;
    private int day;
    private int year;

    public Date(int month, int day, int year) {
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException("Month must be 1-12");
        }
        if (day <= 0 || day > 31) {
            throw new IllegalArgumentException("Day must be 1-31");
        }
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be greater than 0");
        }

        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() { return month; }
    public int getDay() { return day; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }
}
