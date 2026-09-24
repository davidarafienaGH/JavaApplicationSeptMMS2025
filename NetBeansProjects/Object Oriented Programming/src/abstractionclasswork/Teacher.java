
package abstractionclasswork;

public class Teacher extends Person{
    public Teacher(String name, int age){
        super(name, age);
    }
    @Override
    void performDuty(){
        System.out.println("The teacher is teaching");
    }
}
