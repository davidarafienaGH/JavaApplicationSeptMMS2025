
package assignment2;

public class Student1 extends Person1{
    private String course;
    private String level;
    
    public Student1(String name, int age, String course, String level){
        super(name, age);
        this.course = course;
        this.level = level;
    }
    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Course: " + course);
        System.out.println("Level: " + level);
    }
}
