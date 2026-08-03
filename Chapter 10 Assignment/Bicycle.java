public class Bicycle implements CarbonFootprint {
    private double milesRiddenPerYear;

    public Bicycle(double milesRiddenPerYear) {
        this.milesRiddenPerYear = milesRiddenPerYear;
    }

    @Override
    public double getCarbonFootprint() {
        return milesRiddenPerYear * 0.035;
    }

    @Override
    public String toString() {
        return String.format("Bicycle [Annual Miles Ridden: %,.2f]", milesRiddenPerYear);
    }
}
