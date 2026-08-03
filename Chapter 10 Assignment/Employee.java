public abstract class Employee implements Payable {
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private Date birthDate;

    public Employee(String firstName, String lastName, String socialSecurityNumber, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.birthDate = birthDate;
    }

    public Employee(String firstName, String lastName, String socialSecurityNumber) {
        this(firstName, lastName, socialSecurityNumber, null);
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSocialSecurityNumber() { return socialSecurityNumber; }
    public Date getBirthDate() { return birthDate; }

    public abstract double earnings();

    @Override
    public double getPaymentAmount() {
        return earnings();
    }

    @Override
    public String toString() {
        return String.format("%s %s%nSSN: %s%s", 
            getFirstName(), getLastName(), getSocialSecurityNumber(),
            (birthDate != null ? String.format("%nBirthdate: %s", birthDate) : ""));
    }
}
