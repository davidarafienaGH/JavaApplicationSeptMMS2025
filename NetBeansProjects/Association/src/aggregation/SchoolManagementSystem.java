
package aggregation;

import composition.Payment;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class SchoolManagementSystem {
    public static void main(String[] args){      
        //Student
        Student s1 = new Student(101, "Jeremiah", "Mosely", 'M');
        Student s2 = new Student(102, "Monica", "Rambeux", 'F');
        Student s3 = new Student(103, "Andre", "Johnson", 'M');
        Student s4 = new Student(104, "Rainbow", "Johnson", 'F');
        Student s5 = new Student(105, "Dio", "Brando", 'M'); 
        Student s6 = new Student(106, "Johnathan", "Joestar", 'M');
        Student s7 = new Student(107, "Johnny", "Joestar", 'M');
        
        
        
        ArrayList<Student> student = new ArrayList<>();
        student.add(s1);
        student.add(s2);
        student.add(s3);
        student.add(s4);
        student.add(s5);
        student.add(s6);
        student.add(s7);
        
        s1.makePayment(300000.00, LocalDate.of(2026,8,10), "Transfer", "Paid for Data Analytics");
        s3.makePayment(500000.00, LocalDate.of(2026,8,15), "POS", "Paid for Java");
        s5.makePayment(350000.00, LocalDate.of(2026,8,8), "Transfer", "Paid for MMS");
      
        School school = new School("NIIT", student);
        
        school.displayStudentDetails();
    }
}
