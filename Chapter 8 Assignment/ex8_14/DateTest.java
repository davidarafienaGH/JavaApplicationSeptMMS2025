public class DateTest {
    public static void main(String[] args) {
        Date d1 = new Date(6, 14, 1992);
        Date d2 = new Date("June", 14, 1992);
        Date d3 = new Date(166, 1992);

        System.out.println("Format A: " + d1.toFormattedString());
        System.out.println("Format B: " + d2.toLongString());
        System.out.println("Format C: " + d3.toDayOfYearString());
    }
}
