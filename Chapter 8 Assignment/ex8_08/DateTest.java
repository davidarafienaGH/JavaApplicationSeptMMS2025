public class DateTest {
    public static void main(String[] args) {
        Date d1 = new Date(1, 30, 2024);
        System.out.println("Month rollover test:");
        for (int i = 0; i < 3; i++) {
            System.out.println(d1);
            d1.nextDay();
        }

        Date d2 = new Date(12, 30, 2024);
        System.out.println("
Year rollover test:");
        for (int i = 0; i < 3; i++) {
            System.out.println(d2);
            d2.nextDay();
        }
    }
}
