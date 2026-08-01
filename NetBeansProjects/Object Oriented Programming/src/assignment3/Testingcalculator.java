package assignment3;

public class Testingcalculator {
    public static void main(String[] args){
        Calculator calculator = new Calculator();
    
        calculator.calculate(10,20); 
        calculator.calculate(10,20,30);
        calculator.calculate(10,20,true);
        calculator.calculate(20.15,20.25);
        
    }
}

