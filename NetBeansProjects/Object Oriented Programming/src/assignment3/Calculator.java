
package assignment3;

public class Calculator {
    public void calculate(int num1, int num2){
        int sum = num1 + num2;
        System.out.println("The sum: " + sum);
    }
    public void calculate(int num1, int num2, int num3){
        int sum = num1 + num2 + num3;
        System.out.println("The sum: " + sum);
    }
    public void calculate(double num1, double num2){
        double sum = num1 + num2;
        System.out.println("The sum: " + sum);
    }
    public void calculate(int num1, int num2, boolean multiply){
        if(multiply = true){
            int product = num1 * num2;
            System.out.println("The product: " + product);
        }
    }
}
