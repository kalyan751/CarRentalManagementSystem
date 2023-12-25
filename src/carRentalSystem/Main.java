package carRentalSystem;


public class Main {

	public static void main(String[] args) {
		carRentalSystem rentalSystem =  new carRentalSystem();
		//String carId, String brand, String model, double basePricePerDay, boolean isAvailable
		Car c1 = new Car("C001", "Toyota","Canary",60.0);
		Car c2 = new Car("C002", "Honda","Accord",70.0);
		Car c3= new Car("C003", "Mahindra","Thar",150.0);
		
		rentalSystem.addCar(c1);
		rentalSystem.addCar(c2);
		rentalSystem.addCar(c3);
		
		rentalSystem.menu();
		

	}

}
