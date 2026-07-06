public static int celsius(int fahrenheit) {
    return (int) (5.0 / 9.0 * (fahrenheit - 32));
}

public static int fahrenheit(int celsius) {
    return (int) (9.0 / 5.0 * celsius + 32);
}