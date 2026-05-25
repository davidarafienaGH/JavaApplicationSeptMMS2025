// Write a java program to accept a day in numbers, if the person enters 1 = Sunday, 2= Monday, 3= Tuesday, etc...
import java.util.Scanner;
public class Day{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		int day;
		
		System.out.print("Enter a number from 1 to 7: ");
		day = input.nextInt();
		
		if(day ==  1){
			System.out.print("The day is Sunday");
		}
		else if(day == 2){
			System.out.print("The day is Monday");
		}
		else if(day == 3){
			System.out.print("The day is Tuesday");
		}
		else if(day == 4){
			System.out.print("The day is Wednesday");
		}
		else if(day == 5){
			System.out.print("The day is Thursday");
		}
		else if(day == 6){
			System.out.print("The day is Friday");
		}
		else if(day == 7){
			System.out.print("The day is Saturday");
		}
		else{
			System.out.print("Invalid Input")
		}
	}
}