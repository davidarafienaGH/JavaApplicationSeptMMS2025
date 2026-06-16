public class StudentGradingSystem{
	public static void main(String[] args){
		String[] names = {"John","Jack","Mary","James","Lucy"};
		int[] marks = {69, 80, 100, 70, 50};
		
		for (int i = 0; i < 5; i++){
			if(marks[i] >= 70){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Grade: A");
				System.out.println("============================================");
			}
			else if(marks[i] >= 60){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Grade: B");
				System.out.println("============================================");
			}
			else if(marks[i] >= 50){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Grade: C");
				System.out.println("============================================");
			}
			else if(marks[i] >= 40){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Grade: D");
				System.out.println("============================================");
			}
			else{
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",marks[i]);
				System.out.println("Grade: F");
				System.out.println("============================================");
			}
		}
		// Traditional for loop ends here
		for (int i = 0; i < 5; i++){	
			for(int mark : marks){
				if(mark >= 70){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",mark);
				System.out.println("Grade: A");
				System.out.println("============================================");
			}
			else if(mark >= 60){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",mark);
				System.out.println("Grade: B");
				System.out.println("============================================");
			}
			else if(mark >= 50){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",mark);
				System.out.println("Grade: C");
				System.out.println("============================================");
			}
			else if(mark >= 40){
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",mark);
				System.out.println("Grade: D");
				System.out.println("============================================");
			}
			else{
				System.out.printf("Student Name: %s%n",names[i]);
				System.out.printf("Student Mark: %d%n",mark);
				System.out.println("Grade: F");
				System.out.println("============================================");
			}
		}	}
		
	}
}