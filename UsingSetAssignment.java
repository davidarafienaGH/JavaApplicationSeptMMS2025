import java.util.Set;
import java.util.HashSet;

public class UsingSetAssignment{
	public static void main(String[] args){
		Set<String> studentEmails = new HashSet<>();
		
		
		studentEmails.add("roto@gmail.com");
		studentEmails.add("bjorn@yahoo.com");
		studentEmails.add("lowki@gmail.com");
		studentEmails.add("rested@gmail.com");
		studentEmails.add("pearly@outlook.com");
		studentEmails.add("merrybell@gmail.com");
		studentEmails.add("mista@yahoo.com");
		studentEmails.add("jojo@outlook.com");
		studentEmails.add("dio@yahoo.com");
		studentEmails.add("johnny@gmail.com");
		
		
		System.out.println(studentEmails);
		
		System.out.println("");
		
		for(String studentEmail : studentEmails){
			System.out.println(studentEmail);
		}
	}
}