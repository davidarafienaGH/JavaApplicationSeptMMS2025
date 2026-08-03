public class ShapeTest {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[2];
        shapes[0] = new Circle(5.0);
        shapes[1] = new Sphere(3.0);

        for (Shape shape : shapes) {
            System.out.println("Description: " + shape);

            if (shape instanceof TwoDimensionalShape) {
                TwoDimensionalShape s2 = (TwoDimensionalShape) shape;
                System.out.printf("Shape Type: 2D%nArea: %.2f%n%n", s2.getArea());
            } else if (shape instanceof ThreeDimensionalShape) {
                ThreeDimensionalShape s3 = (ThreeDimensionalShape) shape;
                System.out.printf("Shape Type: 3D%nSurface Area: %.2f%nVolume: %.2f%n%n", 
                    s3.getArea(), s3.getVolume());
            }
        }
    }
}
