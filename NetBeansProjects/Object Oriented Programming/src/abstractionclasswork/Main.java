
package abstractionclasswork;

public class Main {
    public static void main(String[] args){
        Teacher teacher = new Teacher("Jeremiah", 34);
        Student student = new Student("Monica", 16);
        
        teacher.displayDetails();
        teacher.performDuty();
        System.out.println("==============");
        student.displayDetails();
        student.performDuty();
    }
}
