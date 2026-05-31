// 3. write a java program to accept name, age and address and format the data to give a meaningful message on the screen

import java.util.Scanner;

public class NumberThree{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter your name: ");
		String name = input.nextLine();
		
		System.out.print("Enter your address: ");
		String address = input.nextLine();
		
		System.out.print("Enter your age: ");
		byte age = input.nextByte();
		
		
		System.out.printf("Hello %s!%n",name);
		System.out.printf("You are %d years old%n",age);
		System.out.printf("Your address is %s%n",address);
		
		
		
	}
}