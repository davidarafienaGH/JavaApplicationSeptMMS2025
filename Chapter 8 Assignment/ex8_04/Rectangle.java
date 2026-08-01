public class Rectangle {
    private double length = 1.0;
    private double width = 1.0;

    public Rectangle() {}

    public Rectangle(double length, double width) {
        setLength(length);
        setWidth(width);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length <= 0.0 || length >= 20.0) {
            throw new IllegalArgumentException("Length must be > 0.0 and < 20.0");
        }
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width <= 0.0 || width >= 20.0) {
            throw new IllegalArgumentException("Width must be > 0.0 and < 20.0");
        }
        this.width = width;
    }

    public double calculatePerimeter() {
        return 2 * (length + width);
    }

    public double calculateArea() {
        return length * width;
    }
}
