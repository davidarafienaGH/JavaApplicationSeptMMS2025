public class Building implements CarbonFootprint {
    private double kwhConsumedPerYear;

    public Building(double kwhConsumedPerYear) {
        this.kwhConsumedPerYear = kwhConsumedPerYear;
    }

    @Override
    public double getCarbonFootprint() {
        return kwhConsumedPerYear * 0.85;
    }

    @Override
    public String toString() {
        return String.format("Building [Annual kWh Consumption: %,.2f]", kwhConsumedPerYear);
    }
}
