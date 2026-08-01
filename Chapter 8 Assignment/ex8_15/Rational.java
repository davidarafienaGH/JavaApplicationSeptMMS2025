public class Rational {
    private final int numerator;
    private final int denominator;

    public Rational() {
        this(0, 1);
    }

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        int sign = (denominator < 0) ? -1 : 1;
        this.numerator = sign * (numerator / gcd);
        this.denominator = sign * (denominator / gcd);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static Rational add(Rational r1, Rational r2) {
        int num = r1.numerator * r2.denominator + r2.numerator * r1.denominator;
        int den = r1.denominator * r2.denominator;
        return new Rational(num, den);
    }

    public static Rational subtract(Rational r1, Rational r2) {
        int num = r1.numerator * r2.denominator - r2.numerator * r1.denominator;
        int den = r1.denominator * r2.denominator;
        return new Rational(num, den);
    }

    public static Rational multiply(Rational r1, Rational r2) {
        return new Rational(r1.numerator * r2.numerator, r1.denominator * r2.denominator);
    }

    public static Rational divide(Rational r1, Rational r2) {
        return new Rational(r1.numerator * r2.denominator, r1.denominator * r2.numerator);
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public String toFloatingPointString(int digits) {
        double val = (double) numerator / denominator;
        return String.format("%." + digits + "f", val);
    }
}
