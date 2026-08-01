
package assignment7;

public class Car2 {
    private String make;
    private String model;
    private Engine engine; // Composition: Car HAS-A Engine

    public Car2(String make, String model, String engineType, int horsepower) {
        this.make = make;
        this.model = model;
        this.engine = new Engine(engineType, horsepower);
    }

    public void displayCarDetails() {
        System.out.println("Car: " + make + " " + model);
        System.out.println("Engine Spec: " + engine.getDetails());
    }
}