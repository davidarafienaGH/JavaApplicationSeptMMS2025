public class PrimitiveDataType{
	public static void main(String[] args){
		byte myAge = 17;
		System.out.printf("Raymama is %d years old%n",myAge);
		
		short quantity = 20000;
		System.out.printf("The quanity of iphones ordered is %,d%n",quantity);
		
		int nigeriaPopulation = 294848848;
		System.out.printf("The population of nigeria is %,d%n",nigeriaPopulation);
		
		long worldPopulation = 37830855893083379L;
		System.out.printf("The population of the world is %,d%n",worldPopulation);
		
		float price = 577858.84747f;
		System.out.printf("The price of each iphone per unit is %,.2f%n",price);
		
		double myBalance = 7869059840494.9495950;
		System.out.printf("My account balance is %c%,.2f%n",'$',myBalance);
		
		char symbol = '%';
		System.out.printf("There is an increment in the world's population by 20.5%c%n",symbol);
		
		boolean isJavaFun = true;
		System.out.printf("Do you love Java %b",isJavaFun);
		
	}
}	