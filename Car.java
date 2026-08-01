public class Car{
	String make = "Ford";
	String model = "Mustang";
	int year = 2025;
	double price = 58000.99;
	boolean isRunning = false;
	
	public void start(){
		isRunning = true;
		System.out.println("You start the engine");
	}
	public void stop(){
		isRunning = false;
		System.out.println("You stop the engine");
	}
	public void drive(){
		System.out.println("You drive the " + model);
	}
	public void brake(){
		System.out.println("You brake the" + model);
	}
}