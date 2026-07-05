import java.time.LocalDateTime;

public class UsingLocalDateTime{
	public static void main(String[] args){
		LocalDateTime todaysDateTime = LocalDateTime.now();
		LocalDateTime meetingDateTime = LocalDateTime.of(2021,05,15,06,30,45);
		LocalDateTime weddingDateTime = LocalDateTime.parse("2027-02-22T11:50:45");
		LocalDateTime resumptionYear = LocalDateTime.getYear(meetingDateTime);
		
		System.out.printf("The current date and time is %s%n",todaysDateTime);
		System.out.printf("The meeting date and time is %s%n",meetingDateTime);
		System.out.printf("The wedding date and time is %s%n",weddingDateTime);
		System.out.printf("The resumption year is %s%n",resumptionYear);
	}
}