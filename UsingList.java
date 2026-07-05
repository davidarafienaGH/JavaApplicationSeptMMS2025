import java.util.ArrayList;
import java.util.List;

public class UsingList{
	public static void main(String[] args){
		List<String> cars = new ArrayList<>();
		
		cars.add("Toyota");
		cars.add("BMW");
		cars.add("Mercedes-Benz");
		cars.add("Mercedes-Benz");
		cars.add(1,"Ford");
		// set the value of an elements
		cars.set(0,"Lotus");
		cars.remove(2);
		
		for(String car : cars){
			System.out.println(car);
		}
		
		System.out.println(cars);
		System.out.printf("the size of the list is %d elements%n",cars.size());
		System.out.println(cars.get(1));
		
	}
}