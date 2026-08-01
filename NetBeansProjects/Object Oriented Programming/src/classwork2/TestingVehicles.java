package classwork2;

public class TestingVehicles {
    public static void main(String[] args){
        Vehicle[] vehiclebject ={
            new Bike(),
            new Bus(),
            new Car()
        };
        for(Vehicle vehicle : vehiclebject){
            vehicle.move();
        }    
    }
}