public class Car implements CarbonFootprint {
    private double milesDrivenPerYear;
    private double milesPerGallon;

    public Car(double milesDrivenPerYear, double milesPerGallon) {
        this.milesDrivenPerYear = milesDrivenPerYear;
        this.milesPerGallon = milesPerGallon;
    }

    @Override
    public double getCarbonFootprint() {
        return (milesDrivenPerYear / milesPerGallon) * 19.6;
    }

    @Override
    public String toString() {
        return String.format("Car [Annual Miles: %,.2f, MPG: %.1f]", milesDrivenPerYear, milesPerGallon);
    }
}
