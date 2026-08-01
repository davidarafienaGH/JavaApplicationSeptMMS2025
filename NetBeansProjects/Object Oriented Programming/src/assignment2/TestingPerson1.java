
package assignment2;

public class TestingPerson1 {
    public static void main(String[] args){
        Student1 student = new Student1("Jerry", 18, "Data Analysis", "200");
        Teacher teacher = new Teacher("Meredith", 25, "Mathematics", 180000.00);
        
        student.displayInfo();
        System.out.println("=====================================");
        teacher.displayInfo();
        
    }
}
