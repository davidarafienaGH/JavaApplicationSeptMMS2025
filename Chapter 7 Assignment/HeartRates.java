import java.time.LocalDate;

public class HeartRates {

    private String firstName;
    private String lastName;
    private int birthMonth;
    private int birthDay;
    private int birthYear;

    // Constructor
    public HeartRates(String firstName, String lastName,
                      int birthMonth, int birthDay, int birthYear) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public int getBirthYear() {
        return birthYear;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    // Calculate age
    public int getAge() {

        LocalDate today = LocalDate.now();

        int age = today.getYear() - birthYear;

        if (today.getMonthValue() < birthMonth ||
                (today.getMonthValue() == birthMonth &&
                 today.getDayOfMonth() < birthDay)) {

            age--;
        }

        return age;
    }

    // Maximum Heart Rate
    public int getMaximumHeartRate() {

        return 220 - getAge();

    }

    // Target Heart Rate (Lower Limit)
    public double getMinimumTargetHeartRate() {

        return getMaximumHeartRate() * 0.50;

    }

    // Target Heart Rate (Upper Limit)
    public double getMaximumTargetHeartRate() {

        return getMaximumHeartRate() * 0.85;

    }

}