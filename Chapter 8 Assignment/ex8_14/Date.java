public class Date {
    private int month;
    private int day;
    private int year;

    private static final String[] MONTH_NAMES = {
        "", "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    private static final int[] daysPerMonth =
        { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date(String monthName, int day, int year) {
        this.year = year;
        this.day = day;
        this.month = 1;
        for (int i = 1; i <= 12; i++) {
            if (MONTH_NAMES[i].equalsIgnoreCase(monthName)) {
                this.month = i;
                break;
            }
        }
    }

    public Date(int dayOfYear, int year) {
        this.year = year;
        int m = 1;
        while (dayOfYear > daysPerMonth[m]) {
            dayOfYear -= daysPerMonth[m];
            m++;
        }
        this.month = m;
        this.day = dayOfYear;
    }

    public String toFormattedString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }

    public String toLongString() {
        return String.format("%s %d, %d", MONTH_NAMES[month], day, year);
    }

    public String toDayOfYearString() {
        int dayOfYear = day;
        for (int i = 1; i < month; i++) {
            dayOfYear += daysPerMonth[i];
        }
        return String.format("%03d %d", dayOfYear, year);
    }
}
