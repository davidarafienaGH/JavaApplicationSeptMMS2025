
package main;

import java.time.LocalDate;
import school.Student;
import school.GraduateStudent;
import school.UnderGraduateStudent;
import java.util.Scanner;


public class SchoolManagementSystemApp{
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter 1: For Graduate Student");
        System.out.println("Enter 2: For UnderGraduate Student");
        System.out.println("Enter 3: For Payment of Fees");
        
        System.out.print("Enter your choice: ");
        int choice = scan.nextInt();
        
        switch(choice){
            case 1 ->  {
                System.out.print("Enter your student ID: ");
                int studentID = scan.nextInt();
                scan.nextLine();
                
                System.out.print("Enter First Name: ");
                String firstName = scan.nextLine();
                
                System.out.print("Enter Last Name: ");
                String lastName = scan.nextLine();
                
                System.out.print("Enter Gender: ");
                char gender = scan.next().charAt(0);
                scan.nextLine();
                
                System.out.print("Enter Dirth Of Birth(YYYY-MM-DD): ");
                String dateOfBirth = scan.nextLine();
                
                System.out.print("Enter Phone Number: ");
                String phoneNumber = scan.nextLine();
                
                System.out.print("Enter Final Year Project: ");
                String finalYearProject = scan.nextLine();
                
                GraduateStudent gStudent = 
                        new GraduateStudent(studentID,firstName,lastName,
                        gender, LocalDate.parse(dateOfBirth), 
                                phoneNumber,finalYearProject);
                gStudent.displayStudentInfo();
                System.out.println("====================");
                System.out.println("");
            break;
            }
            case 2 ->  {
                System.out.print("Enter your student ID: ");
                int studentID = scan.nextInt();
                scan.nextLine();
                
                System.out.print("Enter First Name: ");
                String firstName = scan.nextLine();
                
                System.out.print("Enter Last Name: ");
                String lastName = scan.nextLine();
                
                System.out.print("Enter Gender: ");
                char gender = scan.next().charAt(0);
                scan.nextLine();
                
                System.out.print("Enter Dirth Of Birth(YYYY-MM-DD): ");
                String dateOfBirth = scan.nextLine();
                
                System.out.print("Enter Phone Number: ");
                String phoneNumber = scan.nextLine();
                
                System.out.print("Enter Level: ");
                int level = scan.nextInt();
                
                UnderGraduateStudent ugStudent = 
                        new UnderGraduateStudent(studentID,firstName,lastName,
                        gender, LocalDate.parse(dateOfBirth), 
                                phoneNumber,level);
                
                ugStudent.displayStudentInfo();
                System.out.println("====================");
                System.out.println("");
            break;    
            }
            case 3 -> {
                System.out.println("Choose the following option");
                System.out.println("Enter 1: Amount only");
                System.out.println("Enter 2: Amount and Full Name");
                System.out.println("Enter 3: Amount, Full Name and Payment Method");
                
                System.out.print("Enter your option: ");
                int myOption  = scan.nextInt();
                
                switch(myOption){
                    case 1 -> {
                       System.out.print("Enter StudentID: ");
                       int studentID = scan.nextInt();
                       scan.nextLine();
                       
                       System.out.print("Enter First Name: ");
                       String firstName = scan.nextLine();
                       
                       System.out.print("Enter Last Name: ");
                       String lastName= scan.nextLine();
                       
                       System.out.print("Enter gender: ");
                       char gender = scan.next().charAt(0);
                       scan.nextLine();
                       
                       System.out.print("Enter Birth Date(YYYY-MM-DD): ");
                       String birthDate = scan.nextLine();
                       
                       System.out.print("Enter Phone Number: ");
                       String phoneNumber = scan.nextLine();
                       
                       Student student = new Student(studentID, firstName, lastName, gender, 
                               LocalDate.parse(birthDate), phoneNumber);
                       
                       System.out.print("Enter Amount: ");
                       double amount = scan.nextDouble();
                       student.payFees(amount);
                       
                    }
                    case 2 -> {
                        System.out.print("Enter StudentID: ");
                       int studentID = scan.nextInt();
                       scan.nextLine();
                       
                       System.out.print("Enter First Name: ");
                       String firstName = scan.nextLine();
                       
                       System.out.print("Enter Last Name: ");
                       String lastName= scan.nextLine();
                       
                       System.out.print("Enter gender: ");
                       char gender = scan.next().charAt(0);
                       scan.nextLine();
                       
                       System.out.print("Enter Birth Date(YYYY-MM-DD): ");
                       String birthDate = scan.nextLine();
                       
                       System.out.print("Enter Phone Number: ");
                       String phoneNumber = scan.nextLine();
                       
                       Student student = new Student(studentID, firstName, lastName, gender, 
                               LocalDate.parse(birthDate), phoneNumber);
                       
                       System.out.print("Enter Amount: ");
                       double amount = scan.nextDouble();
                       scan.nextLine();
                       
                       System.out.print("Enter Full Name: ");
                       String fullName = scan.nextLine();
                       student.payFees(amount,fullName);
                    }
                    case 3 -> {
                       System.out.print("Enter StudentID: ");
                       int studentID = scan.nextInt();
                       scan.nextLine();
                       
                       System.out.print("Enter First Name: ");
                       String firstName = scan.nextLine();
                       
                       System.out.print("Enter Last Name: ");
                       String lastName= scan.nextLine();
                       
                       System.out.print("Enter gender: ");
                       char gender = scan.next().charAt(0);
                       scan.nextLine();
                       
                       System.out.print("Enter Birth Date(YYYY-MM-DD): ");
                       String birthDate = scan.nextLine();
                       
                       System.out.print("Enter Phone Number: ");
                       String phoneNumber = scan.nextLine();
                       
                       Student student = new Student(studentID, firstName, lastName, gender, 
                               LocalDate.parse(birthDate), phoneNumber);
                       
                       System.out.print("Enter Amount: ");
                       double amount = scan.nextDouble();
                       scan.nextLine();
                       
                       System.out.print("Enter Full Name: ");
                       String fullName = scan.nextLine();
                       
                       System.out.print("Enter Payment Method: ");
                       String paymentMethod = scan.nextLine();
                       student.payFees(amount,fullName, paymentMethod);                                             
                    }
                    default -> {
                        System.out.println("Invalid input");
                    }
                }
            break;
            }
            
            default -> System.out.println("Invalid Input");
        }
    
    }
        
 
    
}