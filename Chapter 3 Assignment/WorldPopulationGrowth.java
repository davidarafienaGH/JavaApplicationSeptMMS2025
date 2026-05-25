public class WorldPopulationGrowth {

    public static void main(String[] args) {

        // Current world population (approx. 8.3 billion)
        double population = 8_300_000_000.0;

        // Annual growth rate: 0.84%
        double growthRate = 0.0084;

        double originalPopulation = population;

        int doublingYear = 0;

        System.out.printf("%-10s%-25s%-25s%n",
                "Year", "Population", "Increase");

        for (int year = 1; year <= 75; year++) {

            double increase = population * growthRate;

            population += increase;

            System.out.printf("%-10d%-25.0f%-25.0f%n",
                    year, population, increase);

            // Determine when population doubles
            if (doublingYear == 0 &&
                    population >= (2 * originalPopulation)) {

                doublingYear = year;
            }
        }

        System.out.println();

        if (doublingYear > 0) {
            System.out.println(
                "Population will double in approximately year "
                + doublingYear + "."
            );
        } else {
            System.out.println(
                "Population did not double within 75 years."
            );
        }
    }
}