
package abstractionclasswork;

public class Student extends Person{
    public Student(String name, int age){
        super(name, age);
    }
    @Override
    void performDuty(){
        System.out.println("The student is studying");
    }
}
