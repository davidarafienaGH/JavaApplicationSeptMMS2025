public class Time2Test {
    public static void main(String[] args) {
        // Case A: Increment into next minute
        Time2 t1 = new Time2(11, 58, 59);
        System.out.println("Before tick: " + t1.toUniversalString());
        t1.tick();
        System.out.println("After tick:  " + t1.toUniversalString());

        // Case B: Increment into next hour
        Time2 t2 = new Time2(11, 59, 59);
        System.out.println("
Before tick: " + t2.toUniversalString());
        t2.tick();
        System.out.println("After tick:  " + t2.toUniversalString());

        // Case C: Increment into next day
        Time2 t3 = new Time2(23, 59, 59);
        System.out.println("
Before tick: " + t3.toString());
        t3.tick();
        System.out.println("After tick:  " + t3.toString());
    }
}
