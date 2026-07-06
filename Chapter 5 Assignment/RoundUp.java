public static long roundToInteger(double number) {
    return (long) Math.floor(number + 0.5);
}

public static double roundToTenths(double number) {
    return Math.floor(number * 10 + 0.5) / 10;
}

public static double roundToHundredths(double number) {
    return Math.floor(number * 100 + 0.5) / 100;
}

public static double roundToThousandths(double number) {
    return Math.floor(number * 1000 + 0.5) / 1000;
}