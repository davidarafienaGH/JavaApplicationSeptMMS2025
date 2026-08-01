
package assignment2;

public class Teacher extends Person1{
    private String department;
    private double salary;
    
     public Teacher(String name, int age, String department, double salary){
        super(name, age);
        this.department = department;
        this.salary = salary;
    }
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
    }
}
