import java.util.ArrayList;

public class CarbonFootprintTest {
    public static void main(String[] args) {
        ArrayList<CarbonFootprint> categories = new ArrayList<>();

        categories.add(new Building(12000.0));
        categories.add(new Car(15000.0, 25.0));
        categories.add(new Bicycle(1200.0));

        System.out.println("Annual Carbon Footprint Calculations:\n");

        for (CarbonFootprint item : categories) {
            System.out.println(item);
            System.out.printf("Carbon Footprint: %,.2f lbs of CO2%n%n", item.getCarbonFootprint());
        }
    }
}
