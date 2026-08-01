public class DateAndTimeTest {
    public static void main(String[] args) {
        DateAndTime dt = new DateAndTime(12, 31, 2024, 23, 59, 59);
        System.out.println("Initial DateAndTime: " + dt);
        
        dt.tick();
        System.out.println("After ticking into next day: " + dt);

        dt = new DateAndTime(5, 15, 2024, 23, 30, 0);
        System.out.println("
Before incrementing hour: " + dt);
        dt.incrementHour();
        System.out.println("After incrementing hour: " + dt);
    }
}
