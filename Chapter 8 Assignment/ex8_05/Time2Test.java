public class Time2Test {
    public static void main(String[] args) {
        Time2 t1 = new Time2();
        Time2 t2 = new Time2(2);
        Time2 t3 = new Time2(21, 34);
        Time2 t4 = new Time2(12, 25, 42);
        Time2 t5 = new Time2(t4);

        System.out.println("Constructed with:");
        System.out.println("t1: all default arguments -> " + t1.toUniversalString() + " | " + t1.toString());
        System.out.println("t2: hour specified; default minute and second -> " + t2.toUniversalString() + " | " + t2.toString());
        System.out.println("t3: hour and minute specified; default second -> " + t3.toUniversalString() + " | " + t3.toString());
        System.out.println("t4: hour, minute and second specified -> " + t4.toUniversalString() + " | " + t4.toString());
        System.out.println("t5: Time2 object t4 specified -> " + t5.toUniversalString() + " | " + t5.toString());
    }
}
