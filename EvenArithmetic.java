import java.util.Scanner;

public class EvenArithmetic{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		int sum = 0;
		
		for(int i = 1; i <= 3; i++){
			System.out.print("Enter a number: ");
			int num = input.nextInt();
			
			
			sum += num;
		}	
		if(sum % 2 == 0 && sum % 10 == 0){
			int product = 1;
				
			for(int f = 1; f <= 3; f++){
				System.out.print("Enter a number: ");
				int number = input.nextInt();
					
					
				product *= number;
			}	
			if(product > 200){
				System.out.println("Hurray!!");
			}else{
				System.out.println("Ongoing");
			}
					
		}	else{
				System.out.println("I must solve this program on my own!!!");
			}
		
	}
}