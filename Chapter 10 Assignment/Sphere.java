public class Sphere extends ThreeDimensionalShape {
    private double radius;

    public Sphere(double radius) { this.radius = radius; }

    @Override
    public double getArea() { return 4 * Math.PI * radius * radius; }

    @Override
    public double getVolume() { return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3); }

    @Override
    public String toString() {
        return String.format("%s [Radius: %.2f]", super.toString(), radius);
    }
}
