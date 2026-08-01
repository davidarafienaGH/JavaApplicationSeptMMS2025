package assignment2;

public class Person1 {
    public String name;
    public int age;
    
    public Person1(String name, int age){
        this.name = name;
        this.age = age;
    }
    
    public void displayInfo(){
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    }
}

