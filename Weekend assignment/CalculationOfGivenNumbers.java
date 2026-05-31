import java.util.Scanner;

public class CalculationOfGivenNumbers{
	public static void main(String[] Args){
		Scanner input = new Scanner(System.in);
		
		int[] num = new int[11];
		
		for(int i = 1; i <= 10; i++){
			System.out.printf("Enter number %d: ",i);
			num[i] = input.nextInt();
		}
		int sum1 = num[0] + num[4] + num[9];
		
		int sum2 = num[2] + num[7] + num[1];
		
		int multiplication = sum1 * sum2;
		
		int sum3 = num[3] + num[6] + num[5] + num[8];
		
		int result = sum3 - multiplication;
		
		System.out.printf("The result is %d%n",result);
		System.out.println("");
		
		if(result >= 100){
			System.out.print("Hurray i did it!");
		}
		else{
			System.out.print("I still need to learn more in Java");
		}
	}
}