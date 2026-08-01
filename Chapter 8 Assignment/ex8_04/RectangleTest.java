public class RectangleTest {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(5.5, 4.0);
        System.out.printf("Initial - Length: %.2f, Width: %.2f%n", rect.getLength(), rect.getWidth());
        System.out.printf("Perimeter: %.2f, Area: %.2f%n", rect.calculatePerimeter(), rect.calculateArea());

        try {
            rect.setLength(25.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
