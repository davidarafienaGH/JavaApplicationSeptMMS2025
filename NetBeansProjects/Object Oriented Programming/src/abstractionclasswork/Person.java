
package abstractionclasswork;

public abstract class Person {
    public String name;
    public int age;
    
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    
    void displayDetails(){
        System.out.println("Your name is: " + name);
        System.out.println("Your age is: " + age);
    }
    abstract void performDuty();
    
}
