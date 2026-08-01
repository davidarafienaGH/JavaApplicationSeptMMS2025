public class RationalTest {
    public static void main(String[] args) {
        Rational r1 = new Rational(2, 4);
        Rational r2 = new Rational(3, 5);

        System.out.println("r1: " + r1);
        System.out.println("r2: " + r2);
        System.out.println("r1 + r2 = " + Rational.add(r1, r2));
        System.out.println("r1 - r2 = " + Rational.subtract(r1, r2));
        System.out.println("r1 * r2 = " + Rational.multiply(r1, r2));
        System.out.println("r1 / r2 = " + Rational.divide(r1, r2));
        System.out.println("r1 float (3 digits): " + r1.toFloatingPointString(3));
    }
}
