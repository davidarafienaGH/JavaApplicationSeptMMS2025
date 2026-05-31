import java.util.Scanner;

public class EvenOrOddChecker{
	public static void main(String[] Args){
		Scanner scan = new Scanner(System.in);
		
		char option;
		do{
			System.out.print("Enter any number: ");
			int num = scan.nextInt();
			
			if(num % 2 == 0){
				System.out.printf("The number %d is an even number%n");
			}
			else{
				System.out.printf("The number %d is an odd number%n");
			}
			
			System.out.print("Do you want to run the program again(Y/N): ");
			option = scan.next().charAt(0);
		}while(option == 'Y' || option == 'y');
		System.out.println("Good bye..................");
	}
}