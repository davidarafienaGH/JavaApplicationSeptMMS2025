import java.util.Scanner;

public class IfElseIfStatement{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		int score;
		String name;
		String subject;
		
		System.out.print("Enter your name: ");
		name = input.nextLine();
		
		System.out.print("Enter your subject: ");
		subject = input.nextLine();
		
		System.out.print("Enter your score: ");
		score = input.nextInt();
		
		System.out.println("");
		System.out.println("==========================================================================");
		
		if(score >= 70){
			System.out.printf("Full Name: %s%n",name);
			System.out.printf("Subject: %s%n",subject);
			System.out.printf("Score: %d%n",score);
			System.out.println("Your Grade is A");
		}
		else if(score >= 60 && score <= 69){
			System.out.printf("Full Name: %s%n",name);
			System.out.printf("Subject: %s%n",subject);
			System.out.printf("Score: %d%n",score);
			System.out.println("Your Grade is B");
		}
		else if(score >= 50 && score <= 59){
			System.out.printf("Full Name: %s%n",name);
			System.out.printf("Subject: %s%n",subject);
			System.out.printf("Score: %d%n",score);
			System.out.println("Your Grade is C");
		}
		else if(score >= 40 && score <= 49){
			System.out.printf("Full Name: %s%n",name);
			System.out.printf("Subject: %s%n",subject);
			System.out.printf("Score: %d%n",score);
			System.out.println("Your Grade is D");
		}
		else if(score >= 30 && score <= 39){
			System.out.printf("Full Name: %s%n",name);
			System.out.printf("Subject: %s%n",subject);
			System.out.printf("Score: %d%n",score);
			System.out.println("Your Grade is E");
		}
		else{
			System.out.printf("Full Name: %s%n",name);
			System.out.printf("Subject: %s%n",subject);
			System.out.printf("Score: %d%n",score);
			System.out.println("Your Grade is F");
		}
	}
}