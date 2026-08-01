package polymorphism.methodoverriding;

public class AllAnimal {
    public static void main(String[] args){
        Animal[] animalObject={
            new Dog(),
            new Cat(),
            new Lion()
        };
        for(Animal animal : animalObject){
            animal.sound();
        }
    }
    
}