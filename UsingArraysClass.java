import java.util.Arrays;

public class UsingArraysClass{
	public static void main(String[] args){
		int[] numbers = {5,8,2,3,9,4,1,6,7,10};
		int[] a = {7,9,6};
		int[] b = {7,9,6};
		int[] c = {7,9,9};
		int[] d = {7,9,6};
		
		int[] myFillArr = new int[10];
		Arrays.fill(myFillArr,5);
		
		int [][] multiArr = {
			{7,8,9,5},
			{2,3,4,5},
			{4,5,6,7},
			{9,2,4,3}
		};
		
		Arrays.sort(numbers);
		System.out.println("The elements of the array are:");
		
		for(int number : numbers){
			System.out.printf("%d%n",number);
		}
		System.out.println("===========================================================");
		System.out.println("Binary Search:");
		int index = Arrays.binarySearch(numbers,9);
		System.out.printf("The index number of 9 is %d%n",index);
		System.out.println("===========================================================");
		
		System.out.println("Array Comparison:");
		boolean isEqual = Arrays.equals(a,b);
		System.out.printf("The result is %b%n",isEqual);
		System.out.println("===========================================================");
		
		System.out.println("Array Comparison by lexicography:");
		int arrayCompare = Arrays.compare(c,d);
		System.out.printf("%d%n",arrayCompare);
		
		System.out.println("My fill arrays are");
		for(int num : myFillArr){
			System.out.printf("%d%n",num);
		}
		int[] evenNumbers = {2,4,6,8,10,12,14,16,18,20};
		int[] copyArr = Arrays.copyOf(evenNumbers,5);
		System.out.println("The elements of the array are:");
		for(int arr : copyArr){
			System.out.printf("%d%n",arr);
		}
		System.out.println(Arrays.toString(evenNumbers));
		System.out.println(Arrays.deepToString(multiArr));
	}
}