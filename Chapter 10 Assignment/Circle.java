public class Circle extends TwoDimensionalShape {
    private double radius;

    public Circle(double radius) { this.radius = radius; }

    @Override
    public double getArea() { return Math.PI * radius * radius; }

    @Override
    public String toString() {
        return String.format("%s [Radius: %.2f]", super.toString(), radius);
    }
}
