//5.8

public static double calculateCharges(double hours) {
    if (hours <= 3.0) {
        return 2.00;
    }
    // Math.ceil deals with "part thereof"
    double charge = 2.00 + Math.ceil(hours - 3.0) * 0.50; 
    return Math.min(charge, 10.00); // Caps charge at maximum $10.00
}