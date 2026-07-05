import java.time.LocalDate;

public class UsingLocalDate{
	public static void main(String[] args){
		LocalDate todaysDate = LocalDate.now();
		LocalDate myBirthDate = LocalDate.of(2008,02,22);
		LocalDate resumptionDate = LocalDate.parse("2024-10-15");
		
		boolean isLeapYear = resumptionDate.isLeapYear();
		boolean isEqual = resumptionDate.equals(myBirthDate);
		
		System.out.printf("Today's date is %s%n",todaysDate);
		System.out.printf("My Birth date is %s%n",myBirthDate);
		System.out.printf("The date of resumption is %s%n",resumptionDate);
		System.out.printf("The resumption year is %s%n",resumptionDate.getYear());
		System.out.printf("The resumption month is %s%n",resumptionDate.getMonth());
		System.out.printf("The resumption day is %s%n",resumptionDate.getDayOfMonth());
		System.out.printf("The meeting date is %s%n",resumptionDate.plusDays(10));
		System.out.printf("The meeting date is %s%n",resumptionDate.plusMonths(2));
		System.out.printf("The meeting date is %s%n",resumptionDate.plusYears(1));
		System.out.printf("The party date is %s%n",resumptionDate.minusDays(10));
		System.out.printf("Is %s a leap year? %b%n",resumptionDate, isLeapYear);
		System.out.printf("Is %s the same as %s? %b%n",resumptionDate, myBirthDate, isEqual);
	}
}