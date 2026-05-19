// 2. write a java program to accept a number from a user and check if the number is an even number, if it is even, display True, else, display false.

import java.util.Scanner;

public class NumberTwo{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter a number: ");
		int num = input.nextInt();
		
		boolean evenNumber = num % 2 == 0; 
		System.out.printf("Is the number an even number? %b%n",evenNumber);
		
	}
}
