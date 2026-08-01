public class MainApp{
	public static void main(String[] args){
		Student student1 = new Student(1, "John", "Kirakou",'M');
		Student student2 = new Student(2, "Clint", "Eastwood",'M');
		Student student3 = new Student(3, "Dio", "Brando",'M');
		Student student4 = new Student(4, "David", "Bowie",'M');
		Student student5 = new Student(5, "Joy", "Martins",'M');
		
		student1.displayStudentInfo();
		System.out.println("======================");
		
		student2.displayStudentInfo();
		System.out.println("======================");
		
		student3.displayStudentInfo();
		System.out.println("======================");
		
		student4.displayStudentInfo();
		System.out.println("======================");
		
		student5.displayStudentInfo();
		System.out.println("======================");
	}
}