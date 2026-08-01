public class Complex {
    private final double real;
    private final double imaginary;

    public Complex() {
        this(0.0, 0.0);
    }

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex add(Complex right) {
        return new Complex(this.real + right.real, this.imaginary + right.imaginary);
    }

    public Complex subtract(Complex right) {
        return new Complex(this.real - right.real, this.imaginary - right.imaginary);
    }

    public String toString() {
        return String.format("(%.2f, %.2f)", real, imaginary);
    }
}
