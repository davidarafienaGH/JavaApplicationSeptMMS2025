public class UsingEnum{
	public static void main(String[] args){
		DaysOfWeek day = DaysOfWeek.MONDAY;
		
		DaysOfWeek days = DaysOfWeek.SUNDAY;
		
		System.out.printf("Today is %s%n",day);
		System.out.printf("Yesterday was %s%n",days);
	}
}