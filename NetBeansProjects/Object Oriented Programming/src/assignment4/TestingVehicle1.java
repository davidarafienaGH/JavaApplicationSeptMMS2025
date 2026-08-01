
package assignment4;

public class TestingVehicle1 {
    public static void main(String[] args){
        Vehicle1[] vehicleobject ={
            new Bicycle(),
            new Airplane(),
            new Car1()
        };
        for(Vehicle1 vehicle : vehicleobject){
            vehicle.move();
        }    
    }
}
