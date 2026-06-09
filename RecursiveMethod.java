//A recursive method is a method that recalls itself.

public class RecursiveMethod{
	public static void main(String[] args){
		displayName();
	}
	public static void displayName(){
		String name = "John Doe";
		System.out.printf("Your name is %s%n",name);
		displayName();
	}
}