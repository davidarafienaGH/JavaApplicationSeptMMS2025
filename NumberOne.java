// 1. collect 5 numbers from a user and display the sum, average, product of the five numbers.

import java.util.Scanner;

public class NumberOne{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
	
		System.out.print("Enter a number: ");
		int num1 = input.nextInt();
		
		System.out.print("Enter a number: ");
		int num2 = input.nextInt();
		
		System.out.print("Enter a number: ");
		int num3 = input.nextInt();
		
		System.out.print("Enter a number: ");
		int num4 = input.nextInt();
		
		System.out.print("Enter a number: ");
		int num5 = input.nextInt();
		
		int sum = num1 + num2 + num3 + num4 + num5;
		double average = (double)(num1 + num2 + num3 + num4 + num5)/5;
		int product = num1 * num2 * num3 * num4 * num5;
		
		System.out.printf("The sum is %d%n",sum);
		System.out.printf("The average is %f%n",average);
		System.out.printf("The product is %d%n",product);
	}
}	