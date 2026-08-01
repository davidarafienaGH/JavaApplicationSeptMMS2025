public class Date {
    private int month;
    private int day;
    private int year;

    private static final int[] daysPerMonth =
        { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public Date(int month, int day, int year) {
        if (year < 0) {
            throw new IllegalArgumentException("year must be >= 0");
        }
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException("month (" + month + ") must be 1-12");
        }

        int maxDays = daysPerMonth[month];
        if (month == 2 && isLeapYear(year)) {
            maxDays = 29;
        }

        if (day <= 0 || day > maxDays) {
            throw new IllegalArgumentException("day (" + day + ") out-of-range for month and year");
        }

        this.month = month;
        this.day = day;
        this.year = year;
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    public void nextDay() {
        int maxDays = daysPerMonth[month];
        if (month == 2 && isLeapYear(year)) {
            maxDays = 29;
        }

        day++;
        if (day > maxDays) {
            day = 1;
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
    }

    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }
}
