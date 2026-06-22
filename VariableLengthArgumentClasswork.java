public class VariableLengthArgumentClasswork{
	public static void main(String[] args){
		System.out.printf("The sum is %d%n",oddNumber(5,6,8,9,3,2,1));
		System.out.printf("The sum is %d%n",oddNumber(8,6,4,7,5,6));
		System.out.printf("The sum is %d%n",oddNumber(5,6,8,9,4));
		System.out.printf("The sum is %d%n",oddNumber(5,6,8,6));
		System.out.printf("The sum is %d%n",oddNumber(5,6,8));
		System.out.printf("The sum is %d%n",oddNumber(5,6));
		System.out.printf("The sum is %d%n",oddNumber(5));
		System.out.printf("The sum is %d%n",oddNumber());
	}
	public static int oddNumber(int...numbers){
		int count = 0;
		for(int number : numbers){
			if(number % 2 == 1){
				count++;
			}
		}
		return count;
	}
}