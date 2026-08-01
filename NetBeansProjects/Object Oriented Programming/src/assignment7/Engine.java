
package assignment7;

public class Engine {
    private String type;
    private int horsepower;

    public Engine(String type, int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
    }

    public String getDetails() {
        return type + " Engine (" + horsepower + " HP)";
    }
}