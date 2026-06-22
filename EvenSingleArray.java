public class EvenSingleArray{
	public static void main(String[] args){
		int[] arr = {7,8,4,3,2,9,0,1,6,8};
		System.out.println("The even numbers are:");
		evenNumbers(arr);
		
	}
	public static void evenNumbers(int[] numbers){
		for(int i = 0; i < numbers.length; i++){
			if(numbers[i] % 2 == 0){
				System.out.println(numbers[i]);
			}
		}
	}
}