import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class UsingDateTimeFormatter{
	public static void main(String[] args){
		
		LocalDate today = LocalDate.now();
		
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		
		System.out.println(today.format(formatter1));
		System.out.println(today.format(formatter2));
		
		LocalDateTime now = LocalDateTime.now();
		
		System.out.println("dd    : " +
				now.format(DateTimeFormatter.ofPattern("dd")));
		
		System.out.println("MM    : " +
				now.format(DateTimeFormatter.ofPattern("MM")));
		
		System.out.println("MMM    : " +
				now.format(DateTimeFormatter.ofPattern("MMM")));
	}
}